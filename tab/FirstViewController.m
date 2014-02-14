//
//  FirstViewController.m
//  tab
//
//  Created by MIYATA Wataru on 2014/02/05.
//  Copyright (c) 2014年 MIYATA Wataru. All rights reserved.
//

#import "FirstViewController.h"
#import "CustomTableViewCell.h"
#import "LiveInfoTrait.h"
#import "TabBarController.h"

@interface FirstViewController ()
@property (nonatomic, strong) NSArray *items;
@end

@implementation FirstViewController

- (void)viewDidLoad
{
    [super viewDidLoad];
	
	UINib *nib = [UINib nibWithNibName:@"CustomTableViewCell" bundle:nil];
	[self.tableView registerNib:nib forCellReuseIdentifier:@"customCell"];
	
	[LiveInfoTrait addTestLiveInfo];
	_items = [LiveInfoTrait traitList];
	
	_tableView.dataSource = self;
	_tableView.delegate = self;
	
}

- (void)didReceiveMemoryWarning
{
    [super didReceiveMemoryWarning];
    // Dispose of any resources that can be recreated.
}

// =============================================================================
#pragma mark - UITableViewDataSource

- (NSInteger)numberOfSectionsInTableView:(UITableView *)tableView {
    
    return 1;
}

- (NSInteger)tableView:(UITableView *)tableView numberOfRowsInSection:(NSInteger)section {
    
    return [self.items count];
}

- (UITableViewCell *)tableView:(UITableView *)tableView cellForRowAtIndexPath:(NSIndexPath *)indexPath
{
    static NSString *CellIdentifier = @"customCell";
    
    CustomTableViewCell *cell = [self.tableView dequeueReusableCellWithIdentifier:CellIdentifier];
    
    if (cell == nil) {
        
        cell = [[CustomTableViewCell alloc] initWithStyle:UITableViewCellStyleSubtitle
										  reuseIdentifier:CellIdentifier];
        
        
    }
	
	const LiveInfoTrait *trait = [self.items objectAtIndex:indexPath.row];
	[cell setTextWithTrait:trait];

    return cell;
}


// =============================================================================
#pragma mark - UITableViewDelegate

- (CGFloat)tableView:(UITableView *)tableView heightForRowAtIndexPath:(NSIndexPath *)indexPath {
    
    return CELL_HEIGHT;
}

- (void)tableView:(UITableView *)tableView didSelectRowAtIndexPath:(NSIndexPath *)indexPath {
	
    LiveInfoTrait *trait = self.items[indexPath.row];
    NSString *className = [@"Detail" stringByAppendingString:@"ViewController"];
    
    if (NSClassFromString(className)) {
		
        Class aClass = NSClassFromString(@"DetailViewController");
        id instance = [[aClass alloc] init];
        
        if ([instance isKindOfClass:[UIViewController class]]) {
            
            [(UIViewController *)instance setTitle:trait.openTime];
            [self.navigationController pushViewController:(UIViewController *)instance
                                                 animated:YES];
        }
    }
    
    [tableView deselectRowAtIndexPath:indexPath animated:YES];
}

#pragma mark - MyTabBarControllerDelegate
- (void) didSelect:(TabBarController *)tabBarController {
    NSIndexPath *index = [NSIndexPath indexPathForRow:0 inSection:0];
	[_tableView scrollToRowAtIndexPath:index atScrollPosition:UITableViewScrollPositionNone animated:NO];
}

@end
