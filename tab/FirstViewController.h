//
//  FirstViewController.h
//  tab
//
//  Created by MIYATA Wataru on 2014/02/05.
//  Copyright (c) 2014年 MIYATA Wataru. All rights reserved.
//

#import <UIKit/UIKit.h>
#import "TabBarController.h"

@class CustomTableViewCell;

@interface FirstViewController : UIViewController<UITableViewDelegate,UITableViewDataSource,MyTabBarControllerDelegate>
@property (strong, nonatomic) IBOutlet UITableView *tableView;

@end
