//
//  LiveHouseTrait.h
//  tab
//
//  Created by MIYATA Wataru on 2014/02/07.
//  Copyright (c) 2014年 MIYATA Wataru. All rights reserved.
//

#import <Foundation/Foundation.h>

@class LoadData;

@interface LiveHouseTrait : NSObject
@property int		liveHouseNo;
@property NSString	*name;
@property NSString  *info;
@property int		sortNo;

+(NSArray *)traitList;
+ (id)traitOfLiveHouseNo:(int)liveHouseNo;
+(void)loadMast:(LoadData *)data;
+(NSString *)liveHouseName:(int)liveHouseNo;

- (id)initWithLiveHouseNo:(int)liveHouseNo name:(NSString *)name info:(NSString *)info sortNo:(int)sortNo;
@end
