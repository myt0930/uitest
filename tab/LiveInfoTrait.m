//
//  LiveInfoTrait.m
//  iOS7Sampler
//
//  Created by MIYATA Wataru on 2014/01/31.
//  Copyright (c) 2014年 Shuichi Tsutsumi. All rights reserved.
//

#import "LiveInfoTrait.h"
#import "LoadData.h"

static NSMutableArray* traitList;

@implementation LiveInfoTrait

+(void)initialize
{
	traitList = [[NSMutableArray alloc] init];
}

+ (void)removeAllMast
{
	[traitList removeAllObjects];
}

+(NSArray *)traitList
{
	return traitList;
}

+(NSArray *)traitListWithDate:(NSString *)date
{
	NSMutableArray *liveList = [NSMutableArray array];
	for( LiveInfoTrait *trait in traitList )
	{
		if( [trait.liveDate compare:date] == NSOrderedSame )
		{
			[liveList addObject:trait];
		}
	}
	
	return liveList;
}

+(NSArray *)traitListWithLiveHouseNo:(int)liveHouseNo
{
	NSMutableArray *liveList = [NSMutableArray array];
	for( LiveInfoTrait *trait in traitList )
	{
		if( trait.liveHouseNo == liveHouseNo )
		{
			[liveList addObject:trait];
		}
	}
	
	return liveList;
}

+(id)traitOfLiveHouseNo:(int)liveHouseNo liveDate:(NSString *)liveDate
{
	for( LiveInfoTrait *trait in traitList )
	{
		if( trait.liveHouseNo	== liveHouseNo &&
			[trait.liveDate compare:liveDate] == NSOrderedSame )
		{
			return trait;
		}
	}
	return nil;
}

+(id)traitOfUniqueID:(NSString *)uniqueID
{
	for( LiveInfoTrait *trait in traitList )
	{
		if( [trait.uniqueID compare:uniqueID] == NSOrderedSame )
		{
			return trait;
		}
	}
	return nil;
}

+ (void)loadMast:(LoadData*)data
{
	//ライブ一覧をクリア
	[self removeAllMast];
	
	int masterCount = [data getInt16];
	
	for( int i = 0;i < masterCount;i++ )
	{
		int liveHouseNo		= [data getInt16];
		NSString *liveDate	= [data getString16];
		int subNo			= [data getInt16];
		NSString *title		= [data getString16];
		NSString *act		= [data getString16];
		NSString *open		= [data getString16];
		NSString *start		= [data getString16];
		int advanceTicket	= [data getInt16];
		int todayTicket		= [data getInt16];
		
		LiveInfoTrait *trait = [[LiveInfoTrait alloc] initWithLiveHouseNo:liveHouseNo
																 liveDate:liveDate
																	subNo:subNo
															   eventTitle:title
																	  act:act
																 openTime:open
																startTime:start
															advanceTicket:advanceTicket
															  todayTicket:todayTicket];
		[traitList addObject:trait];
	}
}

-(id)initWithLiveHouseNo:(int)liveHouseNo
				liveDate:(NSString *)liveDate
				   subNo:(int)subNo
			  eventTitle:(NSString *)eventTitle
					 act:(NSString *)act
				openTime:(NSString *)openTime
			   startTime:(NSString *)startTime
		   advanceTicket:(int)advanceTicket
			 todayTicket:(int)todayTicket
{
	if( (self = [super init] ) )
	{
		_liveHouseNo	= liveHouseNo;
		_liveDate		= liveDate;
		_subNo			= subNo;
		_eventTitle		= eventTitle;
		_act			= act;
		_openTime		= openTime;
		_startTime		= startTime;
		_advanceTicket	= advanceTicket;
		_todayTicket	= todayTicket;
		_uniqueID		= [NSString stringWithFormat:@"%d%@%d", _liveHouseNo, _liveDate, _subNo];
	}
	return self;
}


