//
//  SectionBaseViewController.m
//  tab
//
//  Created by MIYATA Wataru on 2014/02/25.
//  Copyright (c) 2014年 MIYATA Wataru. All rights reserved.
//

#import "SectionBaseViewController.h"
#import "Common.h"
#import "CustomTableViewCell.h"
#import "LiveInfoTrait.h"
#import "SettingData.h"
#import "DetailViewController.h"

@interface SectionBaseViewController ()

@end

@implementation SectionBaseViewController

- (id)initWithNibName:(NSString *)nibNameOrNil bundle:(NSBundle *)nibBundleOrNil
{
    self = [super initWithNibName:nibNameOrNil bundle:nibBundleOrNil];
    if (self) {
        // Custom initialization
    }
    return self;
}

- (void)viewDidLoad
{
    [super viewDidLoad];
	// Do any additional setup after loading the view.
}

- (void)reloadItems
{
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
            if( ![section isEqualToString:@""] )
            {
                [array addObject:section];
            }
		}
    }
	NSArray *sectionArray = [array sortedArrayUsingComparator:^NSComparisonResult(id obj1, id obj2) {
		return [obj1 compare:obj2];
	}];
	
	[_rowCountArray removeAllObjects];
	for( NSString *sectionName in sectionArray )
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
    
    _sectionArray = sectionArray;
	
	[self.tableView reloadData];
}

- (void)clearItems
{
	if( _rowCountArray )
	{
		[_rowCountArray removeAllObjects];
	}
	if( _sectionArray )
	{
		_sectionArray = nil;
	}
	self.items = [NSArray array];
	
	[self.tableView reloadData];
}

- (NSInteger)numberOfSectionsInTableView:(UITableView *)tableView
{
	NSLog(@"sectionCount :: %d",[_sectionArray count]);
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
	sectionLabel.backgroundColor = LIST_SECTION_COLOR;
	sectionLabel.text = _sectionArray[section];
	sectionLabel.font =  MAKE_HIRAGINO_BOLD_FONT(14);
	[sectionView addSubview:sectionLabel];
    return sectionView;
}

- (void)tableView:(UITableView *)tableView didSelectRowAtIndexPath:(NSIndexPath *)indexPath
{
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
	
	//詳細view表示
	DetailViewController *instance = [[DetailViewController alloc] initWithLiveInfoTrait:trait baseController:self];
	[self.navigationController pushViewController:instance
										 animated:YES];
    
    [tableView deselectRowAtIndexPath:indexPath animated:YES];
}


- (UITableViewCell *)tableView:(UITableView *)tableView cellForRowAtIndexPath:(NSIndexPath *)indexPath
{
    static NSString *CellIdentifier = @"customCell";
    
    CustomTableViewCell *cell = [self.tableView dequeueReusableCellWithIdentifier:CellIdentifier];
    
    if (cell == nil) {
        
        cell = [[CustomTableViewCell alloc] initWithStyle:UITableViewCellStyleSubtitle
										  reuseIdentifier:CellIdentifier];
    }
	
	return cell;
}

- (void)tableView:(UITableView *)tableView willDisplayCell:(UITableViewCell *)cell forRowAtIndexPath:(NSIndexPath *)indexPath
{
	CustomTableViewCell *customcell = (CustomTableViewCell*)cell;
	cell.backgroundColor = WHITE_COLOR;
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
	[customcell setTextWithTrait:trait];
	
	[customcell updateConstraints];
}

@end
