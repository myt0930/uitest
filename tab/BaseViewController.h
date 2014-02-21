//
//  BaseViewController.h
//  tab
//
//  Created by Wataru Miyata on 2014/02/15.
//  Copyright (c) 2014年 MIYATA Wataru. All rights reserved.
//

#import <UIKit/UIKit.h>
#import "TabBarController.h"

@interface BaseViewController : UIViewController<UITableViewDelegate,UITableViewDataSource,MyTabBarControllerDelegate>
@property (strong, nonatomic) IBOutlet UITableView *tableView;
@property (strong, readwrite) NSArray *items;

- (void)reloadTable;
@end
