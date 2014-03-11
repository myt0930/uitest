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
        NSString *info      = [data getString16];
        int sortNo          = [data getInt16];
		
		LiveHouseTrait *trait = [[LiveHouseTrait alloc] initWithLiveHouseNo:liveHouseNo
																	   name:name
                                                                       info:info
                                                                     sortNo:sortNo];
		[traitList addObject:trait];
	}
    
    //ソート順でソート
	traitList = [NSMutableArray arrayWithArray:
                 [traitList sortedArrayUsingComparator:^NSComparisonResult(const LiveHouseTrait *obj1,
                                                                           const LiveHouseTrait *obj2)
                  {
                      return [[NSNumber numberWithInt:obj1.sortNo] compare:[NSNumber numberWithInt:obj2.sortNo]];
                  }]
                 ];
}

+(NSString *)liveHouseName:(int)liveHouseNo
{
    const LiveHouseTrait *trait = [LiveHouseTrait traitOfLiveHouseNo:liveHouseNo];
    if( trait )
    {
        return trait.name;
    }
    return nil;
}

+ (id)traitOfLiveHouseNo:(int)liveHouseNo
{
    for( const LiveHouseTrait *trait in traitList )
	{
		if( trait.liveHouseNo == liveHouseNo )
		{
			return trait;
		}
	}
	return nil;
}

- (id)initWithLiveHouseNo:(int)liveHouseNo name:(NSString *)name info:(NSString *)info sortNo:(int)sortNo
{
	if( (self = [super init] ) )
	{
		_liveHouseNo	= liveHouseNo;
		_name			= name;
        _info           = info;
		_sortNo			= sortNo;
	}
	return self;
}
@end
