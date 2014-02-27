//
//  ViewController.h
//  tab
//
//  Created by MIYATA Wataru on 2014/02/18.
//  Copyright (c) 2014年 MIYATA Wataru. All rights reserved.
//

#import "SectionBaseViewController.h"
@class LiveHouseTrait;

@interface LiveListViewController : SectionBaseViewController
@property const LiveHouseTrait *trait;
@property UIActivityIndicatorView *indicator;

- (id)initWithTrait:(const LiveHouseTrait *)trait;
@end
