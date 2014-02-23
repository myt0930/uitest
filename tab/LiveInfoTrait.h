//
//  LiveInfoTrait.h
//  iOS7Sampler
//
//  Created by MIYATA Wataru on 2014/01/31.
//  Copyright (c) 2014年 Shuichi Tsutsumi. All rights reserved.
//

#import <Foundation/Foundation.h>

@class LoadData;

@interface LiveInfoTrait : NSObject
@property int		liveHouseNo;	//会場ID
@property NSString  *liveDate;		//日程
@property int		subNo;			//サブNo
@property NSString  *eventTitle;		//タイトル
@property NSString  *act;			//出演者
@property NSString  *openTime;		//開場時間
@property NSString  *startTime;		//開始時間
@property int		advanceTicket;	//前売価格
@property int		todayTicket;	//当日価格
@property NSString  *uniqueID;		//ユニークID
@property NSString  *dayOfWeek;     //曜日

+(void)loadMast:(LoadData *)data;

+(NSArray *)traitList;
+(NSArray *)traitListWithDate:(NSString *)date;
+(NSArray *)traitListWithLiveHouseNo:(int)liveHouseNo;
+(id)traitOfLiveHouseNo:(int)liveHouseNo liveDate:(NSString *)liveDate;
+(id)traitOfUniqueID:(NSString *)uniqueID;

//debug method
+(void)addTestLiveInfo;

- (BOOL)isFavorite;
@end
