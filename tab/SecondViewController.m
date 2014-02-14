//
//  SecondViewController.m
//  tab
//
//  Created by MIYATA Wataru on 2014/02/05.
//  Copyright (c) 2014年 MIYATA Wataru. All rights reserved.
//

#import "SecondViewController.h"
#import "CustomTableViewCell.h"
#import "LiveInfoTrait.h"
#import "TabBarController.h"

@interface SecondViewController ()
@property (nonatomic, strong) NSArray *items;
@end

@implementation SecondViewController

- (void)viewDidLoad
{
    [super viewDidLoad];
	// Do any additional setup after loading the view, typically from a nib.
	
	UINib *nib = [UINib nibWithNibName:@"CustomTableViewCell" bundle:nil];
	[self.tableView registerNib:nib forCellReuseIdentifier:@"cell"];
	
	[LiveInfoTrait addTestLiveInfo];
	
	_tableView.dataSource = self;
	_tableView.delegate = self;
	
	//テーブルの戦闘に検索バーを配置
	self.tableView.tableHeaderView = _searchBar;
	_items = [LiveInfoTrait traitList];
}

- (void)didReceiveMemoryWarning
{
    [super didReceiveMemoryWarning];
    // Dispose of any resources that can be recreated.
}

// =============================================================================
#pragma mark - UITableViewDataSource

- (NSInteger)numberOfSectionsInTableView:(UITableView *)tableView {
    
    return 2;
}

- (NSInteger)tableView:(UITableView *)tableView numberOfRowsInSection:(NSInteger)section {
    
    return [self.items count];
}

- (UITableViewCell *)tableView:(UITableView *)tableView cellForRowAtIndexPath:(NSIndexPath *)indexPath
{
    static NSString *CellIdentifier = @"cell";
    
    CustomTableViewCell *cell = [self.tableView dequeueReusableCellWithIdentifier:CellIdentifier];
    
    if (cell == nil) {
        
        cell = [[CustomTableViewCell alloc] initWithStyle:UITableViewCellStyleSubtitle
										  reuseIdentifier:CellIdentifier];
        
        
    }
	cell.textLabel.text = @"";
	cell.detailTextLabel.text = @"";
	cell.accessoryType = UITableViewCellAccessoryDisclosureIndicator;
    
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
    NSString *className = [@"DynamicBehaviors" stringByAppendingString:@"ViewController"];
    
    if (NSClassFromString(className)) {
		
        Class aClass = NSClassFromString(className);
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
    NSLog(@"tab 2");
}
@end
