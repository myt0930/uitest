//
//  CustomTableViewCell.m
//  iOS7Sampler
//
//  Created by MIYATA Wataru on 2014/01/30.
//  Copyright (c) 2014年 Shuichi Tsutsumi. All rights reserved.
//

#import "CustomTableViewCell.h"
#import "LiveInfoTrait.h"
#import "LiveHouseTrait.h"
#import "Common.h"
#import "SettingData.h"

#define LABEL_X_POS 30

@implementation CustomTableViewCell

- (void)awakeFromNib
{
    [super awakeFromNib];
    self.accessoryType = UITableViewCellAccessoryDisclosureIndicator;
    self.backgroundColor				= WHITE_COLOR;
	self.contentView.backgroundColor	= WHITE_COLOR;

	//日付
	{
		//日
		_date = [UILabel labelWithFontName:MAKE_HIRAGINO_FONT(11)
									 color:BLACK_COLOR];
		_date.backgroundColor	= WHITE_COLOR;
        _date.textAlignment     = NSTextAlignmentCenter;
        _date.baselineAdjustment= UIBaselineAdjustmentAlignBaselines;
		[self.contentView addSubview:_date];
		
		//曜日
		_day = [UILabel labelWithFontName:MAKE_HIRAGINO_FONT(11)
									 color:BLACK_COLOR];
		_day.backgroundColor	= WHITE_COLOR;
        _day.textAlignment      = NSTextAlignmentCenter;
		[self.contentView addSubview:_day];
	}
	
	//会場名
	{
		_place = [UILabel labelWithFontName:MAKE_HIRAGINO_BOLD_FONT(12)
									  color:MAKE_UICOLOR(238,101,87,1)];
		_place.numberOfLines	= 1;
		
		_place.backgroundColor	= WHITE_COLOR;
		[self.contentView addSubview:_place];
	}
	
	//イベント名
	{
		_title = [UILabel labelWithFontName:MAKE_HIRAGINO_BOLD_FONT(10)
									  color:BLUE_COLOR];
		_title.numberOfLines	= 2;
		_title.backgroundColor	= WHITE_COLOR;
		
		[self.contentView addSubview:_title];
	}
	
	//出演者名
	{
		_act = [UILabel labelWithFontName:MAKE_HIRAGINO_BOLD_FONT(11)
									color:BLACK_COLOR];
		_act.numberOfLines		= 3;
		_act.backgroundColor	= WHITE_COLOR;
		[self.contentView addSubview:_act];
	}
	
	{
		_favImageView = [[UIImageView alloc] initWithImage:[UIImage imageNamed:@"star_64_f5f5f5.png"
																	withColor:FAV_COLOR
																 drawAsOverlay:NO]];
		_favImageView.frame = SYSTEM_VERSION_LESS_THAN(@"7.0") ? CGRectMake(320-22, 7, 12, 12) : CGRectMake(320-26, 7, 12, 12);
		[self.contentView addSubview:_favImageView];
	}
}

- (void)setSelected:(BOOL)selected animated:(BOOL)animated
{
    [super setSelected:selected animated:animated];

    // Configure the view for the selected state
}

- (void)setTextWithTrait:(const LiveInfoTrait *)trait
{
    int date = [[NSString stringWithDateFormat:@"dd" date:trait.liveDate] intValue];

	_date.frame		= CGRectMake(0, CELL_HEIGHT / 2 - 15, LABEL_X_POS-5, 18);
	_day.frame		= CGRectMake(0, CELL_HEIGHT / 2, LABEL_X_POS-5, 18);
	
	_place.frame	= CGRectMake(LABEL_X_POS, 5,	290-LABEL_X_POS, 12);
	_title.frame	= CGRectMake(LABEL_X_POS, 20,	290-LABEL_X_POS, 30);
	_act.frame		= CGRectMake(LABEL_X_POS, 45,	290-LABEL_X_POS, 30);
	
	_favImageView.hidden = ![[SettingData instance] isContainsFavoriteUniqueId:trait.uniqueID];
	
	_date.text				= [NSString stringWithFormat:@"%d", date];
	
	_day.text				= [NSString stringWithFormat:@"(%@)", trait.dayOfWeek];
	[self replaceDayOfWeekLabelToJapanese:_day];
	
    if( [_day.text isEqualToString:@"(土)"] )
    {
        _day.textColor	= BLUE_COLOR;
    }
    else if( [_day.text isEqualToString:@"(日)"] )
    {
        _day.textColor	= RED_COLOR;
    }
    else
    {
        _date.textColor = BLACK_COLOR;
        _day.textColor	= BLACK_COLOR;
    }
    
    const LiveHouseTrait *liveHouseTrait = [LiveHouseTrait traitOfLiveHouseNo:trait.liveHouseNo];
	
	_place.attributedText	= [NSAttributedString tlsAttributedStringWithString:liveHouseTrait.name
                                                                    lineSpace:2.0f];
	_title.attributedText	= [NSAttributedString tlsAttributedStringWithString:trait.eventTitle
                                                                    lineSpace:2.0f];
    NSString *actString     = [trait.act stringByReplacingOccurrencesOfString:@"\r\n" withString:@" / "];
    actString               = [actString stringByReplacingOccurrencesOfString:@"\n" withString:@" / "];
	_act.attributedText		= [NSAttributedString tlsAttributedStringWithString:actString
                                                                   lineSpace:2.0f];
	
	[_place sizeToFit];
	[_title sizeToFit];
	[_act	sizeToFit];
}

- (void)replaceDayOfWeekLabelToJapanese:(UILabel*)label
{
	NSString *text = label.text;
	
	if(		 [text isEqualToString:@"(Sun)"] )	label.text = @"(日)";
	else if( [text isEqualToString:@"(Mon)"] )	label.text = @"(月)";
	else if( [text isEqualToString:@"(Tue)"] )	label.text = @"(火)";
	else if( [text isEqualToString:@"(Wed)"] )	label.text = @"(水)";
	else if( [text isEqualToString:@"(Thu)"] )	label.text = @"(木)";
	else if( [text isEqualToString:@"(Fri)"] )	label.text = @"(金)";
	else if( [text isEqualToString:@"(Sat)"] )	label.text = @"(土)";
}

@end
