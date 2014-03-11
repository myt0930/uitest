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
	NSMutableArray *liveList = [NSMutableArray array];
	for( LiveInfoTrait *trait in traitList )
	{
		if( trait.liveHouseNo == liveHouseNo )
		{
			[liveList addObject:trait];
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
		NSString *open		= [data getString16];
		NSString *start		= [data getString16];
		int advanceTicket	= [data getInt16];
		int todayTicket		= [data getInt16];
		
		LiveInfoTrait *trait = [[LiveInfoTrait alloc] initWithLiveHouseNo:liveHouseNo
																 liveDate:liveDate
																	subNo:subNo
															   eventTitle:title
																	  act:act
																 openTime:open
																startTime:start
															advanceTicket:advanceTicket
															  todayTicket:todayTicket];
		[traitList addObject:trait];
	}
}

-(id)initWithLiveHouseNo:(int)liveHouseNo
				liveDate:(NSString *)liveDate
				   subNo:(int)subNo
			  eventTitle:(NSString *)eventTitle
					 act:(NSString *)act
				openTime:(NSString *)openTime
			   startTime:(NSString *)startTime
		   advanceTicket:(int)advanceTicket
			 todayTicket:(int)todayTicket
{
	if( (self = [super init] ) )
	{
		_liveHouseNo	= liveHouseNo;
		_subNo			= subNo;
		_eventTitle		= eventTitle;
		_act			= act;
		_openTime		= openTime;
		_startTime		= startTime;
		_advanceTicket	= advanceTicket;
		_todayTicket	= todayTicket;
		_sortNo			= liveHouseNo;
        
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

@end