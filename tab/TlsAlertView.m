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

+ (void)showMasterUpdateDialog
{
    TlsAlertView *alertView = [[TlsAlertView alloc] init];
    
    [alertView showNeedUpdateDialog:^(NSInteger index) {
        [alertView showRetryUpdateDialog:^(NSInteger index) {
            if( index == 1 )
            {
                [NetworkDownload downloadFile:^(BOOL isDownload){}];
            }
        }];
    }];
}

- (void)showNeedUpdateDialog:(void(^)(NSInteger))block
{
    SSGentleAlertView *alertView = [[SSGentleAlertView alloc] initWithStyle:SSGentleAlertViewStyleBlack
                                                                      title:@"データ更新"
                                                                    message:@"ライブ情報の更新を行います。"
                                                                      block:block
                                                          cancelButtonTitle:nil
                                                          otherButtonTitles:@"OK", nil];
    [alertView show];
}

- (void)showRetryUpdateDialog:(void(^)(NSInteger))block
{
    SSGentleAlertView *alertView = [[SSGentleAlertView alloc] initWithStyle:SSGentleAlertViewStyleBlack
                                                                      title:@"データ更新"
                                                                    message:@"ライブ情報の更新に失敗しました。\nリトライしますか？\n(ネットワーク環境の良い場所で行ってください。)"
                                                                      block:block
                                                          cancelButtonTitle:@"いいえ"
                                                          otherButtonTitles:@"はい", nil];
    [alertView show];
}

- (void)showDoneUpdateDialog
{
    SSGentleAlertView *alertView = [[SSGentleAlertView alloc] initWithStyle:SSGentleAlertViewStyleBlack
                                                                      title:@"データ更新"
                                                                    message:@"ライブ情報の更新が完了しました。"
                                                                      block:nil
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
