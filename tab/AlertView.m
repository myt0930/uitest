//
//  AlertView.m
//  tab
//
//  Created by MIYATA Wataru on 2014/02/27.
//  Copyright (c) 2014年 MIYATA Wataru. All rights reserved.
//

#import "AlertView.h"
#import "SSGentleAlertView.h"

enum UPDATE_STATE
{
	UPDATE_STATE_DL = 0,
	UPDATE_STATE_RETRY,
	UPDATE_STATE_DONE,
};

@implementation AlertView

- (void)initWithTitle:(NSString *)title
			  message:(NSString *)message
			   blocks:(void(^)(NSInteger))blocks
	cancelButtonTitle:(NSString *)cancelButtonTitle
	otherButtonTitles:(NSString *)otherButtonTitles, ...
{
	_alertProgress = UPDATE_STATE_DL;
	SSGentleAlertView* alert = [[SSGentleAlertView alloc] initWithStyle:SSGentleAlertViewStyleBlack
																  title:@"SSGentleAlertView"
																message:@"ライブ日程が更新されました。\n今すぐダウンロードしますか？"
															   delegate:self
													  cancelButtonTitle:@"いいえ"
													  otherButtonTitles:@"はい",nil];
	alert.cancelButtonIndex = 0;
	[alert show];
}

- (void)showMasterUpdateRetryAleart
{
	_alertProgress = UPDATE_STATE_RETRY;
	SSGentleAlertView* alert = [[SSGentleAlertView alloc] initWithStyle:SSGentleAlertViewStyleBlack
																  title:@"SSGentleAlertView"
																message:@"ダウンロードに失敗しました。\nリトライしますか？\n(通信環境の良い場所で行って下さい。)"
															   delegate:self
													  cancelButtonTitle:@"いいえ"
													  otherButtonTitles:@"はい",nil];
	alert.cancelButtonIndex = 0;
	[alert show];
}

- (void)showDoneUpdateAleart
{
	_alertProgress = UPDATE_STATE_DONE;
	SSGentleAlertView* alert = [[SSGentleAlertView alloc] initWithStyle:SSGentleAlertViewStyleBlack
																  title:@"SSGentleAlertView"
																message:@"ダウンロードが完了しました。"
															   delegate:self
													  cancelButtonTitle:nil
													  otherButtonTitles:@"OK",nil];
	alert.cancelButtonIndex = 0;
	[alert show];
}

- (void)alertView:(UIAlertView *)alertView clickedButtonAtIndex:(NSInteger)buttonIndex
{
	if( _blocks )
	{
		_blocks(buttonIndex);
		_blocks = nil;
	}
}

@end
