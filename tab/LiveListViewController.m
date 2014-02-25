//
//  ViewController.m
//  tab
//
//  Created by MIYATA Wataru on 2014/02/18.
//  Copyright (c) 2014年 MIYATA Wataru. All rights reserved.
//

#import "LiveListViewController.h"
#import "LiveHouseTrait.h"
#import "LiveInfoTrait.h"
#import "Common.h"
#import "SettingData.h"
#import "CustomTableViewCell.h"

@interface LiveListViewController ()

@end

@implementation LiveListViewController

- (id)initWithTrait:(const LiveHouseTrait *)trait
{
	if( (self = [super init]) )
	{
		_trait = trait;
		[self setTitle:_trait.name];
		[self reloadItems];
	}
	return self;
}

- (void)viewDidLoad
{
    [super viewDidLoad];
	
	CGRect screenFrame = [[UIScreen mainScreen] applicationFrame];
	int height = 540 - screenFrame.size.height;
	if( SYSTEM_VERSION_LESS_THAN(@"7.0") )
	{
		//ios6スクロール調整
		height += 100;
	}
	self.tableView.contentInset				= UIEdgeInsetsMake(0, 0, height, 0);
	self.tableView.scrollIndicatorInsets	= UIEdgeInsetsMake(0, 0, height, 0);
}

- (void)reloadItems
{
	[LiveInfoTrait addTestLiveInfo];
	self.items = [LiveInfoTrait traitList];
	
	[super reloadItems];
}

- (void)didReceiveMemoryWarning
{
    [super didReceiveMemoryWarning];
    // Dispose of any resources that can be recreated.
}

@end
