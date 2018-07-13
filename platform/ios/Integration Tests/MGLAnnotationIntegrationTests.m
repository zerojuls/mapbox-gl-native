//
//  MGLAnnotationIntegrationTests.m
//  MGLUITests
//
//  Created by Jordan on 7/13/18.
//  Copyright © 2018 Mapbox. All rights reserved.
//

#import <XCTest/XCTest.h>
#import "MGLMapViewIntegrationTest.h"
#import "MGLTestUtility.h"

@interface MGLAnnotationIntegrationTests : XCTestCase <MGLMapViewDelegate>
@property (nonatomic) XCTestExpectation *expectation;
@property (nonatomic) MGLMapView *mapView;
@end

@implementation MGLAnnotationIntegrationTests

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
    // Getting "NSInternalInconsistencyException", "No target application path specified via test configuration: ..."
    XCUIApplication *app = [[XCUIApplication alloc] init];
    XCUIElement *mapElement = [[app descendantsMatchingType:XCUIElementTypeAny] elementMatchingPredicate:[NSPredicate predicateWithFormat:@"accessibilityLabel == 'Map View'"]];
    //    XCUIElement *lineElement = [[app descendantsMatchingType:XCUIElementTypeAny] elementMatchingPredicate:[NSPredicate predicateWithFormat:@"accessibilityLabel == 'Polyline'"]];
    
    CGPoint point = [_mapView convertCoordinate:_mapView.centerCoordinate toPointToView:_mapView];
    
    XCUICoordinate *center = [mapElement coordinateWithNormalizedOffset:CGVectorMake(point.x, point.y)];
    [center tap];
    
    XCTAssertNil(_mapView.selectedAnnotations.firstObject, @"There should be no selected annotation");
}

@end

//@interface MGLAnnotationIntegrationTests : XCTestCase
//
//@end
//
//@implementation MGLAnnotationIntegrationTests
//
//- (void)setUp {
//    [super setUp];
//
//    // Put setup code here. This method is called before the invocation of each test method in the class.
//
//    // In UI tests it is usually best to stop immediately when a failure occurs.
//    self.continueAfterFailure = NO;
//    // UI tests must launch the application that they test. Doing this in setup will make sure it happens for each test method.
//    [[[XCUIApplication alloc] init] launch];
//
//    // In UI tests it’s important to set the initial state - such as interface orientation - required for your tests before they run. The setUp method is a good place to do this.
//}
//
//- (void)tearDown {
//    // Put teardown code here. This method is called after the invocation of each test method in the class.
//    [super tearDown];
//}
//
//- (void)testExample {
//    // Use recording to get started writing UI tests.
//    // Use XCTAssert and related functions to verify your tests produce the correct results.
//}
//
//@end
