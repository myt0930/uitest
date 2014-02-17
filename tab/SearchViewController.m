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
#import "DetailViewController.h"

@implementation SearchViewController

- (void)viewDidLoad
{
//    _searchController.displaysSearchBarInNavigationBar = YES;
    [super viewDidLoad];
	
    
    _searchItems = [NSMutableArray array];
    
	[LiveInfoTrait addTestLiveInfo];
	
	//テーブルの戦闘に検索バーを配置
	self.tableView.tableHeaderView = _searchBar;
}

- (void)didReceiveMemoryWarning
{
    [super didReceiveMemoryWarning];
    // Dispose of any resources that can be recreated.
}

#pragma mark UITableView
- (NSInteger)tableView:(UITableView *)tableView numberOfRowsInSection:(NSInteger)section
{
    if (tableView == self.searchDisplayController.searchResultsTableView)
    {
        return [_searchItems count];
    }
    else
    {
        return 0;
    }
}

- (UITableViewCell *)tableView:(UITableView *)tableView cellForRowAtIndexPath:(NSIndexPath *)indexPath
{
    static NSString *CellIdentifier = @"customCell";
    
    CustomTableViewCell *cell = [self.tableView dequeueReusableCellWithIdentifier:CellIdentifier];
    
    if (cell == nil) {
        
        cell = [[CustomTableViewCell alloc] initWithStyle:UITableViewCellStyleSubtitle
										  reuseIdentifier:CellIdentifier];
        
        
    }
    
    if(tableView == self.searchDisplayController.searchResultsTableView)
    {
        const LiveInfoTrait *trait = [_searchItems objectAtIndex:indexPath.row];
        [cell setTextWithTrait:trait];
    }
    
    return cell;
}

- (BOOL)searchDisplayController:(UISearchDisplayController*)controller shouldReloadTableForSearchString:(NSString*)searchString
{
    BOOL ret = YES; //検索結果を更新するときはYESを返す
    searchString = [searchString lowercaseString];
 
    NSLog(@"%@", searchString);
    
    [_searchItems removeAllObjects];
    
    for( const LiveInfoTrait *trait in [LiveInfoTrait traitList] )
    {
        //出演者名検索
        NSString *act = [trait.act lowercaseString];
        if( [act rangeOfString:searchString].location == NSNotFound )
        {
            //イベント名検索
            NSString *title = [trait.eventTitle lowercaseString];
            if( [title rangeOfString:searchString].location == NSNotFound )
            {
                //イベント名、出演者名に含まれない時はcontinue
                continue;
            }
        }
        
        [_searchItems addObject:trait];
    }
    
    return ret; 
}

// =============================================================================
#pragma mark - BaseViewController
- (void)tableView:(UITableView *)tableView didSelectRowAtIndexPath:(NSIndexPath *)indexPath {
	
    LiveInfoTrait *trait = _searchItems[indexPath.row];
    NSString *className = [@"Detail" stringByAppendingString:@"ViewController"];
    
    if (NSClassFromString(className)) {
		
        id instance = [[DetailViewController alloc] initWithLiveInfoTrait:trait];
        
        if ([instance isKindOfClass:[UIViewController class]]) {
            
            [(UIViewController *)instance setTitle:trait.liveDate];
            [self.navigationController pushViewController:(UIViewController *)instance
                                                 animated:YES];
        }
    }
    
    [tableView deselectRowAtIndexPath:indexPath animated:YES];
}

#pragma mark - MyTabBarControllerDelegate
- (void) didSelect:(TabBarController *)tabBarController
{

}

@end
