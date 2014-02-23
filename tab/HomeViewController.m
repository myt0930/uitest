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

@implementation HomeViewController

- (void)viewDidLoad
{
    [super viewDidLoad];
	
	[LiveInfoTrait addTestLiveInfo];
	self.items = [LiveInfoTrait traitList];
	
	//タイトル設定
	self.navigationItem.title = @"2014/02/18 (Tue)";
    UIBarButtonItem *btn = [[UIBarButtonItem alloc]
                            initWithBarButtonSystemItem:UIBarButtonSystemItemCamera
                            target:self
                            action:@selector(showCalendar:)];
    self.navigationItem.rightBarButtonItem = btn;
}

- (void)didReceiveMemoryWarning
{
    [super didReceiveMemoryWarning];
    // Dispose of any resources that can be recreated.
}

- (void)showCalendar:(UIBarButtonItem*)buttonItem
{
    CKViewController *controller = [[CKViewController alloc] init];
    [self.navigationController pushViewController:controller animated:YES];
}

@end
