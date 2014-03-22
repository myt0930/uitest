//
//  LiveInfoTrait.m
//  iOS7Sampler
//
//  Created by MIYATA Wataru on 2014/01/31.
//  Copyright (c) 2014年 Shuichi Tsutsumi. All rights reserved.
//

#import "LiveInfoTrait.h"
#import "LoadData.h"
#import "SettingData.h"
#import "Common.h"

static NSMutableArray* traitList;
static NSDate *minDate;
static NSDate *maxDate;

@implementation LiveInfoTrait

+(void)initialize
{
	traitList = [[NSMutableArray alloc] init];
}

+ (void)removeAllMast
{
	[traitList removeAllObjects];
}

+(NSArray *)traitList
{
	return traitList;
}

+(NSArray *)traitListWithDate:(NSDate *)date
{
    NSString *date1 = [NSString stringWithDateFormat:@"yyyyMMdd" date:date];
    
	NSMutableArray *liveList = [NSMutableArray array];
	for( const LiveInfoTrait *trait in traitList )
	{
        NSString *date2 = [NSString stringWithDateFormat:@"yyyyMMdd" date:trait.liveDate];
        if( [date1 isEqualToString:date2] )
		{
			[liveList addObject:trait];
		}
	}
	
	//ソート順でソート
	NSArray *sortedArray = [liveList sortedArrayUsingComparator:^NSComparisonResult(const LiveInfoTrait *obj1,
																					const LiveInfoTrait *obj2) {
		return [[NSNumber numberWithInt:obj1.sortNo] compare:[NSNumber numberWithInt:obj2.sortNo]];
	}];
	
	return sortedArray;
}

+(NSArray *)traitListWithLiveHouseNo:(int)liveHouseNo
{
	NSString *current = [NSString stringWithDateFormat:@"yyyyMM" date:[NSDate date]];
	NSMutableArray *liveList = [NSMutableArray array];
	for( const LiveInfoTrait *trait in traitList )
	{
		if( trait.liveHouseNo == liveHouseNo && ![trait isPastLive])
		{
			NSString *liveDate = [NSString stringWithDateFormat:@"yyyyMM" date:trait.liveDate];
			if( [liveDate compare:current] != NSOrderedAscending )
			{
				[liveList addObject:trait];
			}
		}
	}
	
	return liveList;
}

+(id)traitOfUniqueID:(NSString *)uniqueID
{
	for( LiveInfoTrait *trait in traitList )
	{
		if( [trait.uniqueID compare:uniqueID] == NSOrderedSame )
		{
			return trait;
		}
	}
	return nil;
}

+(NSDate *)minLiveDate
{
    if( !minDate )
    {
        return [NSDate date];
    }
    return minDate;
}
+(NSDate *)maxLiveDate
{
    if( !maxDate )
    {
        return [NSDate date];
    }
    return maxDate;
}

+ (void)loadMast:(LoadData*)data
{
	//ライブ一覧をクリア
	[self removeAllMast];
    
    minDate = nil;
    maxDate = nil;
	
	int masterCount = [data getInt16];
	
	for( int i = 0;i < masterCount;i++ )
	{
		int liveHouseNo		= [data getInt16];
		NSString *liveDate	= [NSString stringWithFormat:@"%d", [data getInt32]];
		int subNo			= [data getInt16];
		NSString *title		= [data getString16];
		NSString *act		= [data getString16];
		NSString *otherInfo	= [data getString16];
		
		LiveInfoTrait *trait = [[LiveInfoTrait alloc] initWithLiveHouseNo:liveHouseNo
																 liveDate:liveDate
																	subNo:subNo
															   eventTitle:title
																	  act:act
                                                                otherInfo:otherInfo];
		[traitList addObject:trait];
	}
}

-(id)initWithLiveHouseNo:(int)liveHouseNo
				liveDate:(NSString *)liveDate
				   subNo:(int)subNo
			  eventTitle:(NSString *)eventTitle
					 act:(NSString *)act
               otherInfo:(NSString *)otherInfo
{
	if( (self = [super init] ) )
	{
		_liveHouseNo	= liveHouseNo;
		_subNo			= subNo;
		_eventTitle		= eventTitle;
		_act			= act;
		_otherInfo		= otherInfo;
        
        NSDateFormatter *formatter = [Common dateFormatter];
        [formatter setDateFormat:@"yyyyMMdd"];
        _liveDate = [formatter dateFromString:liveDate];
        
        _uniqueID		= [NSString stringWithFormat:@"%@%d%03d", [NSString stringWithDateFormat:@"yyyyMMdd" date:_liveDate], _subNo, _liveHouseNo];
        
        _dayOfWeek      = [_liveDate weekDay];
        
        if( !minDate || [minDate timeIntervalSinceDate:_liveDate] > 0 )
        {
            minDate = _liveDate;
        }
        if( !maxDate || [maxDate timeIntervalSinceDate:_liveDate] < 0 )
        {
            maxDate = _liveDate;
        }
	}
	return self;
}

- (BOOL)isFavorite
{
	return [[SettingData instance] isContainsFavoriteUniqueId:_uniqueID];
}

- (BOOL)isPastLive
{
	NSDate *date = [NSDate dateWithTimeIntervalSinceNow:-24 * 60 * 60];//１日前
	
	if( [_liveDate timeIntervalSinceDate:date] < 0 )
	{
		return YES;
	}
	return NO;
}

@end