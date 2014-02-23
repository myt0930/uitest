//
//  FavViewController.m
//  tab
//
//  Created by Wataru Miyata on 2014/02/15.
//  Copyright (c) 2014年 MIYATA Wataru. All rights reserved.
//

#import <QuartzCore/QuartzCore.h>
#import "FavViewController.h"
#import "LiveInfoTrait.h"
#import "SettingData.h"
#import "CustomTableViewCell.h"
#import "Common.h"

@interface FavViewController ()

@end

@implementation FavViewController

- (void)viewDidLoad
{
    [super viewDidLoad];
}

- (void)didReceiveMemoryWarning
{
    [super didReceiveMemoryWarning];
    // Dispose of any resources that can be recreated.
}

- (void)reloadFavItems
{
    NSMutableArray *favArray = [NSMutableArray array];
    NSArray *saveUniqueIdArray = [SettingData instance].favoriteLiveArray;
    for( NSString *uniqueId in saveUniqueIdArray )
    {
        const LiveInfoTrait *trait = [LiveInfoTrait traitOfUniqueID:uniqueId];
        if( trait )
        {
            [favArray addObject:trait];
        }
    }
	self.items = favArray;
    [self.tableView reloadData];
	
	_sectionArray = @[@"2014/02",@"2014/03"];
	
	NSArray *datas = [NSArray arrayWithObjects:self.items, self.items, nil];
	_dataSource = [NSDictionary dictionaryWithObjects:datas forKeys:_sectionArray];
	
}

- (NSInteger)numberOfSectionsInTableView:(UITableView *)tableView
{
    return [_sectionArray count];
}

- (NSString *)tableView:(UITableView *)tableView titleForHeaderInSection:(NSInteger)section
{
    return [_sectionArray objectAtIndex:section];
}

- (UIView *)tableView:(UITableView *)tableView viewForHeaderInSection:(NSInteger)section {
	
    if( SYSTEM_VERSION_LESS_THAN(@"7.0") )
    {
        // iOS7.0より前の端末ではデフォルトのセクションを表示
        return nil;
    }
    
    UIView *sectionView = [[UIView alloc] init];
	sectionView.backgroundColor = BLACK_COLOR;
	sectionView.frame = CGRectMake(0.0f, 0.0f, 320.0f, 20.0f);
	
	UILabel *sectionLabel = [[UILabel alloc] initWithFrame:CGRectMake(0.0f, 0.0f, 320.0f, 20.0f)];
	sectionLabel.textColor = WHITE_COLOR;
	sectionLabel.backgroundColor = BLACK_COLOR;
	sectionLabel.text = _sectionArray[section];
	sectionLabel.font =  MAKE_HIRAGINO_BOLD_FONT(14);
	[sectionView addSubview:sectionLabel];
    return sectionView;
}

- (UITableViewCell *)tableView:(UITableView *)tableView cellForRowAtIndexPath:(NSIndexPath *)indexPath
{
    static NSString *CellIdentifier = @"customCell";
    
    CustomTableViewCell *cell = [self.tableView dequeueReusableCellWithIdentifier:CellIdentifier];
    
    if (cell == nil) {
        
        cell = [[CustomTableViewCell alloc] initWithStyle:UITableViewCellStyleSubtitle
										  reuseIdentifier:CellIdentifier];
        
        
    }
	NSString *sectionName = [_sectionArray objectAtIndex:indexPath.section];
	
    // セクション名をキーにしてそのセクションの項目をすべて取得
    NSArray *items = [_dataSource objectForKey:sectionName];
	LiveInfoTrait *trait = [items objectAtIndex:indexPath.row];

	[cell setTextWithTrait:trait];
	return cell;
}

#pragma mark - MyTabBarControllerDelegate
- (void) didSelect:(TabBarController *)tabBarController {
	[super didSelect:tabBarController];
    [self reloadFavItems];
}
@end
