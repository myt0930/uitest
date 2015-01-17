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
#import "Appirater.h"
#import "Common.h"
#import "LoadData.h"
#import "TabBarController.h"

#import "LiveHouseTrait.h"
#import "LiveInfoTrait.h"
#import "appCCloud.h"
#import <FelloPush/KonectNotificationsAPI.h>


@implementation AppDelegate

- (BOOL)application:(UIApplication *)application didFinishLaunchingWithOptions:(NSDictionary *)launchOptions
{
    //お気に入りをロード
    [[SettingData instance] loadData];
	
	_indicator = [TlsIndicatorView instance];
	[self.window.rootViewController.view addSubview:_indicator];
	
	[self performSelectorInBackground:@selector(checkUpdateMaster) withObject:nil];
	
    NSString* appId = @"10562";
    [KonectNotificationsAPI initialize:nil launchOptions:launchOptions appId:appId];
    
	[Appirater setAppId:@"840221818"];
    [Appirater appLaunched:YES];
    
    _showrecommendAppRate = 50;
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
    _isResume = YES;
	[self checkUpdateMaster];
	[Appirater appEnteredForeground:YES];
    
    [UIApplication sharedApplication].applicationIconBadgeNumber = -1;
	
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

// デバイストークンを受信した際の処理
- (void)application:(UIApplication *)application didRegisterForRemoteNotificationsWithDeviceToken:(NSData *)devToken
{
    // 渡ってきたデバイストークンを渡す
    [KonectNotificationsAPI setupNotifications:devToken];
}

// プッシュ通知を受信した際の処理
- (void)application:(UIApplication *)application didReceiveRemoteNotification:(NSDictionary *)userInfo
{
    // 渡ってきたuserInfoを渡す
    [KonectNotificationsAPI processNotifications:userInfo];
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
            //更新無し
            
			[self loadMaster];

            //アプリ表示
            if(arc4random() % 100 <= _showrecommendAppRate)
            {
                [self openAppRecommend];
            }
			return;
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
                //アプリ表示
                if(arc4random() % 100 <= _showrecommendAppRate)
                {
                    [self openAppRecommend];
                }
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
        
		if( programVersion > PROGRAM_VERSION )
		{
			[self endIndicator];
			//アプリを更新させる
			[TlsAlertView showAppUpdateDialog:^(NSInteger index) {
                [[UIApplication sharedApplication] openURL:[NSURL URLWithString:@"https://itunes.apple.com/app/id840221818"]];
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
        
        if([loadData isReadable:sizeof(int16_t)])
        {
            _showrecommendAppRate = [loadData getInt16];
        }
        else
        {
            _showrecommendAppRate = 50;
        }
	}
	
	// 通知先にデータを渡す場合はuserInfoにデータを指定
	[[NSNotificationCenter defaultCenter] postNotificationName:NOTICE_FINISH_LOADMAST
														object:self
													  userInfo:nil];
	
	[self endIndicator];
}

// このメソッドで、プッシュ通知からの起動後の処理を行うことが出来る
- (void)onLaunchFromNotification:(NSString *)notificationsId message:(NSString *)message extra:(NSDictionary *)extra
{
    NSLog(@"ここでextraの中身にひもづいたインセンティブの付与などを行うことが出来ます");
}

- (void)openAppRecommend
{
    if([[SettingData instance] isShowDetailDialog])
    {
        [TlsAlertView showAppRecommendDialog:^(NSInteger index)
        {
            [self startIndicator];
            [self performSelector:@selector(openAppRecommendBlock) withObject:nil afterDelay:0.8];
        }];
    }
}

- (void)openAppRecommendBlock
{
    [self endIndicator];
    [appCCloud setupAppCWithMediaKey:@"60b12829000287197bd3b54b4c69a0b78a1b35c0"
                              option:APPC_CLOUD_AD];
    [appCCloud setSplashLogo:NO];
    [appCCloud openWebView];
}

@end
