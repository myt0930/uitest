@class LiveInfoTrait;

@interface SettingData : NSObject
@property (readonly) NSMutableArray* favoriteLiveArray;


+ (SettingData*)instance;

- (BOOL)loadData;
- (BOOL)saveData;
- (void)addFavoriteUniqueId:(NSString *)uniqueId;
- (void)removeFavoriteUniqueId:(NSString *)uniqueId;
- (BOOL)isContainsFavoriteUniqueId:(NSString *)uniqueId;
@end
