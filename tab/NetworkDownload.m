//
//  NetworkDownload.m
//  tab
//
//  Created by MIYATA Wataru on 2014/03/03.
//  Copyright (c) 2014年 MIYATA Wataru. All rights reserved.
//

#import "NetworkDownload.h"
#import "AFHTTPRequestOperation.h"

#define VERSION_FILE		@"version.bin"
#define VERSION_TEMP_FILE	@"tempversion.bin"
#define MASTER_FILE			@"master.bin"

@implementation NetworkDownload

+ (void)downloadFile:(void(^)(BOOL))block
{
    NSURL *url = [NSURL URLWithString:@"https://s3-ap-northeast-1.amazonaws.com/tokyolive/version.bin"];
    NSURLRequest *request = [NSURLRequest requestWithURL:url];
	
    AFHTTPRequestOperation *operation = [[AFHTTPRequestOperation alloc] initWithRequest:request];
	
    NSString *fullPath = [NSTemporaryDirectory() stringByAppendingPathComponent:[url lastPathComponent]];
	
    [operation setOutputStream:[NSOutputStream outputStreamToFileAtPath:fullPath append:NO]];
	
    [operation setDownloadProgressBlock:^(NSUInteger bytesRead, NSInteger totalBytesRead, NSInteger totalBytesExpectedToRead) {
        NSLog(@"bytesRead: %u, totalBytesRead: %d, totalBytesExpectedToRead: %d", bytesRead, totalBytesRead, totalBytesExpectedToRead);
    }];
	
    [operation setCompletionBlockWithSuccess:^(AFHTTPRequestOperation *operation, id responseObject) {
		
        NSLog(@"RES: %@", [[[operation response] allHeaderFields] description]);
		
        NSError *error;
		[[NSFileManager defaultManager] attributesOfItemAtPath:fullPath error:&error];
        if (error)
		{
			block(NO);
        }
		else
		{
			NSData * data = [[NSData alloc] initWithContentsOfFile:fullPath];
			
			NSData *data4 = [data subdataWithRange:NSMakeRange(0, 4)];
			int version = CFSwapInt32LittleToHost(*(int*)([data4 bytes]));
			
			block(YES);
        }
		
		
    } failure:^(AFHTTPRequestOperation *operation, NSError *error) {
        NSLog(@"ERR: %@", [error description]);
		block(NO);
    }];
	
    [operation start];
}

@end
