//
//  AppDelegate.m
//  tab
//
//  Created by MIYATA Wataru on 2014/02/05.
//  Copyright (c) 2014年 MIYATA Wataru. All rights reserved.
//

#import "AppDelegate.h"
#import "SettingData.h"
#import "SSGentleAlertView.h"
#import "TlsAlertView.h"
#import "TlsIndicatorView.h"
#import "NetworkDownload.h"
#import "Appirater.h"

@implementation AppDelegate

- (BOOL)application:(UIApplication *)application didFinishLaunchingWithOptions:(NSDictionary *)launchOptions
{
    //お気に入りをロード
    [[SettingData instance] loadData];
	
	_indicator = [[TlsIndicatorView alloc] init];
	[self.window.rootViewController.view addSubview:_indicator];
	
	[self performSelectorInBackground:@selector(checkUpdateMaster) withObject:nil];
	
	//TODO: AppIdをセット
	[Appirater setAppId:@"000000"];
	[Appirater appLaunched:YES];
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
	[Appirater appEnteredForeground:YES];
}

- (void)test:(id)id
{
	[[UIApplication sharedApplication] endIgnoringInteractionEvents];
	[_indicator stopAnimating];
}

- (void)applicationDidBecomeActive:(UIApplication *)application
{

}

- (void)applicationWillTerminate:(UIApplication *)application
{

}

// Optional UITabBarControllerDelegate method
- (void)tabBarController:(UITabBarController *)tabBarController didSelectViewController:(UIViewController *)viewController {
	
	NSLog(@"%@",viewController.class);
}

//----------------------------

- (void)startIndicator
{
	[[UIApplication sharedApplication] beginIgnoringInteractionEvents];
	[_indicator startAnimating];
}
- (void)endIndicator
{
	[[UIApplication sharedApplication] endIgnoringInteractionEvents];
	[_indicator stopAnimating];
}

- (void)checkUpdateMaster
{
	[self startIndicator];
	[NetworkDownload isNeedUpdateMaster:^(enum MASTER_UPDATE_STATE state) {
		[self endIndicator];
		
		if( state == MASTER_UPDATE_NONE )
		{
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
			[TlsAlertView showDoneUpdateDialog:^(NSInteger index) {
				[self startIndicator];
				//マスターダウンロード後処理
				[self didMasterDownload];
				[self endIndicator];
			}];
			return;
		}
		
		//リトライを促すダイアログ
		[TlsAlertView showRetryUpdateDialog:isContraintDL block:^(NSInteger index) {
			if( !isContraintDL && index == 0 )
			{
				//「閉じる」を選択した時は何もせずに終了
				return;
			}
			
			//リトライ
			[self masterDownload:isContraintDL];
		}];
	}];
}

- (void)didMasterDownload
{
	//マスターリロード
	//画面再生成
}
@end
