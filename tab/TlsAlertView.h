//
//  AlertView.h
//  tab
//
//  Created by MIYATA Wataru on 2014/02/27.
//  Copyright (c) 2014年 MIYATA Wataru. All rights reserved.
//

#import <Foundation/Foundation.h>
#import "SSGentleAlertView.h"

enum ALERT_BUTTON_TYPE
{
    ALERT_BUTTON_OK = 0,
    ALERT_BUTTON_YESNO,
};

@interface TlsAlertView : NSObject
@property int alertProgress;
@property SSGentleAlertView *alert;

+ (id)dialogWithTitle:(NSString *)title
              message:(NSString *)message
           buttonType:(enum ALERT_BUTTON_TYPE)type
                block:(void(^)(NSInteger))block;

+ (void)showNeedUpdateDialog:(void(^)(NSInteger))block;
+ (void)showRetryUpdateDialog:(BOOL)isFirstLaunch block:(void(^)(NSInteger))block;
+ (void)showDoneUpdateDialog:(void(^)(NSInteger))block;
+ (void)showAppUpdateDialog:(void(^)(NSInteger))block;
+ (void)showAppRecommendDialog:(void(^)(NSInteger))block;
@end
