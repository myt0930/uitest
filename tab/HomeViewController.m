//
//  FirstViewController.m
//  tab
//
//  Created by MIYATA Wataru on 2014/02/05.
//  Copyright (c) 2014年 MIYATA Wataru. All rights reserved.
//

#import "HomeViewController.h"
#import "CustomTableViewCell.h"
#import "LiveInfoTrait.h"
#import "TabBarController.h"
#import "DetailViewController.h"
#import "CKViewController.h"
#import "Common.h"

@implementation HomeViewController

- (void)viewDidLoad
{
    [super viewDidLoad];
	
	[LiveInfoTrait addTestLiveInfo];
    
    NSDate *todayDate = [NSDate date];
	self.items = [LiveInfoTrait traitListWithDate:[NSString stringWithDateFormat:@"yyyyMMdd" date:todayDate]];
	
	//タイトル設定
	self.navigationItem.title = [NSString stringWithDateFormat:@"yyyy/MM/dd (E)" date:todayDate];
    UIBarButtonItem *btn = [[UIBarButtonItem alloc]
                            initWithTitle:@"カレンダー"
                            style:UIBarButtonItemStylePlain
                            target:self
                            action:@selector(showCalendar:)];
    self.navigationItem.rightBarButtonItem = btn;
}

- (void)didReceiveMemoryWarning
{
    [super didReceiveMemoryWarning];
}

- (void)showCalendar:(UIBarButtonItem*)buttonItem
{
    CKViewController *controller = [[CKViewController alloc] init];
    [self.navigationController pushViewController:controller animated:YES];
}

- (void)changeDate:(NSDate*)date
{
    self.items = [LiveInfoTrait traitListWithDate:[NSString stringWithDateFormat:@"yyyyMMdd" date:date]];
    [self reloadTable];
}

@end
