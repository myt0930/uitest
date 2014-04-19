//
//  AppDelegate.h
//  tab
//
//  Created by MIYATA Wataru on 2014/02/05.
//  Copyright (c) 2014年 MIYATA Wataru. All rights reserved.
//

#import <UIKit/UIKit.h>
#import <FelloPush/KonectNotificationsAPI.h>

@class TlsIndicatorView;

@interface AppDelegate : UIResponder <UIApplicationDelegate,IKonectNotificationsCallback>

@property (strong, nonatomic) UIWindow *window;
@property TlsIndicatorView *indicator;
@property BOOL isResume;
@property int showrecommendAppRate;
@end
