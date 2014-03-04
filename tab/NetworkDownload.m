//
//  NetworkDownload.m
//  tab
//
//  Created by MIYATA Wataru on 2014/03/03.
//  Copyright (c) 2014年 MIYATA Wataru. All rights reserved.
//

#import "NetworkDownload.h"
#import "AFHTTPRequestOperation.h"
#import "Common.h"

#define SERVER_URL			@"https://s3-ap-northeast-1.amazonaws.com/tokyolive/"

#define VERSION_FILE		@"version.bin"
#define VERSION_TEMP_FILE	@"tempversion.bin"
#define MASTER_FILE			@"master.bin"

@implementation NetworkDownload

+ (void)isNeedUpdateMaster:(void(^)(enum MASTER_UPDATE_STATE))isNeedUpdate
{
	NSString *filePath			= [[Common libraryCachesDir] stringByAppendingPathComponent:VERSION_FILE];
	NSFileHandle *fileHandle	= [NSFileHandle fileHandleForReadingAtPath:filePath];
	if (!fileHandle)
	{
		isNeedUpdate(MASTER_UPDATE_CONSTRAINT);			//バージョンファイルが無いときは強制DL
		return;
	}
	
	//Localのバージョンを読み出し
	NSData *localVersionData	= [fileHandle readDataToEndOfFile];
	int localVersion			= CFSwapInt32LittleToHost(*(int*)([localVersionData bytes]));
	
	[self downloadFile:^(NSError *error, NSData *responseData) {
		if( error )
		{
			isNeedUpdate(MASTER_UPDATE_NETWORK_ERROR);	//通信エラー
			return;
		}
		int version	= CFSwapInt32LittleToHost(*(int*)([responseData bytes]));
		
		if( localVersion < version )
		{
			isNeedUpdate(MASTER_UPDATE_NEED);			//更新あり
		}
		else
		{
			isNeedUpdate(MASTER_UPDATE_NONE);			//更新なし
		}
	}];
}

+ (void)downloadMaster:(BOOL)isConstraintDL block:(void(^)(BOOL))block
{
	void(^download)(BOOL) = ^(BOOL isSuccess){
		//マスターDL
		
		//version.binをtempから移動
	};
	if( isConstraintDL )
	{
		//tempにversion.binをDLしてから実行
		
		return;
	}
	
}

+ (void)downloadFile:(void(^)(NSError*,NSData*))block
{
	//まだマスターダウンロードをしていなければ強制DL
	
    NSURL *url				= [NSURL URLWithString:[NSString stringWithFormat:@"%@%@",SERVER_URL, VERSION_TEMP_FILE]];
	NSString *fullPath		= [[Common libraryCachesDir] stringByAppendingPathComponent:[url lastPathComponent]];
	
    NSURLRequest *request				= [NSURLRequest requestWithURL:url];
    AFHTTPRequestOperation *operation	= [[AFHTTPRequestOperation alloc] initWithRequest:request];
    [operation setOutputStream:[NSOutputStream outputStreamToFileAtPath:fullPath append:NO]];
    [operation setCompletionBlockWithSuccess:^(AFHTTPRequestOperation *operation, id responseObject) {
        NSError *error;
		[[NSFileManager defaultManager] attributesOfItemAtPath:fullPath error:&error];
        if (error)
		{
			block(error, nil);
        }
		else
		{
			block(nil, operation.responseData);
        }
		
		NSLog(@"RES: %@", [[[operation response] allHeaderFields] description]);
    } failure:^(AFHTTPRequestOperation *operation, NSError *error) {
		block(error, nil);
		
		NSLog(@"ERR: %@", [error description]);
    }];
	[operation setDownloadProgressBlock:^(NSUInteger bytesRead,
										  NSInteger totalBytesRead,
										  NSInteger totalBytesExpectedToRead) {
        NSLog(@"bytesRead: %u, totalBytesRead: %d, totalBytesExpectedToRead: %d", bytesRead, totalBytesRead, totalBytesExpectedToRead);
    }];
	
    [operation start];
}

@end
