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


@interface UILabel(Ex)
+ (id)labelWithFontName:(UIFont *)font color:(UIColor *)color;
@end

@interface NSAttributedString(Ex)
+ (id)attributedStringWithString:(NSString *)string space:(int)space alignment:(NSTextAlignment)alignment breakmode:(NSLineBreakMode)breakmode;
@end