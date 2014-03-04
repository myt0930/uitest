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

@implementation AppDelegate

- (BOOL)application:(UIApplication *)application didFinishLaunchingWithOptions:(NSDictionary *)launchOptions
{
    //お気に入りをロード
    [[SettingData instance] loadData];
	
	_indicator = [[TlsIndicatorView alloc] init];
	[self.window.rootViewController.view addSubview:_indicator];
    return YES;
}
							
- (void)applicationWillResignActive:(UIApplication *)application
{
	//ここでアラートを呼んでみる
//    [TlsAlertView showMasterUpdateDialog];
}

- (void)applicationDidEnterBackground:(UIApplication *)application
{
	
}

- (void)applicationWillEnterForeground:(UIApplication *)application
{
	//
	[[UIApplication sharedApplication] beginIgnoringInteractionEvents];
	[_indicator startAnimating];
	[self performSelector:@selector(test:) withObject:nil afterDelay:5.0];
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
@end
