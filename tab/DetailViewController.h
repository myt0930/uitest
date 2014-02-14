//
//  DetailViewController.h
//  tab
//
//  Created by MIYATA Wataru on 2014/02/12.
//  Copyright (c) 2014年 MIYATA Wataru. All rights reserved.
//

#import <UIKit/UIKit.h>

@interface DetailViewController : UIViewController<UIScrollViewDelegate>
@property (strong, nonatomic) IBOutlet UIScrollView *scrollView;
@property (strong, nonatomic) IBOutlet UILabel *place;
@property (strong, nonatomic) IBOutlet UIView *childView;

@end
