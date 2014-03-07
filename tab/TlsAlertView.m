//
//  AlertView.m
//  tab
//
//  Created by MIYATA Wataru on 2014/02/27.
//  Copyright (c) 2014年 MIYATA Wataru. All rights reserved.
//

#import "TlsAlertView.h"
#import "SSGentleAlertView.h"
#import "Common.h"
#import "NetworkDownload.h"

enum UPDATE_STATE
{
	UPDATE_STATE_DL = 0,
	UPDATE_STATE_RETRY,
	UPDATE_STATE_DONE,
};

@implementation TlsAlertView

+ (id)dialogWithTitle:(NSString *)title
			  message:(NSString *)message
           buttonType:(enum ALERT_BUTTON_TYPE)type
                block:(void(^)(NSInteger))block;
{
    return [[TlsAlertView alloc] initWithTitle:title message:message buttonType:type block:block];
}

+ (void)showNeedUpdateDialog:(void(^)(NSInteger))block
{
    SSGentleAlertView *alertView = [[SSGentleAlertView alloc] initWithStyle:SSGentleAlertViewStyleBlack
                                                                      title:@"データ更新"
                                                                    message:@"ライブ情報の更新を行います。"
                                                                      block:block
                                                          cancelButtonTitle:nil
                                                          otherButtonTitles:@"OK", nil];
    [alertView show];
}

+ (void)showRetryUpdateDialog:(BOOL)isFirstLaunch block:(void(^)(NSInteger))block
{
	NSString *message = isFirstLaunch ?
						@"ライブ情報の更新に失敗しました。ネットワーク環境の良い場所でリトライして下さい。" :
						@"ライブ情報の更新に失敗しました。\nリトライしますか？\n(ネットワーク環境の良い場所で行ってください。)";
	
	
    SSGentleAlertView *alertView = [[SSGentleAlertView alloc] initWithStyle:SSGentleAlertViewStyleBlack
                                                                      title:@"データ更新"
                                                                    message:message
                                                                      block:block
                                                          cancelButtonTitle:isFirstLaunch ? nil : @"後で"
                                                          otherButtonTitles:@"リトライ", nil];
    [alertView show];
}

+ (void)showDoneUpdateDialog:(void(^)(NSInteger))block
{
    SSGentleAlertView *alertView = [[SSGentleAlertView alloc] initWithStyle:SSGentleAlertViewStyleBlack
                                                                      title:@"データ更新"
                                                                    message:@"ライブ情報の更新が完了しました。"
                                                                      block:block
                                                          cancelButtonTitle:nil
                                                          otherButtonTitles:@"OK", nil];
    [alertView show];
}

- (id)initWithTitle:(NSString *)title
			message:(NSString *)message
         buttonType:(enum ALERT_BUTTON_TYPE)type
              block:(void(^)(NSInteger))block
{
    if( (self = [super init] ))
    {
        NSString *cancelButton  = nil;
        NSString *otherButton   = nil;
        
        switch (type) {
            case ALERT_BUTTON_OK:
                cancelButton    = nil;
                otherButton     = @"OK";
                break;
            case ALERT_BUTTON_YESNO:
                cancelButton    = @"いいえ";
                otherButton     = @"はい";
                break;
        }
        
        _alert = [[SSGentleAlertView alloc] initWithStyle:SSGentleAlertViewStyleBlack
													title:title
												  message:message
													block:(void(^)(NSInteger))block
										cancelButtonTitle:cancelButton
										otherButtonTitles:otherButton, nil];
        _alert.cancelButtonIndex = 0;
        [_alert show];
    }
    return self;
}

@end
