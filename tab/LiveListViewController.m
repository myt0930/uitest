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
#import "SettingData.h"
#import "CustomTableViewCell.h"

@interface LiveListViewController ()

@end

@implementation LiveListViewController

- (id)initWithTrait:(const LiveHouseTrait *)trait
{
	if( (self = [super init]) )
	{
		_trait = trait;
		[self setTitle:_trait.name];
		[self reloadItems];
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
		height += 100;
	}
	self.tableView.contentInset				= UIEdgeInsetsMake(0, 0, height, 0);
	self.tableView.scrollIndicatorInsets	= UIEdgeInsetsMake(0, 0, height, 0);
}

- (void)reloadItems
{
	[LiveInfoTrait addTestLiveInfo];
	self.items = [LiveInfoTrait traitList];
	
	if( !_rowCountArray )
	{
		_rowCountArray = [NSMutableArray array];
	}
	
    NSMutableArray *array = [NSMutableArray array];
    for( const LiveInfoTrait *trait in self.items )
    {
		NSString *section = [NSString stringWithDateFormat:@"yyyy/MM" date:trait.liveDate];
		if( ![array containsObject:section] )
		{
			[array addObject:section];
		}
    }
	_sectionArray = [array sortedArrayUsingComparator:^NSComparisonResult(id obj1, id obj2) {
		return [obj1 compare:obj2];
	}];
	
	[_rowCountArray removeAllObjects];
	for( NSString *sectionName in _sectionArray )
	{
		int count = 0;
		for( const LiveInfoTrait *trait in self.items )
		{
			NSString *date = [NSString stringWithDateFormat:@"yyyy/MM" date:trait.liveDate];
			if( [date isEqualToString:sectionName] )
			{
				count++;
			}
		}
		[_rowCountArray addObject:[NSNumber numberWithInt:count]];
	}
	
	[self.tableView reloadData];
}

- (void)didReceiveMemoryWarning
{
    [super didReceiveMemoryWarning];
    // Dispose of any resources that can be recreated.
}

- (NSInteger)numberOfSectionsInTableView:(UITableView *)tableView
{
    return [_sectionArray count];
}

- (NSString *)tableView:(UITableView *)tableView titleForHeaderInSection:(NSInteger)section
{
    return [_sectionArray objectAtIndex:section];
}

- (NSInteger)tableView:(UITableView *)tableView numberOfRowsInSection:(NSInteger)section {
    return [[_rowCountArray objectAtIndex:section] integerValue];
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
	
	[cell setTextWithTrait:trait];
	
	return cell;
}

@end
