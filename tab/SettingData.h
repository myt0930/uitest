@class LiveInfoTrait;

@interface SettingData : NSObject
@property NSMutableArray* favoriteLiveArray;
@property BOOL isShowDetailDialog;

+ (SettingData*)instance;

- (BOOL)loadData;
- (BOOL)saveData;
- (void)addFavoriteUniqueId:(NSString *)uniqueId;
- (void)removeFavoriteUniqueId:(NSString *)uniqueId;
- (BOOL)isContainsFavoriteUniqueId:(NSString *)uniqueId;
- (void)setShowDetailDialog:(BOOL)isShow;
@end
