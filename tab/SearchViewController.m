//
//  SecondViewController.m
//  tab
//
//  Created by MIYATA Wataru on 2014/02/05.
//  Copyright (c) 2014年 MIYATA Wataru. All rights reserved.
//

#import "SearchViewController.h"
#import "LiveInfoTrait.h"
#import "DetailViewController.h"
#import "Common.h"

@implementation SearchViewController
- (void)viewDidLoad
{
    [super viewDidLoad];
	
	if( SYSTEM_VERSION_LESS_THAN(@"7.0") )
	{
		self.tableView.tableHeaderView = _searchBar;
	}
	else
	{
		_searchController.displaysSearchBarInNavigationBar = YES;
	}
    UIBarButtonItem *backButton = [[UIBarButtonItem alloc] init];
	backButton.title = @"Back";
	[self.navigationItem setBackBarButtonItem:backButton];
	
	// UIActivityIndicatorViewのインスタンス化
	_indicator = [[UIActivityIndicatorView alloc]initWithFrame:CGRectMake(0, 0, 100, 100)];
	_indicator.center = CGPointMake(160,200);
	_indicator.activityIndicatorViewStyle = UIActivityIndicatorViewStyleGray;
	[self.searchDisplayController.searchResultsTableView addSubview:_indicator];
    
	[LiveInfoTrait addTestLiveInfo];
}

- (void)didReceiveMemoryWarning
{
    [super didReceiveMemoryWarning];
    // Dispose of any resources that can be recreated.
}

- (void)reloadTable
{
	[_searchController.searchResultsTableView reloadData];
}

- (BOOL)searchDisplayController:(UISearchDisplayController*)controller shouldReloadTableForSearchString:(NSString*)searchString
{
	searchString = [searchString lowercaseString];
	
	//インジケーターON
	[_indicator startAnimating];
	
	//バックグラウンドで検索結果の更新処理
	[self performBlockInBackground:^{
		NSMutableArray *searchItems = [NSMutableArray array];
		
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
			
			[searchItems addObject:trait];
		}
		self.items = searchItems;
		[super reloadItems];
		
		[_searchController.searchResultsTableView reloadData];
		
		//インジケーターOFF
		[_indicator stopAnimating];
	}];
	return NO;
}

- (void)searchBarCancelButtonClicked:(UISearchBar *)searchBar
{
	[self clearItems];
}

// =============================================================================
#pragma mark - BaseViewController
- (void)tableView:(UITableView *)tableView didSelectRowAtIndexPath:(NSIndexPath *)indexPath {
	NSString *sectionName = [self.sectionArray objectAtIndex:indexPath.section];
	
    NSMutableArray *array = [NSMutableArray array];
    for( const LiveInfoTrait *trait in self.items )
    {
        NSString *date = [NSString stringWithDateFormat:@"yyyy/MM" date:trait.liveDate];
        if( [date isEqualToString:sectionName] )
        {
            [array addObject:trait];
        }
    }
	
	LiveInfoTrait *trait = [array objectAtIndex:indexPath.row];
	
	//詳細view表示
	DetailViewController *instance = [[DetailViewController alloc] initWithLiveInfoTrait:trait baseController:self];
	[self.navigationController pushViewController:instance
										 animated:YES];
    
    [tableView deselectRowAtIndexPath:indexPath animated:YES];
}

#pragma mark - MyTabBarControllerDelegate
- (void) didSelect:(TabBarController *)tabBarController
{

}

@end
