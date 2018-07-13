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
    _mapView.accessibilityIdentifier = @"Map View";
}

- (void)testTappingDisabledPolyline {
    XCUIApplication *app = [[XCUIApplication alloc] init];
    XCUIElement *element = 
    
}
