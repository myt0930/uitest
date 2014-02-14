//
//  DetailViewController.m
//  tab
//
//  Created by MIYATA Wataru on 2014/02/12.
//  Copyright (c) 2014年 MIYATA Wataru. All rights reserved.
//

#import "DetailViewController.h"

@interface DetailViewController ()

@end

@implementation DetailViewController

- (id)initWithNibName:(NSString *)nibNameOrNil bundle:(NSBundle *)nibBundleOrNil
{
    self = [super initWithNibName:nibNameOrNil bundle:nibBundleOrNil];
    if (self) {
//		_scrollView = [[UIScrollView alloc] initWithFrame:CGRectMake(0, 0, 1000, 1000)];
		
    }
    return self;
}

- (void)viewDidLoad
{
    [super viewDidLoad];
    // Do any additional setup after loading the view from its nib.
	
	_scrollView.delegate = self;
	_scrollView.backgroundColor = [UIColor blueColor];
	[_scrollView setContentSize:_childView.bounds.size];
	NSLog(@" viewDidLoad %@",NSStringFromCGRect(self.scrollView.frame));
}

- (void)didReceiveMemoryWarning
{
    [super didReceiveMemoryWarning];
    // Dispose of any resources that can be recreated.
}

@end
