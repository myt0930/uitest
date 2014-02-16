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
}

- (void)didReceiveMemoryWarning
{
    [super didReceiveMemoryWarning];
    // Dispose of any resources that can be recreated.
}

- (UITableViewCell *)tableView:(UITableView *)tableView cellForRowAtIndexPath:(NSIndexPath *)indexPath
{
    static NSString *CellIdentifier = @"customCell";
    
    CustomTableViewCell *cell = [self.tableView dequeueReusableCellWithIdentifier:CellIdentifier];
    
    if (cell == nil) {
        
        cell = [[CustomTableViewCell alloc] initWithStyle:UITableViewCellStyleSubtitle
										  reuseIdentifier:CellIdentifier];
        
        
    }
    
    if( indexPath.row == 0 )
    {
        return cell;
    }
	
    NSLog(@"%d",indexPath.row);
	const LiveInfoTrait *trait = [self.items objectAtIndex:indexPath.row];
	[cell setTextWithTrait:trait];
    
    return cell;
}

- (void) searchItem:(NSString *) searchText {
    NSMutableArray *resultArray = [NSMutableArray array];
    for( const LiveInfoTrait *trait in [LiveInfoTrait traitList] )
    {
        //出演者名検索
        NSRange searchResult = [trait.act rangeOfString:searchText options:NSCaseInsensitiveSearch];
        if(searchResult.location == NSNotFound)
        {
            //イベント名検索
            searchResult = [trait.act rangeOfString:searchText options:NSCaseInsensitiveSearch];
            if(searchResult.location == NSNotFound)
            {
                //出演者、イベント名共に含まれなければcontinue
                continue;
            }
        }
        
        [resultArray addObject:trait];
    }
    
    self.items = resultArray;
    
    [self.tableView performSelectorOnMainThread:@selector(reloadData) withObject:nil waitUntilDone:NO];
}

- (void) searchBarSearchButtonClicked: (UISearchBar *) searchBar {
    [searchBar resignFirstResponder];
    [self searchItem:searchBar.text];
}

- (void) searchBar:(UISearchBar *)searchBar textDidChange:(NSString *) searchText {
    NSLog(@"serch text=%@", searchText);
    if ([searchText length]!=0) {
        // インクリメンタル検索など
    }
}

#pragma mark - UISearchBar Delegate

// テキストフィールド入力開始前に呼ばれる
- (BOOL)searchBarShouldBeginEditing:(UISearchBar *)searchBar
{
    NSLog(@"searchBarShouldBeginEditing");
    
    return YES;
}

// テキストフィールドの入力開始時に呼ばれる
- (void)searchBarTextDidBeginEditing:(UISearchBar *)searchBar
{
    NSLog(@"searchBarTextDidBeginEditing");
}

// テキストフィールドの入力完了前に呼ばれる
- (BOOL)searchBarShouldEndEditing:(UISearchBar *)searchBar
{
    NSLog(@"searchBarShouldEndEditing");
    
    return YES;
}

// テキストフィールドの入力完了後に呼ばれる
- (void)searchBarTextDidEndEditing:(UISearchBar *)searchBar
{
    NSLog(@"searchBarTextDidEndEditing");
}

// UISearchBarのtextFieldでenterを押した時に呼ばれる
- (BOOL)textFieldShouldReturn:(UITextField *)textField {
    
    NSLog(@"textFieldShouldReturn");
    return YES;
}
@end
