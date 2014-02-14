//
//  LoadData.m
//  iOS7Sampler
//
//  Created by MIYATA Wataru on 2014/01/31.
//  Copyright (c) 2014年 Shuichi Tsutsumi. All rights reserved.
//

#import "LoadData.h"

@interface LoadData()
@property NSData *data;
@property int readOffset;
@property int version;
@end

@implementation LoadData
//-<NHN>--------------------------------------------------------------------------------------------
// Function : init
// Explain  :
//--------------------------------------------------------------------------------------------<NHN>-
-(id) initWithData:(NSData*)data
{
	return [self initWithData:data offset:0];
}
//-<NHN>--------------------------------------------------------------------------------------------
// Function : init
// Explain  :
//--------------------------------------------------------------------------------------------<NHN>-
-(id) initWithData:(NSData*)data offset:(int)offset
{
	if( (self = [super init]) )
	{
		_readOffset = offset;
		_data = data;
		_version = [self getInt32];
	}
	return self;
}

//-<NHN>--------------------------------------------------------------------------------------------
// Function : getInt16
// Explain  :
//--------------------------------------------------------------------------------------------<NHN>-
-(int16_t) getInt16
{
	if( [self isReadable:sizeof(int16_t)] == NO )
	{
		NSLog(@"getInt16 error");
		return 0;
	}
	
	int16_t value;
	[_data getBytes:&value range:NSMakeRange(_readOffset, sizeof(int16_t))];
	_readOffset += sizeof(int16_t);
	
	return ntohs(value);
}


//-<NHN>--------------------------------------------------------------------------------------------
// Function : getInt32
// Explain  :
//--------------------------------------------------------------------------------------------<NHN>-
-(int32_t) getInt32
{
	if( [self isReadable:sizeof(int32_t)] == NO )
	{
		NSLog(@"getInt32 error");
		return 0;
	}
	
	int32_t value;
	[_data getBytes:&value range:NSMakeRange(_readOffset, sizeof(int32_t))];
	_readOffset += sizeof(int32_t);
	
	return ntohl(value);
}

//-<NHN>--------------------------------------------------------------------------------------------
// Function : getString16
// Explain  :
//--------------------------------------------------------------------------------------------<NHN>-
-(NSString*) getString16
{
	if( [self isReadable:sizeof(int16_t)] == NO )
	{
		NSLog(@"getString16 error");
		return @"";
	}
	
	int16_t value;
	[_data getBytes:&value range:NSMakeRange(_readOffset, sizeof(int16_t))];
	int length = ntohs(value);
	_readOffset += sizeof(int16_t);
	
	if( [self isReadable:length] == NO )
	{
		NSLog(@"getString16 error 2");
		return @"";
	}
	
	NSString* string = [[NSString alloc]initWithData:[_data subdataWithRange:NSMakeRange(_readOffset, length)] encoding:NSUTF8StringEncoding];
	_readOffset += length;
	
	return string;
}

//-<NHN>--------------------------------------------------------------------------------------------
// Function : isReadable
// Explain  :
//--------------------------------------------------------------------------------------------<NHN>-
-(BOOL) isReadable:(int)length
{
	return self && [_data length] >= _readOffset + length;
}
@end
