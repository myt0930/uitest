//
//  FavViewController.m
//  tab
//
//  Created by Wataru Miyata on 2014/02/15.
//  Copyright (c) 2014年 MIYATA Wataru. All rights reserved.
//

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
	
    UIView *sectionView = [[UIView alloc] init];
        
	// UIView を戻り値にて返すとセクションに反映される。
	sectionView.backgroundColor = LIST_SECTION_COLOR;
	sectionView.frame = CGRectMake(0.0f, 0.0f, 320.0f, 20.0f);
	
	// UIView にラベルを追加する。
	UILabel *sectionLabel = [[UILabel alloc] initWithFrame:CGRectMake(0.0f, 0.0f, 320.0f, 20.0f)];
	// テキストの色を変更したり・・・
	sectionLabel.textColor = ACT_COLOR;
	sectionLabel.backgroundColor = LIST_SECTION_COLOR;
	// 背景の色を変更したり・・・
	sectionLabel.text = _sectionArray[section];
	// フォント変更ももちろん可能
	sectionLabel.font =  MAKE_HIRAGINO_FONT(14);
	// シャドウカラーを設定することももちろんできます。
	// ビューにセットして
	[sectionView addSubview:sectionLabel];
	// ビューを戻り値で返すとセクションに反映されます。

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
