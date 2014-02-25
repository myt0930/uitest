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
//@property (strong, nonatomic) IBOutlet UITableView *tableView;
@property const LiveHouseTrait *trait;

- (id)initWithTrait:(const LiveHouseTrait *)trait;
@end
