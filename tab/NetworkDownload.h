//
//  NetworkDownload.h
//  tab
//
//  Created by MIYATA Wataru on 2014/03/03.
//  Copyright (c) 2014年 MIYATA Wataru. All rights reserved.
//

#import <Foundation/Foundation.h>

@interface NetworkDownload : NSObject
+ (void)downloadFile:(void(^)(BOOL))block;
@end
