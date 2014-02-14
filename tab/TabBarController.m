//
//  TabBarController.m
//  tab
//
//  Created by MIYATA Wataru on 2014/02/12.
//  Copyright (c) 2014年 MIYATA Wataru. All rights reserved.
//

#import "TabBarController.h"

@interface TabBarController ()

@end

@implementation TabBarController

- (id)initWithNibName:(NSString *)nibNameOrNil bundle:(NSBundle *)nibBundleOrNil
{
    self = [super initWithNibName:nibNameOrNil bundle:nibBundleOrNil];
    if (self) {
        
    }
    return self;
}

- (void)viewDidLoad
{
    [super viewDidLoad];
   
	self.delegate = self;
	
}

- (void)didReceiveMemoryWarning
{
    [super didReceiveMemoryWarning];
    // Dispose of any resources that can be recreated.
}

- (void) tabBarController:(UITabBarController *)tabBarController didSelectViewController:(UIViewController *)viewController
{
	// プロトコルを実装しているかのチェック
	if( [viewController isMemberOfClass:[UINavigationController class]] )
	{
		NSArray *controllers = [(UINavigationController*)viewController viewControllers];
		UIViewController *caller = [controllers objectAtIndex:0];
		if ([caller conformsToProtocol:@protocol(MyTabBarControllerDelegate)]) {
			// 各UIViewControllerのデリゲートメソッドを呼ぶ
			[(UIViewController<MyTabBarControllerDelegate>*)caller didSelect:self];
		}
	}
}

@end
