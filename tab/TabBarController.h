//
//  TabBarController.h
//  tab
//
//  Created by MIYATA Wataru on 2014/02/12.
//  Copyright (c) 2014年 MIYATA Wataru. All rights reserved.
//

#import <UIKit/UIKit.h>

@interface TabBarController : UITabBarController<UITabBarControllerDelegate>
- (void) tabBarController:(UITabBarController *)tabBarController didSelectViewController:(UIViewController *)viewController;
- (void) reloadCellItems;
@end

@protocol MyTabBarControllerDelegate

- (void) didSelect:(TabBarController*) tabBarController;
- (void) reloadCellItems;
@end