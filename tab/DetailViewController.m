//
//  DetailViewController.m
//  tab
//
//  Created by MIYATA Wataru on 2014/02/12.
//  Copyright (c) 2014年 MIYATA Wataru. All rights reserved.
//

#import "DetailViewController.h"
#import "Common.h"
#import "LiveInfoTrait.h"

@interface DetailViewController ()

@end

@implementation DetailViewController

- (id)initWithLiveInfoTrait:(const LiveInfoTrait*)trait
{
    if( (self = [super init]) )
    {
        _liveTrait = trait;
    }
    return self;
}

- (void)viewDidLoad
{
    [super viewDidLoad];
    // Do any additional setup after loading the view from its nib.
	
	_scrollView.delegate = self;
	_childView.backgroundColor = BACKGROUND_COLOR;
    
    float labelHeight = _place.frame.origin.y;

    //会場名
	{
        _place.textColor        = PLACE_COLOR;
		_place.backgroundColor	= BACKGROUND_COLOR;
        _place.text             = @"新宿Motion";
        [_place sizeToFit];

        labelHeight += _place.frame.size.height + 10;
	}
    
    //イベント名
	{
        CGRect rect = _eventTitle.frame;
        _eventTitle.frame = CGRectMake(rect.origin.x, labelHeight, rect.size.width, rect.size.height);
        
		_eventTitle.textColor           = TITLE_COLOR;
		_eventTitle.backgroundColor     = BACKGROUND_COLOR;
        _eventTitle.attributedText      = [NSAttributedString tlsAttributedStringWithString:_liveTrait.eventTitle lineSpace:2.0f];
        [_eventTitle sizeToFit];
        
        labelHeight += _eventTitle.frame.size.height + 10;
	}
    
    //出演者名
	{
        NSString *actText = [_liveTrait.act stringByReplacingOccurrencesOfString:@"/" withString:@"\n"];
        actText = [actText stringByReplacingOccurrencesOfString:@"\n " withString:@"\n"];
        
        CGRect rect = _act.frame;
        _act.frame = CGRectMake(rect.origin.x, labelHeight, rect.size.width, rect.size.height);
        
		_act.textColor           = ACT_COLOR;
		_act.backgroundColor     = BACKGROUND_COLOR;
        _act.attributedText      = [NSAttributedString tlsAttributedStringWithString:actText lineSpace:3.0f];
        [_act sizeToFit];
        
        labelHeight += _act.frame.size.height + 30;
	}
    
    //時刻
	{
        CGRect rect = _startTime.frame;
        _startTime.frame = CGRectMake(rect.origin.x, labelHeight, rect.size.width, rect.size.height);
        
		_startTime.textColor           = ACT_COLOR;
		_startTime.backgroundColor     = BACKGROUND_COLOR;
        _startTime.attributedText      = [NSAttributedString tlsAttributedStringWithString:[NSString stringWithFormat:@"OPEN/START %@/%@",
                                                                                            _liveTrait.openTime,
                                                                                            _liveTrait.startTime]
                                                                                 lineSpace:2.0f];
        [_startTime sizeToFit];
        
        labelHeight += _startTime.frame.size.height;
	}
    
    //チケット
	{
        CGRect rect = _ticket.frame;
        _ticket.frame = CGRectMake(rect.origin.x, labelHeight, rect.size.width, rect.size.height);
        
		_ticket.textColor           = ACT_COLOR;
		_ticket.backgroundColor     = BACKGROUND_COLOR;
        _ticket.attributedText      = [NSAttributedString tlsAttributedStringWithString:[NSString stringWithFormat:@"前売/当日      ¥%d/¥%d",
                                                                                         _liveTrait.advanceTicket,
                                                                                         _liveTrait.todayTicket]
                                                                              lineSpace:2.0f];
        [_ticket sizeToFit];
        
        labelHeight += _ticket.frame.size.height + 10;
	}
    
    //ライブハウス情報
	{
        CGRect rect = _liveHouseInfo.frame;
        _liveHouseInfo.frame = CGRectMake(rect.origin.x, labelHeight, rect.size.width, rect.size.height);
        
        _liveHouseInfo.textColor        = PLACE_COLOR;
		_liveHouseInfo.backgroundColor	= BACKGROUND_COLOR;
        _liveHouseInfo.text             = @"ライブハウスの連絡先や住所など\n３行か4行ぐらいのテキストを表示。";
        [_liveHouseInfo sizeToFit];
        
        labelHeight += _liveHouseInfo.frame.size.height;
	}
//
//    
//    _eventTitle.attributedText = [NSAttributedString tlsAttributedStringWithString:_liveTrait.eventTitle];
//    _eventTitle.text    = _liveTrait.eventTitle;
//    _act.text           = _liveTrait.act;
//    _ticket.text        = [NSString stringWithFormat:@"前売/当日 ¥%d/¥%d",
//                           _liveTrait.advanceTicket, _liveTrait.todayTicket];
//    _startTime.text     = [NSString stringWithFormat:@"OPEN/START %@/%@", _liveTrait.openTime, _liveTrait.startTime];
//    _liveHouseInfo.text = @"ライブハウスの連絡先や住所など\n３行か4行ぐらいのテキストを表示。";
//    
    
	[_scrollView setContentSize:CGSizeMake(_childView.bounds.size.width, labelHeight)];
	NSLog(@" viewDidLoad %@",NSStringFromCGRect(self.scrollView.frame));
}

- (void)didReceiveMemoryWarning
{
    [super didReceiveMemoryWarning];
    // Dispose of any resources that can be recreated.
}

@end
