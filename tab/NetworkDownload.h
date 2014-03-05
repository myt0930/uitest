//
//  NetworkDownload.h
//  tab
//
//  Created by MIYATA Wataru on 2014/03/03.
//  Copyright (c) 2014年 MIYATA Wataru. All rights reserved.
//

#import <Foundation/Foundation.h>

enum MASTER_UPDATE_STATE
{
	MASTER_UPDATE_NETWORK_ERROR = -1,	//通信エラー
	MASTER_UPDATE_NONE = 0,				//アップデートなし
	MASTER_UPDATE_NEED,					//アップデートあり
	MASTER_UPDATE_CONSTRAINT,			//強制アップデート
};

@interface NetworkDownload : NSObject
+ (void)downloadFile:(NSString*)fileName saveTo:(NSString*)saveTo block:(void(^)(NSError*,NSData*))block;
@end
