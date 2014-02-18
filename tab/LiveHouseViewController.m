//
//  LiveHouseViewController.m
//  tab
//
//  Created by Wataru Miyata on 2014/02/15.
//  Copyright (c) 2014年 MIYATA Wataru. All rights reserved.
//

#import "LiveHouseViewController.h"
#import "LiveInfoTrait.h"
#import "LiveHouseTrait.h"
#import "HomeViewController.h"

@interface LiveHouseViewController ()

@end

@implementation LiveHouseViewController

- (void)viewDidLoad
{
    [super viewDidLoad];
	
	//お気に入りを追加
    [LiveHouseTrait addTestLiveHouseTrait];
	self.items = [LiveHouseTrait traitList];
}

- (void)didReceiveMemoryWarning
{
    [super didReceiveMemoryWarning];
    // Dispose of any resources that can be recreated.
}

// =============================================================================
#pragma mark - UITableViewDataSource

- (NSInteger)numberOfSectionsInTableView:(UITableView *)tableView {
    
    return 1;
}

- (NSInteger)tableView:(UITableView *)tableView numberOfRowsInSection:(NSInteger)section {
    
    return [self.items count];
}

- (UITableViewCell *)tableView:(UITableView *)tableView cellForRowAtIndexPath:(NSIndexPath *)indexPath
{
    static NSString *CellIdentifier = @"Cell";
    
	UITableViewCell *cell = [self.tableView dequeueReusableCellWithIdentifier:CellIdentifier];
    
    if (cell == nil) {
        
        cell = [[UITableViewCell alloc] initWithStyle:UITableViewCellStyleSubtitle
										  reuseIdentifier:CellIdentifier];
        
        
    }
	
	const LiveHouseTrait *trait = [self.items objectAtIndex:indexPath.row];
	cell.textLabel.font = [UIFont fontWithName:@"HiraKakuProN-W6" size:16];
	cell.textLabel.text = trait.name;
    
    return cell;
}

- (void)tableView:(UITableView *)tableView didSelectRowAtIndexPath:(NSIndexPath *)indexPath
{
	LiveInfoTrait *trait = self.items[indexPath.row];
	
	//詳細view表示
	HomeViewController *instance = [[HomeViewController alloc] init];
	[self.navigationController pushViewController:instance
										 animated:YES];
    
    [tableView deselectRowAtIndexPath:indexPath animated:YES];
}

// =============================================================================
#pragma mark - UITableViewDelegate

- (CGFloat)tableView:(UITableView *)tableView heightForRowAtIndexPath:(NSIndexPath *)indexPath
{
    return 30;
}

@end