+(void)addTestLiveInfo
{
	int date = 20140101;
	
	[traitList removeAllObjects];
	
	LiveInfoTrait *trait = nil;
	
	trait = [[LiveInfoTrait alloc] initWithLiveHouseNo:1
											  liveDate:[NSString stringWithFormat:@"%d", date++]
												 subNo:1
											eventTitle:@"水の中で雨中 presents 'squlls' vol.1\n"
														"「水の中で雨中＆カミヒカルス Wレコ発ライブ」"
												   act:@"水の中で雨中 / カミヒカルス / OA：ホッタモモ"
											  openTime:@"18:00"
											 startTime:@"18:30"
										 advanceTicket:2000
										   todayTicket:2500];
	[traitList addObject:trait];
	
	trait = [[LiveInfoTrait alloc] initWithLiveHouseNo:1
											  liveDate:[NSString stringWithFormat:@"%d", date++]
												 subNo:1
											eventTitle:@"New Creation"
												   act:@"ハクビシン / 所在ない / Gizmondo / -u / ダテオトコ / MOGIKOJIN"
											  openTime:@"18:00"
											 startTime:@"18:30"
										 advanceTicket:2000
										   todayTicket:2500];
	[traitList addObject:trait];
	
	trait = [[LiveInfoTrait alloc] initWithLiveHouseNo:1
											  liveDate:[NSString stringWithFormat:@"%d", date++]
												 subNo:1
											eventTitle:@"東放学園音響専門学校 presents "" 逆チョコ時代到来！？ """
												   act:@"SPELL BOX / FULL EFFECT'"
											  openTime:@"18:00"
											 startTime:@"18:30"
										 advanceTicket:2000
										   todayTicket:2500];
	[traitList addObject:trait];
	
	trait = [[LiveInfoTrait alloc] initWithLiveHouseNo:1
											  liveDate:[NSString stringWithFormat:@"%d", date++]
												 subNo:1
											eventTitle:@"Motion day!"
												   act:@"ボクノクロエ / スーパーキウイ / 天然ウォーター / the aims"
											  openTime:@"18:00"
											 startTime:@"18:30"
										 advanceTicket:2000
										   todayTicket:2500];
	[traitList addObject:trait];
	
	trait = [[LiveInfoTrait alloc] initWithLiveHouseNo:1
											  liveDate:[NSString stringWithFormat:@"%d", date++]
												 subNo:1
											eventTitle:@"sign"
												   act:@"amenoto / econoise (大阪) / 小玉しのぶ / Laco / chimes"
											  openTime:@"18:00"
											 startTime:@"18:30"
										 advanceTicket:2000
										   todayTicket:2500];
	[traitList addObject:trait];
	
	trait = [[LiveInfoTrait alloc] initWithLiveHouseNo:1
											  liveDate:[NSString stringWithFormat:@"%d", date++]
												 subNo:1
											eventTitle:@"Speech"
												   act:@"kumagusu / VIVISECTION / はるお幕府(28才) / ゆゆゆ / YourGentleman"
											  openTime:@"18:00"
											 startTime:@"18:30"
										 advanceTicket:2000
										   todayTicket:2500];
	[traitList addObject:trait];
	
	trait = [[LiveInfoTrait alloc] initWithLiveHouseNo:1
											  liveDate:[NSString stringWithFormat:@"%d", date++]
												 subNo:1
											eventTitle:@"sign"
												   act:@"NEIGHBOUHOOD / CICADA / lore (帯広) / The Sexbots (USA) / オカープー / less quantic"
											  openTime:@"18:00"
											 startTime:@"18:30"
										 advanceTicket:2000
										   todayTicket:2500];
	[traitList addObject:trait];
	
	trait = [[LiveInfoTrait alloc] initWithLiveHouseNo:1
											  liveDate:[NSString stringWithFormat:@"%d", date++]
												 subNo:1
											eventTitle:@"UTAGE presents #43 創造の祭"
												   act:@"Jump the Lights / unicycle dio / キリクと魔女 / 青猫 / ORIE / nord / 前髪が、ある"
											  openTime:@"18:00"
											 startTime:@"18:30"
										 advanceTicket:2000
										   todayTicket:2500];
	[traitList addObject:trait];
	
	trait = [[LiveInfoTrait alloc] initWithLiveHouseNo:1
											  liveDate:[NSString stringWithFormat:@"%d", date++]
												 subNo:1
											eventTitle:@"【ともぞう企画 不要の要】"
												   act:@"neonrocks / 嬲 (ナブル) / KEEN MONKEY WORK / GOLD & SAINT / 1000000$boyz"
											  openTime:@"18:00"
											 startTime:@"18:30"
										 advanceTicket:2000
										   todayTicket:2500];
	[traitList addObject:trait];
	
	trait = [[LiveInfoTrait alloc] initWithLiveHouseNo:1
											  liveDate:[NSString stringWithFormat:@"%d", date++]
												 subNo:1
											eventTitle:@"UTAGE presents #44 宴"
												   act:@"giovanna / 空際ノベル / Manhole New World / -JunK- / 空リウム。 / STAYING INDOORS"
											  openTime:@"18:00"
											 startTime:@"18:30"
										 advanceTicket:2000
										   todayTicket:2500];
	[traitList addObject:trait];
	
	trait = [[LiveInfoTrait alloc] initWithLiveHouseNo:1
											  liveDate:[NSString stringWithFormat:@"%d", date++]
												 subNo:1
											eventTitle:@"きのうみたゆめ"
												   act:@"「ricca」 / 病気くん / Sea Child / 終電車 / The Applepie"
											  openTime:@"18:00"
											 startTime:@"18:30"
										 advanceTicket:2000
										   todayTicket:2500];
	[traitList addObject:trait];
	
	trait = [[LiveInfoTrait alloc] initWithLiveHouseNo:1
											  liveDate:[NSString stringWithFormat:@"%d", date++]
												 subNo:1
											eventTitle:@"Speech"
												   act:@"ネス / Theこゝろ此処に在らズ / and more"
											  openTime:@"18:00"
											 startTime:@"18:30"
										 advanceTicket:2000
										   todayTicket:2500];
	[traitList addObject:trait];
	
	trait = [[LiveInfoTrait alloc] initWithLiveHouseNo:1
											  liveDate:[NSString stringWithFormat:@"%d", date++]
												 subNo:1
											eventTitle:@"sign"
												   act:@"絶えずBEATしろ / Qu (神戸) / HILU / Doxie / quiet acting / zooqs"
											  openTime:@"18:00"
											 startTime:@"18:30"
										 advanceTicket:2000
										   todayTicket:2500];
	[traitList addObject:trait];
	
	trait = [[LiveInfoTrait alloc] initWithLiveHouseNo:1
											  liveDate:[NSString stringWithFormat:@"%d", date++]
												 subNo:1
											eventTitle:@"「毒を喰らわば皿まで舐れ！」"
												   act:@"野獣のリリアン / ゲスバンド / THE天国カー / Droog"
											  openTime:@"18:00"
											 startTime:@"18:30"
										 advanceTicket:2000
										   todayTicket:2500];
	[traitList addObject:trait];
	
	trait = [[LiveInfoTrait alloc] initWithLiveHouseNo:1
											  liveDate:[NSString stringWithFormat:@"%d", date++]
												 subNo:1
											eventTitle:@"POCALIS レコ発×Beat Burn 東京編"
												   act:@"POCALIS / moja / bronbaba / てあしくちびる /【 DJ 】 伊香賀守 (つくばロックフェス)"
											  openTime:@"18:00"
											 startTime:@"18:30"
										 advanceTicket:2000
										   todayTicket:2500];
	[traitList addObject:trait];
	
	trait = [[LiveInfoTrait alloc] initWithLiveHouseNo:1
											  liveDate:[NSString stringWithFormat:@"%d", date++]
												 subNo:1
											eventTitle:@"サキ企画「さらば安定」"
												   act:@"センチメンタル岡田 / THIS IS JAPAN / 狂気 / +1バンド"
											  openTime:@"18:00"
											 startTime:@"18:30"
										 advanceTicket:2000
										   todayTicket:2500];
	[traitList addObject:trait];
	
	trait = [[LiveInfoTrait alloc] initWithLiveHouseNo:1
											  liveDate:[NSString stringWithFormat:@"%d", date++]
												 subNo:1
											eventTitle:@"小さなギフト"
												   act:@"中村ガク＆田辺ヒカル (THE FOREVERS) / フジーシンゴ (March) / 少年ヤング / 片山さゆ里 / and more"
											  openTime:@"18:00"
											 startTime:@"18:30"
										 advanceTicket:2000
										   todayTicket:2500];
	[traitList addObject:trait];
	
	trait = [[LiveInfoTrait alloc] initWithLiveHouseNo:1
											  liveDate:[NSString stringWithFormat:@"%d", date++]
												 subNo:1
											eventTitle:@"Motion day! "
												   act:@"硝子越しの暴走 / 風来ボーイズ / Black Sixx / HELLO AND ROLL / -JunK-"
											  openTime:@"18:00"
											 startTime:@"18:30"
										 advanceTicket:2000
										   todayTicket:2500];
	[traitList addObject:trait];
	
	trait = [[LiveInfoTrait alloc] initWithLiveHouseNo:1
											  liveDate:[NSString stringWithFormat:@"%d", date++]
												 subNo:1
											eventTitle:@"sign"
												   act:@"Artlark / nikka / Artrandom / 芦塚雅俊 / lowbalance / イレイサーズ"
											  openTime:@"18:00"
											 startTime:@"18:30"
										 advanceTicket:2000
										   todayTicket:2500];
	[traitList addObject:trait];
	
	trait = [[LiveInfoTrait alloc] initWithLiveHouseNo:1
											  liveDate:[NSString stringWithFormat:@"%d", date++]
												 subNo:1
											eventTitle:@"New Creation"
												   act:@"ワールドバンパク / postcard of japan / 夜の夢 / 殺生に絶望 / 平山織愛 (Sir Oriental Orchestra/HELLO AND ROLL)"
											  openTime:@"18:00"
											 startTime:@"18:30"
										 advanceTicket:2000
										   todayTicket:2500];
	[traitList addObject:trait];
	
	trait = [[LiveInfoTrait alloc] initWithLiveHouseNo:1
											  liveDate:[NSString stringWithFormat:@"%d", date++]
												 subNo:1
											eventTitle:@"石川 (股下89)・サエキ共同企画 "" jyumyou waiting room vol.2 """
												   act:@"股下89 / SOSITE / 月夜のドラッグ"
											  openTime:@"18:00"
											 startTime:@"18:30"
										 advanceTicket:2000
										   todayTicket:2500];
	[traitList addObject:trait];
	
	trait = [[LiveInfoTrait alloc] initWithLiveHouseNo:1
											  liveDate:[NSString stringWithFormat:@"%d", date++]
												 subNo:1
											eventTitle:@"necozeneco企画「木天蓼祭～またたびまつり～」"
												   act:@"necozeneco / ソコラノグループ / ジブンジカン / テジナ"
											  openTime:@"18:00"
											 startTime:@"18:30"
										 advanceTicket:2000
										   todayTicket:2500];
	[traitList addObject:trait];
	
	trait = [[LiveInfoTrait alloc] initWithLiveHouseNo:1
											  liveDate:[NSString stringWithFormat:@"%d", date++]
												 subNo:1
											eventTitle:@"妥協レコード presents 「ベルリンペンギンレコード"" 晩年 "" レコ発」"
												   act:@"ベルリンペンギンレコード / THE MASHIKO / ヤーチャイカ / Amia Calva (京都) / 左右 / ポテコムジン"
											  openTime:@"18:00"
											 startTime:@"18:30"
										 advanceTicket:2000
										   todayTicket:2500];
	[traitList addObject:trait];
	
	trait = [[LiveInfoTrait alloc] initWithLiveHouseNo:1
											  liveDate:[NSString stringWithFormat:@"%d", date++]
												 subNo:1
											eventTitle:@"カンノテルヒロ×相良×Motion共同企画弾き語りイベン ト「弾くか死ぬか」"
												   act:@"相良 (from ALTRA&CRYOGENIC) / 菅野旭洋 (from The chord) / 太田哲宇 (from RENTAL HOPE) / 太田美音 (from microcosm) / アオキ (from you made my day) / 石井優也 (from MAGI SCENE ) / Ayumi melody (from A Month of Sundays ) / 平野太樹 (from room12) / 中村コロ助（from fifi）/ 世界のあーちゃん（from きのこ帝国）"
											  openTime:@"18:00"
											 startTime:@"18:30"
										 advanceTicket:2000
										   todayTicket:2500];
	[traitList addObject:trait];
	
	trait = [[LiveInfoTrait alloc] initWithLiveHouseNo:1
											  liveDate:[NSString stringWithFormat:@"%d", date++]
												 subNo:1
											eventTitle:@"Speed of Light"
												   act:@"Newspaper Boys (秋田) / GIRAFFEL LIFE (神戸) / bundle gaze spoon / メルシーバッハ / ささきたかゆき"
											  openTime:@"18:00"
											 startTime:@"18:30"
										 advanceTicket:2000
										   todayTicket:2500];
	[traitList addObject:trait];
	
	trait = [[LiveInfoTrait alloc] initWithLiveHouseNo:1
											  liveDate:[NSString stringWithFormat:@"%d", date++]
												 subNo:1
											eventTitle:@"Slogun"
												   act:@"ハルカカナタ / the stall / jiji / ナスツカサ (macronift) / Moshu / trente-sept"
											  openTime:@"18:00"
											 startTime:@"18:30"
										 advanceTicket:2000
										   todayTicket:2500];
	[traitList addObject:trait];
	
	trait = [[LiveInfoTrait alloc] initWithLiveHouseNo:1
											  liveDate:[NSString stringWithFormat:@"%d", date++]
												 subNo:1
											eventTitle:@"New Creation"
												   act:@"・クリストファー・マグワイアのコピバン\n"
														 "サダトシトモヤ (Slumberland)/"
														 "キノシタナオヤ (Dots Dash・サーティーン)/"
														 "マツイヒロキ (ポテコムジン・SATISFACTIONS)/"
														 "そとかわたかこ (ex.the banca)//"
														 
														 "・トースタンコース/"
														 "モデストロイ (modestroy)/"
														 "えらめぐみ (股下89・Dots dash)/"
														 "和田ハマー翔太 (modestroy)//"
														 
														 "・鈴木バンド/"
														 "スズキヨウスケ (she might be swimmer)/"
														 "ミヤジマ (ex.she might be swimmer)/"
														 "赤羽 (told)//"
														 
														 "・Dots Dash//"
														 
														 "・帝国の逆襲/"
														 "アンドレ (狂気・SATISFACTIONS)/"
														 "Ikuko (Merpeoples)/"
														 "シン・マスナガ (シガレットケース・科楽特奏隊)//"
											  openTime:@"18:00"
											 startTime:@"18:30"
										 advanceTicket:2000
										   todayTicket:2500];
	[traitList addObject:trait];
	
	trait = [[LiveInfoTrait alloc] initWithLiveHouseNo:1
											  liveDate:[NSString stringWithFormat:@"%d", date++]
												 subNo:1
											eventTitle:@"Ander Suplex presents "" Under mine vol.5 """
												   act:@"Ander Suplex / and more"
											  openTime:@"18:00"
											 startTime:@"18:30"
										 advanceTicket:2000
										   todayTicket:2500];
	[traitList addObject:trait];
	
}

@end
