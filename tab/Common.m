//
//  Common.m
//  iOS7Sampler
//
//  Created by Wataru Miyata on 2014/02/04.
//  Copyright (c) 2014年 Shuichi Tsutsumi. All rights reserved.
//

#import "Common.h"

@implementation Common
//-<NHN>--------------------------------------------------------------------------------------------
// Function : documentDir
// Explain  :
//--------------------------------------------------------------------------------------------<NHN>-
+ (NSString*)documentDir
{
	NSArray* paths = NSSearchPathForDirectoriesInDomains(NSDocumentDirectory, NSUserDomainMask, YES);
	return [paths objectAtIndex:0];
}

//-<NHN>--------------------------------------------------------------------------------------------
// Function : libraryCachesDir
// Explain  :
//--------------------------------------------------------------------------------------------<NHN>-
+ (NSString*)libraryCachesDir
{
	NSArray* paths = NSSearchPathForDirectoriesInDomains(NSCachesDirectory, NSUserDomainMask, YES);
	return [paths objectAtIndex:0];
}

//-<NHN>--------------------------------------------------------------------------------------------
// Function : tempDir
// Explain  :
//--------------------------------------------------------------------------------------------<NHN>-
+ (NSString*)tempDir
{
	return NSTemporaryDirectory();
}

@end

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
@implementation UIImage (Ex)

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

#pragma mark -
@implementation NSString(Ex)
//-<NHN>--------------------------------------------------------------------------------------------
// Function : stringWithDateFormat: date:
// Explain  : カレンダー設定が和暦でも正しくフォーマットしてNSStringを返す
//--------------------------------------------------------------------------------------------<NHN>-
+ (NSString*)stringWithDateFormat:(NSString*)format timeZone:(NSTimeZone*)timeZone date:(NSDate*)date
{
	if( format == nil || date == nil ){ return @""; }
    
	NSDateFormatter* df = [[NSDateFormatter alloc] initWithGregorianCalendar];
	[df setLocale:[NSLocale currentLocale]];
	[df setTimeZone:timeZone];
	df.dateFormat  = format;
	NSString* str = [df stringFromDate:date];
    
	return str;
}
+ (NSString*)stringWithDateFormat:(NSString*)format date:(NSDate*)date
{
	return [NSString stringWithDateFormat:format timeZone:[NSTimeZone timeZoneWithAbbreviation:@"JST"] date:date];
}

@end

#pragma mark -
@implementation NSDateFormatter(Ex)
- (id)initWithGregorianCalendar
{
	if( self = [self init] )
	{
		NSCalendar* calendar = [[NSCalendar alloc] initWithCalendarIdentifier:NSGregorianCalendar];
		[self setCalendar:calendar];
	}
	return self;
}
@end


#pragma mark -
@implementation NSDate(Ex)

- (BOOL)smallerThanDate:(NSDate*)date
{
    return [self timeIntervalSinceDate:date] < 0;
}
- (BOOL)largerThanDate:(NSDate*)date
{
    return [self timeIntervalSinceDate:date] > 0;
}

@end

@implementation NSObject (Ex)

- (void)performBlockInBackground:(VoidBlock)block {
	[self performSelectorInBackground:@selector(executeBlockInAutoReleasePool:) withObject:[block copy]];
}

- (void)executeBlockInAutoReleasePool:(VoidBlock)block {
	@autoreleasepool {
		block();
	}
}

@end