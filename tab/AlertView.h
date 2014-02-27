//
//  AlertView.h
//  tab
//
//  Created by MIYATA Wataru on 2014/02/27.
//  Copyright (c) 2014年 MIYATA Wataru. All rights reserved.
//

#import <Foundation/Foundation.h>
#import "SSGentleAlertView.h"

@interface AlertView : NSObject<UIAlertViewDelegate>
@property int alertProgress;
@property (nonatomic, strong, readwrite) void(^blocks)(NSInteger);
- (void)initWithTitle:(NSString *)title
			  message:(NSString *)message
			   blocks:(void(^)(NSInteger))blocks
	cancelButtonTitle:(NSString *)cancelButtonTitle
	otherButtonTitles:(NSString *)otherButtonTitles, ...;
@end
