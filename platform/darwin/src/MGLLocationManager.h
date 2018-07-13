#import <Foundation/Foundation.h>
#import <CoreLocation/CoreLocation.h>

NS_ASSUME_NONNULL_BEGIN

@protocol MGLLocationManagerDelegate;

/**
 The `MGLLocationManager` protocol defines a set of methods that
 the `MGLMapView` uses to receive location-related events.
 
 To receive the current user location implement `MGLMapViewDelegate`s delegate methods:
 
 -[MGLMapViewDelegate mapView:didUpdateUserLocation:]
 -[MGLMapViewDelegate mapView:didFailToLocateUserWithError:]
 
 */
@protocol MGLLocationManager <NSObject>

@required

/**
 The delegate to receive location updates.
 
 This property must not be setted either before or after setting this object as
 the `MGLMapView`'s location manager.
 */
@property (nonatomic, weak) id<MGLLocationManagerDelegate> delegate;

/**
 Specifies a physical device orientation.
 */
@property (nonatomic) CLDeviceOrientation headingOrientation;

/**
 Returns the current localization authorization status.
 */
@property (nonatomic, readonly) CLAuthorizationStatus authorizationStatus;

/**
 Starts the generation of location updates that reports the user's current location.
 */
- (void)startUpdatingLocation;

/**
 Stops the generation of location updates.
 */
- (void)stopUpdatingLocation;

/**
 Starts the generation of heading updates that reports the user's current hading.
 */
- (void)startUpdatingHeading;

/**
 Stops the generation of heading updates.
 */
- (void)stopUpdatingHeading;

/**
 Requests permission to use the location services whenever the app is running.
 */
- (void)requestAlwaysAuthorization;

/**
 Requests permission to use the location services while the app is in
 the foreground.
 */
- (void)requestWhenInUseAuthorization;

/**
 Dissmisses immediately the heading calibration view from screen.
 */
- (void)dismissHeadingCalibrationDisplay;

@end

/**
 The `MGLLocationManagerDelegate` protocol defines a set of methods that you
 use to receive location updates from the associated location manager.
 */
@protocol MGLLocationManagerDelegate <NSObject>

/**
 Notifies the delegate with the new location data.
 
 @param manager The location manager reporting the update.
 @param locations An array of `CLLocation` objects. For `MGLMapView` default
 location manager this property will always contain at least one object
 representing the current location.
 */
- (void)locationManager:(id<MGLLocationManager>)manager
     didUpdateLocations:(NSArray<CLLocation *> *)locations;

/**
 Notifies the delegate with the new heading data.
 
 @param manager The location manager reporting the update.
 @param newHeading The new heading update.
 */
- (void)locationManager:(id<MGLLocationManager>)manager
       didUpdateHeading:(CLHeading *)newHeading;

/**
 Asks the delegate if the calibration alert should be displayed.
 
 @param manager The location manager reporting the calibration.
 */
- (BOOL)locationManagerShouldDisplayHeadingCalibration:(id<MGLLocationManager>)manager;

/**
 Notifies the delegate that the location manager was unable to retrieve
 location updates.
 
 @param manager The location manager reporting the error.
 @param error An error object containing the error code that indicates
 why the location manager failed.
 */
- (void)locationManager:(id<MGLLocationManager>)manager
       didFailWithError:(nonnull NSError *)error;

@optional

@end

NS_ASSUME_NONNULL_END
