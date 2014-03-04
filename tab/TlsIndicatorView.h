//
//  TlsIndicatorView.h
//  tab
//
//  Created by MIYATA Wataru on 2014/03/04.
//  Copyright (c) 2014年 MIYATA Wataru. All rights reserved.
//

#import <UIKit/UIKit.h>

@interface TlsIndicatorView : UIView
@property UIView *loadingView;
@property UIActivityIndicatorView *indicator;

- (void)startAnimating;
- (void)stopAnimating;
@end
