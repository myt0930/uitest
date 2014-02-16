//
//  FirstViewController.m
//  tab
//
//  Created by MIYATA Wataru on 2014/02/05.
//  Copyright (c) 2014年 MIYATA Wataru. All rights reserved.
//

#import "HomeViewController.h"
#import "CustomTableViewCell.h"
#import "LiveInfoTrait.h"
#import "TabBarController.h"
#import "DetailViewController.h"

@implementation HomeViewController

- (void)viewDidLoad
{
    [super viewDidLoad];
	
	[LiveInfoTrait addTestLiveInfo];
	self.items = [LiveInfoTrait traitList];
}

- (void)didReceiveMemoryWarning
{
    [super didReceiveMemoryWarning];
    // Dispose of any resources that can be recreated.
}

// =============================================================================
#pragma mark - BaseViewController
- (void)tableView:(UITableView *)tableView didSelectRowAtIndexPath:(NSIndexPath *)indexPath {
	
    LiveInfoTrait *trait = self.items[indexPath.row];
    NSString *className = [@"Detail" stringByAppendingString:@"ViewController"];
    
    if (NSClassFromString(className)) {
		
        id instance = [[DetailViewController alloc] initWithLiveInfoTrait:trait];
        
        if ([instance isKindOfClass:[UIViewController class]]) {
            
            [(UIViewController *)instance setTitle:trait.liveDate];
            [self.navigationController pushViewController:(UIViewController *)instance
                                                 animated:YES];
        }
    }
    
    [tableView deselectRowAtIndexPath:indexPath animated:YES];
}

@end
