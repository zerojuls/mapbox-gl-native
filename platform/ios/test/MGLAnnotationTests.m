#import <Mapbox/Mapbox.h>
#import <XCTest/XCTest.h>
#import "MGLTestUtility.h"

@interface MGLAnnotationTests : XCTestCase <MGLMapViewDelegate>
@property (nonatomic) XCTestExpectation *expectation;
@property (nonatomic) MGLMapView *mapView;
@end

@implementation MGLAnnotationTests

- (void)setUp
{
    [super setUp];
    _mapView = [[MGLMapView alloc] initWithFrame:CGRectMake(0, 0, 64, 64)];
    _mapView.delegate = self;
    _mapView.accessibilityLabel = @"Map View";
}

- (void)testTappingDisabledPolyline {
    
    CLLocationCoordinate2D coordinates[] = {
        _mapView.visibleCoordinateBounds.ne,
        _mapView.centerCoordinate,
        _mapView.visibleCoordinateBounds.sw
    };
    
    MGLPolyline *polyline = [MGLPolyline polylineWithCoordinates:coordinates count:3];
    polyline.enabled = NO;
    polyline.accessibilityLabel = @"Polyline";
    [_mapView addAnnotation:polyline];
    
    // Code to simulate tap
    // Getting 
    XCUIApplication *app = [[XCUIApplication alloc] init];
    XCUIElement *mapElement = [[app descendantsMatchingType:XCUIElementTypeAny] elementMatchingPredicate:[NSPredicate predicateWithFormat:@"accessibilityLabel == 'Map View'"]];
//    XCUIElement *lineElement = [[app descendantsMatchingType:XCUIElementTypeAny] elementMatchingPredicate:[NSPredicate predicateWithFormat:@"accessibilityLabel == 'Polyline'"]];
    
    CGPoint point = [_mapView convertCoordinate:_mapView.centerCoordinate toPointToView:_mapView];
    
    XCUICoordinate *center = [mapElement coordinateWithNormalizedOffset:CGVectorMake(point.x, point.y)];
    [center tap];
    
    XCTAssertNil(_mapView.selectedAnnotations.firstObject, @"There should be no selected annotation");
}

@end
