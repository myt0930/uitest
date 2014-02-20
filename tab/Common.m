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
@implementation UIImage (Color)

+ (UIImage *)imageNamed:(NSString *)name withColor:(UIColor *)color drawAsOverlay:(BOOL)overlay{
    // load the image
    UIImage *img = [UIImage imageNamed:name];
    
    // begin a new image context, to draw our colored image onto
    UIGraphicsBeginImageContextWithOptions(img.size, NO, [[UIScreen mainScreen] scale]);
    
    // get a reference to that context we created
    CGContextRef context = UIGraphicsGetCurrentContext();
    
    // set the fill color
    [color setFill];
    
    // translate/flip the graphics context (for transforming from CG* coords to UI* coords
    CGContextTranslateCTM(context, 0, img.size.height);
    CGContextScaleCTM(context, 1.0, -1.0);
    
    // set the blend mode to overlay, and the original image
    CGContextSetBlendMode(context, kCGBlendModeOverlay);
    CGRect rect = CGRectMake(0, 0, img.size.width, img.size.height);
    if(overlay) CGContextDrawImage(context, rect, img.CGImage);
    
    // set a mask that matches the shape of the image, then draw (overlay) a colored rectangle
    CGContextClipToMask(context, rect, img.CGImage);
    CGContextAddRect(context, rect);
    CGContextDrawPath(context,kCGPathFill);
    
    // generate a new UIImage from the graphics context we drew onto
    UIImage *coloredImg = UIGraphicsGetImageFromCurrentImageContext();
    UIGraphicsEndImageContext();
    
    //return the color-burned image
    return coloredImg;
}
@end