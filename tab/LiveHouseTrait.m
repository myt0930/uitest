//
//  LiveHouseTrait.m
//  tab
//
//  Created by MIYATA Wataru on 2014/02/07.
//  Copyright (c) 2014年 MIYATA Wataru. All rights reserved.
//

#import "LiveHouseTrait.h"
#import "LoadData.h"

static NSMutableArray* traitList;

@implementation LiveHouseTrait

+ (void)initialize
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

+ (void)loadMast:(LoadData*)data
{
	//ライブ一覧をクリア
	[self removeAllMast];
	
	int masterCount = [data getInt16];
	
	for( int i = 0;i < masterCount;i++ )
	{
		int liveHouseNo		= [data getInt16];
		NSString *name		= [data getString16];
		
		LiveHouseTrait *trait = [[LiveHouseTrait alloc] initWithLiveHouseNo:liveHouseNo
																	   name:name];
		[traitList addObject:trait];
	}
}

+(NSString *)liveHouseName:(int)liveHouseNo
{
	for( const LiveHouseTrait *trait in traitList )
	{
		if( trait.liveHouseNo == liveHouseNo )
		{
			return trait.name;
		}
	}
	return nil;
}

- (id)initWithLiveHouseNo:(int)liveHouseNo name:(NSString *)name
{
	if( (self = [super init] ) )
	{
		_liveHouseNo	= liveHouseNo;
		_name			= name;
	}
	return self;
}

+ (void)addTestLiveHouseTrait
{
	[traitList removeAllObjects];
	LiveHouseTrait *trait = nil;
	
	trait = [[LiveHouseTrait alloc] initWithLiveHouseNo:1 name:@"新宿Motion"];
	[traitList addObject:trait];
	
	trait = [[LiveHouseTrait alloc] initWithLiveHouseNo:2 name:@"秋葉原GOODMAN"];
	[traitList addObject:trait];
	
	trait = [[LiveHouseTrait alloc] initWithLiveHouseNo:3 name:@"下北沢BASEMENT BAR"];
	[traitList addObject:trait];
	
	trait = [[LiveHouseTrait alloc] initWithLiveHouseNo:4 name:@"下北沢THREE"];
	[traitList addObject:trait];
	
	trait = [[LiveHouseTrait alloc] initWithLiveHouseNo:5 name:@"下北沢DAISY BAR"];
	[traitList addObject:trait];
	
	trait = [[LiveHouseTrait alloc] initWithLiveHouseNo:6 name:@"下北沢SHELTER"];
	[traitList addObject:trait];
	
	trait = [[LiveHouseTrait alloc] initWithLiveHouseNo:7 name:@"下北沢QUE"];
	[traitList addObject:trait];
	
	trait = [[LiveHouseTrait alloc] initWithLiveHouseNo:8 name:@"下北沢251"];
	[traitList addObject:trait];
}
@end
