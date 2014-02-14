//
//  LoadData.h
//  iOS7Sampler
//
//  Created by MIYATA Wataru on 2014/01/31.
//  Copyright (c) 2014年 Shuichi Tsutsumi. All rights reserved.
//

#import <Foundation/Foundation.h>

@interface LoadData : NSObject
-(id) initWithData:(NSData*)_data;
-(id) initWithData:(NSData*)_data offset:(int)readOffset;

-(int16_t) getInt16;
-(int32_t) getInt32;
-(NSString*) getString16;
@end
