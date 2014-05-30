//
//  mobial.m
//  mobial
//
//  Created by Vipul Nataraj on 1/16/14.
//  Copyright (c) 2014 espressbros. All rights reserved.
//

#import "mobial.h"

@implementation mobial

-(void) startTrackingViewController{
    NSDictionary *sendToServer;
    NSError *error;
    
    NSArray *params = [[NSArray alloc]initWithObjects:@"platform",@"activityName",@"activityScreenshot",@"DeviceID",@"timeStamp", nil];
    NSArray *data = [[NSArray alloc]initWithObjects:@"iOS", @"self", nil];
    NSData *moreJSONdata;
    UIImage *img;
    //moreJSONdata = [takeScreenshot:img];
    sendToServer = [NSDictionary dictionaryWithObjects:data forKeys:params];
    
    NSData *jsonData = [NSJSONSerialization dataWithJSONObject:sendToServer options:NSJSONWritingPrettyPrinted error:&error];
    postToParse:jsonData;
    return;
}
-(void)postToParse:(NSData*)jsonData{
    //add parse posting logic
    return;
}

-(void)takeScreenshot:(UIImage*)img{ //should be NSData
    
}


-(void)error:(NSError *)error{
    NSLog(@"error received from JSON");
}


@end
