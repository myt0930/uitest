#import <UIKit/UIKit.h>

@interface CKViewController : UIViewController
@property UIActivityIndicatorView *indicator;

- (id)initWithCurrentDate:(NSDate*)currentDate;
@end