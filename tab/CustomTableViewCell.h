//
//  CustomTableViewCell.h
//  iOS7Sampler
//
//  Created by MIYATA Wataru on 2014/01/30.
//  Copyright (c) 2014年 Shuichi Tsutsumi. All rights reserved.
//

#import <UIKit/UIKit.h>

#define CELL_HEIGHT			90.0

@class LiveInfoTrait;

@interface CustomTableViewCell : UITableViewCell
@property IBOutlet UILabel *place;
@property IBOutlet UILabel *title;
@property IBOutlet UILabel *act;
@property IBOutlet UILabel *date;
@property IBOutlet UILabel *day;
@property IBOutlet UIImageView *favImageView;

- (void)setTextWithTrait:(const LiveInfoTrait *)trait;
@end
