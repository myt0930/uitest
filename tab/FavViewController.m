//
//  FavViewController.m
//  tab
//
//  Created by Wataru Miyata on 2014/02/15.
//  Copyright (c) 2014年 MIYATA Wataru. All rights reserved.
//

#import "FavViewController.h"
#import "LiveInfoTrait.h"
#import "SettingData.h"
#import "SSGentleAlertView.h"

@interface FavViewController ()

@end

@implementation FavViewController

- (void)viewDidLoad
{
    [super viewDidLoad];
}

- (void)didReceiveMemoryWarning
{
    [super didReceiveMemoryWarning];
    // Dispose of any resources that can be recreated.
}

- (void)reloadFavItems
{
    NSMutableArray *favArray = [NSMutableArray array];
    NSArray *saveUniqueIdArray = [SettingData instance].favoriteLiveArray;
    for( NSString *uniqueId in saveUniqueIdArray )
    {
        const LiveInfoTrait *trait = [LiveInfoTrait traitOfUniqueID:uniqueId];
        if( trait )
        {
            [favArray addObject:trait];
        }
    }
	self.items = favArray;
	
    [super reloadItems];
}

#pragma mark - MyTabBarControllerDelegate
- (void) didSelect:(TabBarController *)tabBarController {
    [self.navigationController popToRootViewControllerAnimated:NO];
	[self reloadFavItems];
}

- (void)didLoadMast
{
	[self reloadFavItems];
}
@end
