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
	
	[LiveInfoTrait addTestLiveInfo];
	self.items = [LiveInfoTrait traitList];
}

- (void)didReceiveMemoryWarning
{
    [super didReceiveMemoryWarning];
    // Dispose of any resources that can be recreated.
}

@end
