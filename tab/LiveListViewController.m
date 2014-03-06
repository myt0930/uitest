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

@interface LiveListViewController ()

@end

@implementation LiveListViewController

- (id)initWithTrait:(const LiveHouseTrait *)trait
{
	if( (self = [super init]) )
	{
		_trait = trait;
		[self setTitle:_trait.name];
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
		height += 150;
	}
	self.tableView.contentInset				= UIEdgeInsetsMake(0, 0, height, 0);
	self.tableView.scrollIndicatorInsets	= UIEdgeInsetsMake(0, 0, height, 0);
	
	// UIActivityIndicatorViewのインスタンス化
	_indicator = [[UIActivityIndicatorView alloc]initWithFrame:CGRectMake(0, 0, 100, 100)];
	_indicator.center = self.view.center;
	_indicator.activityIndicatorViewStyle = UIActivityIndicatorViewStyleGray;
	[self.view addSubview:_indicator];
	
	[_indicator startAnimating];
	[self performBlockInBackground:^{
		self.items = [LiveInfoTrait traitList];
		[super reloadItems];
		[_indicator stopAnimating];
	}];
}

- (void)didReceiveMemoryWarning
{
    [super didReceiveMemoryWarning];
    // Dispose of any resources that can be recreated.
}

@end
