//
//  SecondViewController.m
//  tab
//
//  Created by MIYATA Wataru on 2014/02/05.
//  Copyright (c) 2014年 MIYATA Wataru. All rights reserved.
//

#import "SearchViewController.h"
#import "CustomTableViewCell.h"
#import "LiveInfoTrait.h"
#import "TabBarController.h"

@implementation SearchViewController

- (void)viewDidLoad
{
    [super viewDidLoad];
	
	[LiveInfoTrait addTestLiveInfo];
	
	//テーブルの戦闘に検索バーを配置
	self.tableView.tableHeaderView = _searchBar;
	self.items = [LiveInfoTrait traitList];
}

- (void)didReceiveMemoryWarning
{
    [super didReceiveMemoryWarning];
    // Dispose of any resources that can be recreated.
}
@end
