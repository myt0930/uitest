#import "SettingData.h"
#import "LiveInfoTrait.h"

#define KEY_FAVORITE              (@"KEY_FAVORITE_LIVE")
#define KEY_SHOW_DETAIL_DIALOG    (@"KEY_SHOW_DETAIL_DIALOG")

@implementation SettingData

+ (SettingData*)instance
{
    static id _instance = nil;
    @synchronized( self )
	{
        if( _instance == nil )
		{
            _instance = [[self alloc] init];
        }
    }
	
    return _instance;
}

- (id)init
{
	if( (self = [super init]) )
	{
		_favoriteLiveArray = [[NSMutableArray alloc] init];
		[self defaultData];
	}
	
	return self;
}

- (void)defaultData
{
	NSUserDefaults*      userDefaults      = [NSUserDefaults standardUserDefaults];
	NSMutableDictionary* mutableDictionary = [NSMutableDictionary dictionary];

	[mutableDictionary setObject:_favoriteLiveArray				forKey:KEY_FAVORITE];
	[mutableDictionary setObject:[NSNumber numberWithBool:NO]	forKey:KEY_SHOW_DETAIL_DIALOG];

	[userDefaults registerDefaults:mutableDictionary];
}

- (BOOL)loadData
{
	NSUserDefaults* userDefaults = [NSUserDefaults standardUserDefaults];

	NSArray* array			= [userDefaults arrayForKey:  KEY_FAVORITE];
	_favoriteLiveArray      = [[NSMutableArray alloc] initWithArray:array];
	_isShowDetailDialog		= [userDefaults boolForKey:KEY_SHOW_DETAIL_DIALOG];
	
	return true;
}


- (BOOL)saveData
{
	NSUserDefaults* userDefaults = [NSUserDefaults standardUserDefaults];

	[userDefaults setObject:_favoriteLiveArray								forKey:KEY_FAVORITE];
	[userDefaults setObject:[NSNumber numberWithBool:_isShowDetailDialog]	forKey:KEY_SHOW_DETAIL_DIALOG];

	return [userDefaults synchronize];
}


- (void)addFavoriteUniqueId:(NSString *)uniqueId
{
    if( [self isContainsFavoriteUniqueId:uniqueId] )
    {
        //重複登録されないように対応
        return;
    }
    
	[_favoriteLiveArray addObject:uniqueId];
    
    [_favoriteLiveArray sortUsingComparator:^NSComparisonResult(id obj1, id obj2) {
        return [obj1 compare:obj2];
    }];
	[self saveData];
}

- (void)removeFavoriteUniqueId:(NSString *)uniqueId
{
    [_favoriteLiveArray removeObject:uniqueId];
	[self saveData];
}

- (BOOL)isContainsFavoriteUniqueId:(NSString *)uniqueId
{
    return [_favoriteLiveArray containsObject:uniqueId];
}

- (void)setShowDetailDialog:(BOOL)isShow
{
	_isShowDetailDialog = isShow;
	[self saveData];
}
@end
