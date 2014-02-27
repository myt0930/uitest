//
//  SectionBaseViewController.h
//  tab
//
//  Created by MIYATA Wataru on 2014/02/25.
//  Copyright (c) 2014年 MIYATA Wataru. All rights reserved.
//

#import "BaseViewController.h"

@interface SectionBaseViewController : BaseViewController
@property NSArray *sectionArray;
@property NSMutableArray *rowCountArray;

- (void)reloadItems;
- (void)clearItems;
@end
