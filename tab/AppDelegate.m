//
//  AppDelegate.m
//  tab
//
//  Created by MIYATA Wataru on 2014/02/05.
//  Copyright (c) 2014年 MIYATA Wataru. All rights reserved.
//

#import "AppDelegate.h"
#import "SettingData.h"
#import "TlsAlertView.h"
#import "TlsIndicatorView.h"
#import "NetworkDownload.h"
//#import "Appirater.h"
#import "Common.h"
#import "LoadData.h"
#import "TabBarController.h"

#import "LiveHouseTrait.h"
#import "LiveInfoTrait.h"

@implementation AppDelegate

- (BOOL)application:(UIApplication *)application didFinishLaunchingWithOptions:(NSDictionary *)launchOptions
{
    //お気に入りをロード
    [[SettingData instance] loadData];
	
	_indicator = [TlsIndicatorView instance];
	[self.window.rootViewController.view addSubview:_indicator];
	
	[self performSelectorInBackground:@selector(checkUpdateMaster) withObject:nil];
	
	//TODO: AppIdをセット
//	[Appirater setAppId:@"000000"];
//	[Appirater appLaunched:YES];
    return YES;
}
							
- (void)applicationWillResignActive:(UIApplication *)application
{

}

- (void)applicationDidEnterBackground:(UIApplication *)application
{
	
}

- (void)applicationWillEnterForeground:(UIApplication *)application
{
	[self checkUpdateMaster];
//	[Appirater appEnteredForeground:YES];
	
	[[NSNotificationCenter defaultCenter] postNotificationName:NOTICE_FINISH_LOADMAST
														object:self
													  userInfo:nil];
}

- (void)applicationDidBecomeActive:(UIApplication *)application
{

}

- (void)applicationWillTerminate:(UIApplication *)application
{

}

// Optional UITabBarControllerDelegate method
- (void)tabBarController:(UITabBarController *)tabBarController didSelectViewController:(UIViewController *)viewController {
	

}

//----------------------------

- (void)startIndicator
{
	[_indicator startAnimating];
}
- (void)endIndicator
{
	[_indicator stopAnimating];
}

- (void)checkUpdateMaster
{
	[self startIndicator];
	[NetworkDownload isNeedUpdateMaster:^(enum MASTER_UPDATE_STATE state) {
		[self endIndicator];
		
		if( state == MASTER_UPDATE_NONE || state == MASTER_UPDATE_NETWORK_ERROR )
		{
			[self loadMaster];
			return;	//更新無し
		}
		//「更新がある」ダイアログ
		[TlsAlertView showNeedUpdateDialog:^(NSInteger index) {
			//更新処理
			[self masterDownload:state == MASTER_UPDATE_CONSTRAINT];
		}];
	}];
}
- (void)masterDownload:(BOOL)isContraintDL
{
	[self startIndicator];
	
	[NetworkDownload downloadMaster:isContraintDL block:^(BOOL isSuccess) {
		[self endIndicator];
		if( isSuccess )
		{
			//更新完了ダイアログ
			[TlsAlertView showDoneUpdateDialog:^(NSInteger index)
			{
				[self loadMaster];
			}];
			return;
		}
		
		//リトライを促すダイアログ
		[TlsAlertView showRetryUpdateDialog:isContraintDL block:^(NSInteger index) {
			if( !isContraintDL && index == 0 )
			{
				//「閉じる」を選択した時は何もせずに終了
				[self loadMaster];
				return;
			}
			
			//リトライ
			[self masterDownload:isContraintDL];
		}];
	}];
}

- (void)loadMaster
{
	[self startIndicator];
	
	NSString *filePath			= [CACHE_FOLDER stringByAppendingPathComponent:MASTER_FILE];
	NSFileHandle *fileHandle	= [NSFileHandle fileHandleForReadingAtPath:filePath];
	if (fileHandle)
	{
		NSData *masterData		= [fileHandle readDataToEndOfFile];
		LoadData *loadData		= [[LoadData alloc] initWithData:masterData];
        //プログラムバージョン
		int programVersion		= [loadData getInt32];
        
		if( programVersion+1 > PROGRAM_VERSION )
		{
			[self endIndicator];
			//アプリを更新させる
			[TlsAlertView showAppUpdateDialog:^(NSInteger index) {
				NSURL* url = [NSURL URLWithString:@"http://www.google.co.jp/"]; //TODO: 要修正
				[[UIApplication sharedApplication] openURL:url];
			}];
			return;
		}
		
		int masterCount = [loadData getInt16];
        for( int i = 0;i < masterCount;i++ )
        {
            int masterType = [loadData getInt16];
            switch (masterType) {
                case MASTER_TYPE_LIVEINFO:
                    [LiveInfoTrait loadMast:loadData];
                    break;
                case MASTER_TYPE_LIVEHOUSE:
                    [LiveHouseTrait loadMast:loadData];
                    break;
                default:
                    break;
            }
        }
	}
	
	// 通知先にデータを渡す場合はuserInfoにデータを指定
	[[NSNotificationCenter defaultCenter] postNotificationName:NOTICE_FINISH_LOADMAST
														object:self
													  userInfo:nil];
	
	[self endIndicator];
}
@end
