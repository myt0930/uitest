//
//  LiveHouseViewController.m
//  tab
//
//  Created by Wataru Miyata on 2014/02/15.
//  Copyright (c) 2014年 MIYATA Wataru. All rights reserved.
//

#import "LiveHouseViewController.h"
#import "LiveInfoTrait.h"

@interface LiveHouseViewController ()

@end

@implementation LiveHouseViewController

- (void)viewDidLoad
{
    [super viewDidLoad];
	
	//お気に入りを追加
    [LiveInfoTrait addTestLiveInfo];
	self.items = [LiveInfoTrait traitList];
}

- (void)didReceiveMemoryWarning
{
    [super didReceiveMemoryWarning];
    // Dispose of any resources that can be recreated.
}

@end
