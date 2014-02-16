//
//  CustomTableViewCell.m
//  iOS7Sampler
//
//  Created by MIYATA Wataru on 2014/01/30.
//  Copyright (c) 2014年 Shuichi Tsutsumi. All rights reserved.
//

#import "CustomTableViewCell.h"
#import "LiveInfoTrait.h"
#import "Common.h"

#define LABEL_X_POS 30

@implementation CustomTableViewCell

- (void)awakeFromNib
{
    [super awakeFromNib];
    self.accessoryType = UITableViewCellAccessoryDisclosureIndicator;
    self.backgroundColor				= BACKGROUND_COLOR;
	self.contentView.backgroundColor	= BACKGROUND_COLOR;

	//日付
	{
		//日
		_date = [UILabel labelWithFontName:MAKE_HIRAGINO_FONT(9)
									 color:ACT_COLOR];
		_date.backgroundColor	= BACKGROUND_COLOR;
		[self.contentView addSubview:_date];
		
		//曜日
		_day = [UILabel labelWithFontName:MAKE_HIRAGINO_FONT(9)
									 color:ACT_COLOR];
		_day.backgroundColor	= BACKGROUND_COLOR;
		[self.contentView addSubview:_day];
	}
	
	//会場名
	{
		_place = [UILabel labelWithFontName:MAKE_HIRAGINO_BOLD_FONT(12)
									  color:MAKE_UICOLOR(238,101,87,1)];
		_place.numberOfLines	= 1;
		
		_place.backgroundColor	= BACKGROUND_COLOR;
		[self.contentView addSubview:_place];
	}
	
	//イベント名
	{
		_title = [UILabel labelWithFontName:MAKE_HIRAGINO_BOLD_FONT(10)
									  color:TITLE_COLOR];
		_title.numberOfLines	= 2;
		_title.backgroundColor	= BACKGROUND_COLOR;
		
		[self.contentView addSubview:_title];
	}
	
	//出演者名
	{
		_act = [UILabel labelWithFontName:MAKE_HIRAGINO_BOLD_FONT(11)
									color:ACT_COLOR];
		_act.numberOfLines		= 3;
		_act.backgroundColor	= BACKGROUND_COLOR;
		[self.contentView addSubview:_act];
	}
}

- (void)setSelected:(BOOL)selected animated:(BOOL)animated
{
    [super setSelected:selected animated:animated];

    // Configure the view for the selected state
}

static int dateCount = 1;

- (void)setTextWithTrait:(const LiveInfoTrait *)trait
{
	float datePosX	= dateCount >= 10 ? 6 : 8.5;
	_date.frame		= CGRectMake(datePosX, CELL_HEIGHT / 2 - 15, 20, 8);
	_day.frame		= CGRectMake(5, CELL_HEIGHT / 2, 20, 9);
	
	_place.frame	= CGRectMake(LABEL_X_POS, 5,	290-LABEL_X_POS, 12);
	_title.frame	= CGRectMake(LABEL_X_POS, 20,	290-LABEL_X_POS, 30);
	_act.frame		= CGRectMake(LABEL_X_POS, 45,	290-LABEL_X_POS, 30);
	
	NSArray* placeList		= @[@"新宿Motion"];
	_date.text				= [NSString stringWithFormat:@"%d", dateCount++];
	
	int a = arc4random() % 3;
	switch (a) {
		case 0:
			_day.textColor	= ACT_COLOR;
			break;
		case 1:
			_day.textColor	= TITLE_COLOR;
			break;
		case 2:
			_day.textColor	= PLACE_COLOR;
			break;
	}
	
	_day.text				= @"Sat";
	_place.attributedText	= [NSAttributedString tlsAttributedStringWithString:placeList[arc4random() % placeList.count]
                                                                    lineSpace:2.0f];
	_title.attributedText	= [NSAttributedString tlsAttributedStringWithString:trait.eventTitle
                                                                    lineSpace:2.0f];
	_act.attributedText		= [NSAttributedString tlsAttributedStringWithString:trait.act
                                                                   lineSpace:2.0f];
	
	//座標調整
	[_day	sizeToFit];
	[_date	sizeToFit];
	[_place sizeToFit];
	[_title sizeToFit];
	[_act	sizeToFit];
}

@end
