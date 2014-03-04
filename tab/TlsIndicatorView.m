//
//  TlsIndicatorView.m
//  tab
//
//  Created by MIYATA Wataru on 2014/03/04.
//  Copyright (c) 2014年 MIYATA Wataru. All rights reserved.
//

#import "TlsIndicatorView.h"
#import <QuartzCore/QuartzCore.h>

@implementation TlsIndicatorView

- (id)init
{
	CGRect screenRect = [[UIScreen mainScreen] bounds];
	CGRect r = CGRectMake(screenRect.size.width/2 -20, screenRect.size.height/2-20, 40, 40);
	if( (self = [super initWithFrame:r]) )
	{
		//Viewを角丸にする
		self.layer.cornerRadius = 5;
		self.clipsToBounds = true;
		
		self.backgroundColor = [[UIColor alloc] initWithRed:0 green:0 blue:0 alpha:0.5];
		_indicator = [[UIActivityIndicatorView alloc] initWithActivityIndicatorStyle:UIActivityIndicatorViewStyleWhiteLarge];
		[self addSubview:_indicator];
		[_indicator setFrame:CGRectMake(0, 0, 40, 40)];
		
		self.hidden = YES;
	}
	return self;
}

- (void)startAnimating
{
	self.hidden = NO;
	[_indicator startAnimating];
}

- (void)stopAnimating
{
	self.hidden = YES;
	[_indicator stopAnimating];
}

@end
