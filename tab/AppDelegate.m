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

@implementation AppDelegate

- (BOOL)application:(UIApplication *)application didFinishLaunchingWithOptions:(NSDictionary *)launchOptions
{
    //お気に入りをロード
    [[SettingData instance] loadData];
    return YES;
}
							
- (void)applicationWillResignActive:(UIApplication *)application
{
	//ここでアラートを呼んでみる
    [TlsAlertView showMasterUpdateDialog];
}

- (void)applicationDidEnterBackground:(UIApplication *)application
{
	
}

- (void)applicationWillEnterForeground:(UIApplication *)application
{

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
@end
