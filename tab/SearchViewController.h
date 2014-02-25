//
//  SecondViewController.h
//  tab
//
//  Created by MIYATA Wataru on 2014/02/05.
//  Copyright (c) 2014年 MIYATA Wataru. All rights reserved.
//

#import <UIKit/UIKit.h>
#import "TabBarController.h"
#import "SectionBaseViewController.h"

@interface SearchViewController : SectionBaseViewController<UISearchDisplayDelegate,UISearchBarDelegate>
@property (strong, nonatomic) IBOutlet UISearchDisplayController *searchController;
@property (strong, nonatomic) IBOutlet UISearchBar *searchBar;

@end
