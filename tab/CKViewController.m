#import <CoreGraphics/CoreGraphics.h>
#import "CKViewController.h"
#import "CKCalendarView.h"
#import "HomeViewController.h"
#import "LiveInfoTrait.h"

@interface CKViewController () <CKCalendarDelegate>

@property(nonatomic, weak) CKCalendarView *calendar;
@property(nonatomic, strong) UILabel *dateLabel;
@property(nonatomic, strong) NSDateFormatter *dateFormatter;
@property(nonatomic, strong) NSDate *minimumDate;
@property(nonatomic, strong) NSArray *disabledDates;

@end

@implementation CKViewController

- (id)initWithCurrentDate:(NSDate*)currentDate {
    self = [super init];
    if (self) {
        CKCalendarView *calendar = [[CKCalendarView alloc] initWithStartDay:startMonday currentDate:(NSDate*)currentDate];
        self.calendar = calendar;
        calendar.delegate = self;

        self.dateFormatter = [[NSDateFormatter alloc] init];
        [self.dateFormatter setDateFormat:@"yyyy/MM/dd"];
        self.minimumDate = [self.dateFormatter dateFromString:@"2014/02/01"];

        self.disabledDates = @[];

        calendar.onlyShowCurrentMonth = NO;
        calendar.adaptHeightToNumberOfWeeksInMonth = YES;

        calendar.frame = CGRectMake(10, 10, 300, 320);
        [self.view addSubview:calendar];

        self.dateLabel = [[UILabel alloc] initWithFrame:CGRectMake(10, CGRectGetMaxY(calendar.frame) + 4, self.view.bounds.size.width, 24)];
        [self.view addSubview:self.dateLabel];

        self.view.backgroundColor = [UIColor whiteColor];
        
        // UIActivityIndicatorViewのインスタンス化
        _indicator = [[UIActivityIndicatorView alloc]initWithFrame:CGRectMake(0, 0, 100, 100)];
        _indicator.center = self.view.center;
        _indicator.activityIndicatorViewStyle = UIActivityIndicatorViewStyleGray;
        [self.view addSubview:_indicator];

        [[NSNotificationCenter defaultCenter] addObserver:self selector:@selector(localeDidChange) name:NSCurrentLocaleDidChangeNotification object:nil];
    }
    return self;
}

- (void)dealloc {
    [[NSNotificationCenter defaultCenter] removeObserver:self];
}

- (void)viewDidLoad
{
    [super viewDidLoad];
	// Do any additional setup after loading the view, typically from a nib.
    
    self.navigationItem.title = @"カレンダー";
}

- (void)viewDidUnload
{
    [super viewDidUnload];
    // Release any retained subviews of the main view.
}

- (BOOL)shouldAutorotateToInterfaceOrientation:(UIInterfaceOrientation)interfaceOrientation
{
    if ([[UIDevice currentDevice] userInterfaceIdiom] == UIUserInterfaceIdiomPhone) {
        return (interfaceOrientation != UIInterfaceOrientationPortraitUpsideDown);
    } else {
        return YES;
    }
}

- (void)localeDidChange {
    [self.calendar setLocale:[NSLocale currentLocale]];
}

- (BOOL)dateIsDisabled:(NSDate *)date {
    for (NSDate *disabledDate in self.disabledDates) {
        if ([disabledDate isEqualToDate:date]) {
            return YES;
        }
    }
    return NO;
}

#pragma mark -
#pragma mark - CKCalendarDelegate

- (void)calendar:(CKCalendarView *)calendar configureDateItem:(CKDateItem *)dateItem forDate:(NSDate *)date {
//    if ([self dateIsDisabled:date]) {
//        dateItem.backgroundColor = [UIColor redColor];
//        dateItem.textColor = [UIColor whiteColor];
//    }
}

- (BOOL)calendar:(CKCalendarView *)calendar willSelectDate:(NSDate *)date {
    return ![self dateIsDisabled:date];
}

- (void)calendar:(CKCalendarView *)calendar didSelectDate:(NSDate *)date {
    if( _indicator.isAnimating )
    {
        //インジケーター表示中は何もしない
        return;
    }
    HomeViewController *home = [self.navigationController.viewControllers objectAtIndex:0];
    [home changeDate:date];
    [_indicator startAnimating];
    
    [self performSelector:@selector(popRootView) withObject:nil afterDelay:0.2];
}

- (BOOL)calendar:(CKCalendarView *)calendar willChangeToMonth:(NSDate *)date {
    return YES;
}

- (void)popRootView
{
    [self.navigationController popToRootViewControllerAnimated:YES];
}

@end