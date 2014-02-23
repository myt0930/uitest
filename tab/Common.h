//
//  Common.h
//  iOS7Sampler
//
//  Created by Wataru Miyata on 2014/02/04.
//  Copyright (c) 2014年 Shuichi Tsutsumi. All rights reserved.
//

#import <Foundation/Foundation.h>

#define MAKE_HIRAGINO_FONT(x) [UIFont fontWithName:@"HiraKakuProN-W3"size:x]
#define MAKE_HIRAGINO_BOLD_FONT(x) [UIFont fontWithName:@"HiraKakuProN-W6"size:x]
#define MAKE_UICOLOR(r,g,b,a) [UIColor colorWithRed:r/255. green:g/255. blue:b/255. alpha:a]

#define WHITE_COLOR         MAKE_UICOLOR(245, 245, 245, 1)
#define RED_COLOR			MAKE_UICOLOR(238,101,87,1)
#define BLUE_COLOR			MAKE_UICOLOR(22,166,182,1)
#define BLACK_COLOR			MAKE_UICOLOR(24,24,24,1)
#define FAV_COLOR			MAKE_UICOLOR(255,133,28,1)
#define FAV_DISABLE_COLOR	MAKE_UICOLOR(200,200,200,1)
#define LIST_SECTION_COLOR	MAKE_UICOLOR(160,160,160,0.8)

#define SYSTEM_VERSION_EQUAL_TO(v)                  ([[[UIDevice currentDevice] systemVersion] compare:v options:NSNumericSearch] == NSOrderedSame)
#define SYSTEM_VERSION_GREATER_THAN(v)              ([[[UIDevice currentDevice] systemVersion] compare:v options:NSNumericSearch] == NSOrderedDescending)
#define SYSTEM_VERSION_GREATER_THAN_OR_EQUAL_TO(v)  ([[[UIDevice currentDevice] systemVersion] compare:v options:NSNumericSearch] != NSOrderedAscending)
#define SYSTEM_VERSION_LESS_THAN(v)                 ([[[UIDevice currentDevice] systemVersion] compare:v options:NSNumericSearch] == NSOrderedAscending)
#define SYSTEM_VERSION_LESS_THAN_OR_EQUAL_TO(v)     ([[[UIDevice currentDevice] systemVersion] compare:v options:NSNumericSearch] != NSOrderedDescending)



@interface UILabel(Ex)
+ (id)labelWithFontName:(UIFont *)font color:(UIColor *)color;
@end

@interface NSAttributedString(Ex)
+ (id)tlsAttributedStringWithString:(NSString *)string lineSpace:(float)lineSpace;
@end

@interface UIImage(Ex)
+ (UIImage *)imageNamed:(NSString *)name withColor:(UIColor *)color drawAsOverlay:(BOOL)overlay;
@end

@interface NSString(Ex)
+ (NSString*)stringWithDateFormat:(NSString*)format timeZone:(NSTimeZone*)timeZone date:(NSDate*)date;
+ (NSString*)stringWithDateFormat:(NSString*)format date:(NSDate*)date;
@end

@interface NSDateFormatter(Ex)
- (id)initWithGregorianCalendar;
@end