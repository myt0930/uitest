//
//  DetailViewController.h
//  tab
//
//  Created by MIYATA Wataru on 2014/02/12.
//  Copyright (c) 2014年 MIYATA Wataru. All rights reserved.
//

#import <UIKit/UIKit.h>

@class LiveInfoTrait;

@interface DetailViewController : UIViewController<UIScrollViewDelegate>
@property (strong, nonatomic) IBOutlet UIScrollView *scrollView;
@property (strong, nonatomic) IBOutlet UIView *childView;
@property (weak, nonatomic) IBOutlet UISwitch *favSwitch;
@property const LiveInfoTrait *liveTrait;
@property IBOutlet UIImageView *favImageView;

@property IBOutlet UILabel *date;
@property IBOutlet UILabel *place;
@property IBOutlet UILabel *eventTitle;
@property IBOutlet UILabel *act;
@property IBOutlet UILabel *ticket;
@property IBOutlet UILabel *startTime;
@property IBOutlet UILabel *liveHouseInfo;

- (id)initWithLiveInfoTrait:(const LiveInfoTrait*)trait;

@end
