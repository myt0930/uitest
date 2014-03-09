//
//  TabBarController.h
//  tab
//
//  Created by MIYATA Wataru on 2014/02/12.
//  Copyright (c) 2014年 MIYATA Wataru. All rights reserved.
//

#import <UIKit/UIKit.h>
@class GADBannerView;

@interface TabBarController : UITabBarController<UITabBarControllerDelegate>
@property GADBannerView *bannerView;


- (void) tabBarController:(UITabBarController *)tabBarController didSelectViewController:(UIViewController *)viewController;
@end

@protocol MyTabBarControllerDelegate

- (void) didSelect:(TabBarController*) tabBarController;
@end