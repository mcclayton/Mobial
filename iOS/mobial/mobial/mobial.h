//
//  mobial.h
//  mobial
//
//  Created by Vipul Nataraj on 1/16/14.
//  Copyright (c) 2014 espressbros. All rights reserved.
//

#import <Foundation/Foundation.h>
#import <UIKit/UIKit.h>
#import <QuartzCore/QuartzCore.h>

@interface mobial : NSObject
-(void)startTrackingViewController;
-(void)postToParse:(NSData *)jsonData;
-(NSData*)takeScreenshot:(UIImage*)img;
@end
