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

+ (id)tlsAttributedStringWithString:(NSString *)string lineSpace:(float)lineSpace
{
	NSMutableParagraphStyle *paragraphStyle = [[NSMutableParagraphStyle alloc] init];
	
	if( SYSTEM_VERSION_LESS_THAN(@"7.0") )
	{
		lineSpace -= 2.0f;
		if( lineSpace < 0 )
		{
			lineSpace = 0;
		}
	}
    paragraphStyle.lineSpacing = lineSpace;
	paragraphStyle.alignment = NSTextAlignmentLeft;
	paragraphStyle.lineBreakMode = NSLineBreakByTruncatingTail;
	paragraphStyle.lineHeightMultiple = 1.1f;
    
	NSDictionary *attributeDic = @{NSParagraphStyleAttributeName:paragraphStyle};
	
    NSAttributedString *repString = [[NSAttributedString alloc] initWithString:string attributes:attributeDic];
	
    return repString;
}
@end