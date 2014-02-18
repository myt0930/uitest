//
//  BaseViewController.m
//  tab
//
//  Created by Wataru Miyata on 2014/02/15.
//  Copyright (c) 2014年 MIYATA Wataru. All rights reserved.
//

#import "BaseViewController.h"
#import "CustomTableViewCell.h"
#import "Common.h"
#import "DetailViewController.h"

@implementation BaseViewController

- (void)viewDidLoad
{
    [super viewDidLoad];
	
	UINib *nib = [UINib nibWithNibName:@"CustomTableViewCell" bundle:nil];
	[self.tableView registerNib:nib forCellReuseIdentifier:@"customCell"];
	
	_tableView.dataSource = self;
	_tableView.delegate = self;
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
    static NSString *CellIdentifier = @"customCell";
    
    CustomTableViewCell *cell = [self.tableView dequeueReusableCellWithIdentifier:CellIdentifier];
    
    if (cell == nil) {
        
        cell = [[CustomTableViewCell alloc] initWithStyle:UITableViewCellStyleSubtitle
										  reuseIdentifier:CellIdentifier];
        
        
    }
	
	const LiveInfoTrait *trait = [self.items objectAtIndex:indexPath.row];
	[cell setTextWithTrait:trait];
    
    return cell;
}

// =============================================================================
#pragma mark - UITableViewDelegate

- (CGFloat)tableView:(UITableView *)tableView heightForRowAtIndexPath:(NSIndexPath *)indexPath
{
    return CELL_HEIGHT;
}

- (void)tableView:(UITableView *)tableView didSelectRowAtIndexPath:(NSIndexPath *)indexPath
{
	LiveInfoTrait *trait = self.items[indexPath.row];
	
	//詳細view表示
	DetailViewController *instance = [[DetailViewController alloc] initWithLiveInfoTrait:trait];
	[self.navigationController pushViewController:instance
										 animated:YES];
    
    [tableView deselectRowAtIndexPath:indexPath animated:YES];
}

- (void) tableView:(UITableView *)tableView willDisplayCell:(UITableViewCell *)cell forRowAtIndexPath:(NSIndexPath *)indexPath
{
    cell.backgroundColor = BACKGROUND_COLOR;
}

#pragma mark - MyTabBarControllerDelegate
- (void) didSelect:(TabBarController *)tabBarController {
    NSIndexPath *index = [NSIndexPath indexPathForRow:0 inSection:0];
	[_tableView scrollToRowAtIndexPath:index atScrollPosition:UITableViewScrollPositionNone animated:NO];
}
@end