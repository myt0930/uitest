//
//  Common.m
//  iOS7Sampler
//
//  Created by Wataru Miyata on 2014/02/04.
//  Copyright (c) 2014年 Shuichi Tsutsumi. All rights reserved.
//

#import "Common.h"

@implementation UILabel(Ex)
+ (id)labelWithFontName:(UIFont *)font color:(UIColor *)color
{
    UILabel *label = [[UILabel alloc] init];
    label.textColor = color;
    label.font = font;
    
    return label;
}
@end

@implementation NSAttributedString(Ex)

+ (id)attributedStringWithString:(NSString *)string
						   space:(int)space
					   alignment:(NSTextAlignment)alignment
					   breakmode:(NSLineBreakMode)breakmode
{
	NSMutableParagraphStyle *paragraphStyle = [[NSMutableParagraphStyle alloc] init];
    paragraphStyle.lineSpacing = 2.0f;
	paragraphStyle.alignment = alignment;
	paragraphStyle.lineBreakMode = breakmode;
	paragraphStyle.lineHeightMultiple = 1.1f;
    
	NSDictionary *attributeDic = @{NSParagraphStyleAttributeName:paragraphStyle};
	
    NSAttributedString *repString = [[NSAttributedString alloc] initWithString:string attributes:attributeDic];
	
    return repString;
}
@end