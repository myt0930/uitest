//
//  DetailViewController.m
//  tab
//
//  Created by MIYATA Wataru on 2014/02/12.
//  Copyright (c) 2014年 MIYATA Wataru. All rights reserved.
//

#import <CoreGraphics/CoreGraphics.h>
#import <QuartzCore/QuartzCore.h>
#import "DetailViewController.h"
#import "Common.h"
#import "LiveInfoTrait.h"
#import "SettingData.h"
#import "BaseViewController.h"

@interface DetailViewController ()

@end

@implementation DetailViewController

- (id)initWithLiveInfoTrait:(const LiveInfoTrait*)trait baseController:(BaseViewController *)baseController
{
    if( (self = [super init]) )
    {
        _liveTrait = trait;
		_baseController = baseController;
		[self setTitle:@"2014/02/18 (Wed)"];
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

        labelHeight += _place.frame.size.height + 5;
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
        _act.attributedText      = [NSAttributedString tlsAttributedStringWithString:actText lineSpace:4.0f];
        [_act sizeToFit];
        
        labelHeight += _act.frame.size.height + 20;
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
        
        labelHeight += _startTime.frame.size.height + 2;
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
        
        _liveHouseInfo.textColor        = ACT_COLOR;
		_liveHouseInfo.backgroundColor	= BACKGROUND_COLOR;
        _liveHouseInfo.attributedText   = [NSAttributedString tlsAttributedStringWithString:@"ライブハウスの連絡先や住所など\n３行か4行ぐらいのテキストを表示。\n\n" lineSpace:2.0f];//最後の行に\n\nを入れると調度良いスクロールサイズになる
		[_liveHouseInfo sizeToFit];
        
        labelHeight += _liveHouseInfo.frame.size.height;
	}
	
	//画面サイズからヘッダー、フッダーの高さを引いたサイズ
	int appFrameHeight = [UIScreen mainScreen].applicationFrame.size.height - 44 - 49 + 1;
	if( labelHeight < appFrameHeight )
	{
		labelHeight = appFrameHeight;
	}
    
	[_scrollView setContentSize:CGSizeMake(_childView.bounds.size.width, labelHeight)];
	
	//スター画像
	{
		_favBaseView		= [[UIView alloc] initWithFrame:CGRectMake(260, 0, 60, 60)];
		[_scrollView addSubview:_favBaseView];
		
		UITapGestureRecognizer *tapGesture = [[UITapGestureRecognizer alloc] initWithTarget:self action:@selector(onTapFavorite:)];
		_favBaseView.userInteractionEnabled = YES;
		[_favBaseView addGestureRecognizer:tapGesture];
		
		_favImageView		= [[UIImageView alloc] initWithFrame:CGRectMake(20, 10, 24, 24)];
		_favImageView.image = [self favoriteUIImage:[[SettingData instance] isContainsFavoriteUniqueId:_liveTrait.uniqueID]];
		
		[_favBaseView addSubview:_favImageView];
	}
}

- (void)didReceiveMemoryWarning
{
    [super didReceiveMemoryWarning];
    // Dispose of any resources that can be recreated.
}

-(void)switchChanged:(UISwitch*)uiSwitch{
    if( uiSwitch.on )
    {
        [[SettingData instance] addFavoriteUniqueId:_liveTrait.uniqueID];
    }
    else
    {
        [[SettingData instance] removeFavoriteUniqueId:_liveTrait.uniqueID];
    }
}

- (void)screenShot
{
	// キャプチャ対象をWindowに設定
	UIWindow *window = [[UIApplication sharedApplication] keyWindow];
	
	// キャプチャ画像を描画する対象を生成
	UIGraphicsBeginImageContextWithOptions(window.bounds.size, NO, 0.0f);
	CGContextRef context = UIGraphicsGetCurrentContext();
	
	// Windowの現在の表示内容を１つずつ描画
	for (UIWindow *aWindow in [[UIApplication sharedApplication] windows]) {
		[aWindow.layer renderInContext:context];
	}
	
	// 描画した内容をUIImageとして受け取り
	UIImage *capturedImage = UIGraphicsGetImageFromCurrentImageContext();
	
	// 描画を終了
	UIGraphicsEndImageContext();
	
	//画像保存完了時のセレクタ指定
	SEL selector = @selector(onCompleteCapture:didFinishSavingWithError:contextInfo:);
	//画像を保存する
	UIImageWriteToSavedPhotosAlbum(capturedImage, self, selector, NULL);
}

//画像保存完了時のセレクタ
- (void)onCompleteCapture:(UIImage *)screenImage
 didFinishSavingWithError:(NSError *)error contextInfo:(void *)contextInfo
{
	NSString *message = @"画像を保存しました";
	if (error) message = @"画像の保存に失敗しました";
	UIAlertView *alert = [[UIAlertView alloc] initWithTitle: @""
													message: message
												   delegate: nil
										  cancelButtonTitle: @"OK"
										  otherButtonTitles: nil];	//twitterへ投稿ボタンをつける
	[alert show];
}
										  
- (void)onTapFavorite:(UITapGestureRecognizer *)tapGesture
{
	if( [[SettingData instance] isContainsFavoriteUniqueId:_liveTrait.uniqueID] )
	{
		[[SettingData instance] removeFavoriteUniqueId:_liveTrait.uniqueID];
		_favImageView.image = [self favoriteUIImage:NO];
	}
	else
	{
		[[SettingData instance] addFavoriteUniqueId:_liveTrait.uniqueID];
		_favImageView.image = [self favoriteUIImage:YES];
	}
	
	//呼び出し元のテーブル表示をリロード
	[_baseController reloadTable];
}

- (UIImage *)favoriteUIImage:(BOOL)isFavorite
{
	return [UIImage imageNamed:@"star_64_f5f5f5.png"
					 withColor:isFavorite ? FAV_COLOR : FAV_DISABLE_COLOR
				 drawAsOverlay:NO];
}

@end
