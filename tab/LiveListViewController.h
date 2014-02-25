//
//  ViewController.h
//  tab
//
//  Created by MIYATA Wataru on 2014/02/18.
//  Copyright (c) 2014年 MIYATA Wataru. All rights reserved.
//

#import "BaseViewController.h"
@class LiveHouseTrait;

@interface LiveListViewController : BaseViewController
@property const LiveHouseTrait *trait;
@property NSArray *sectionArray;
@property NSMutableArray *rowCountArray;

- (id)initWithTrait:(const LiveHouseTrait *)trait;
@end
