//
//  Requester.m
//  SeguridadTemplate
//
//  Created by IronStark on 9/26/13.
//  Copyright (c) 2013 MARKS. All rights reserved.
//

#import "Requester.h"

@implementation Requester

-(NSData*) SendJSONRequest:(NSString *)Url :(NSDictionary *)Json{
    
    NSError *error1;
    
    NSData *jsonData = [NSJSONSerialization dataWithJSONObject:Json options:NSJSONWritingPrettyPrinted error:&error1];
    
    NSString *postLength = [NSString stringWithFormat:@"%d", [jsonData length]];
    
    NSMutableURLRequest *request = [[NSMutableURLRequest alloc] init];
    
    [request setURL:[NSURL URLWithString:Url]];
    
    [request setHTTPMethod:@"POST"];
    
    [request setValue:postLength forHTTPHeaderField:@"Content-Length"];
    
    [request setValue:@"application/x-www-form-urlencoded" forHTTPHeaderField:@"Content-Type"];
    
    [request setHTTPBody:jsonData];
    
    NSHTTPURLResponse* urlResponse = nil;
    
    NSError *error = [[NSError alloc] init];
    
    NSData *responseData = [NSURLConnection sendSynchronousRequest:request returningResponse:&urlResponse error:&error];
    
    //NSString * result = [[NSString alloc] initWithData:responseData encoding:NSUTF8StringEncoding];
    
    return responseData;
    
    
}

-(NSArray*) ConvertToArray:(NSData*) Data{

    NSArray *allDataArray = [NSJSONSerialization JSONObjectWithData:Data options:0 error:nil];
    
    allDataArray = [allDataArray valueForKey:@"News"];
    
    return allDataArray;
    
}

-(NSData*) SendRequest:(NSString *)Url{
    
    NSMutableURLRequest *request = [[NSMutableURLRequest alloc] init];
    
    [request setURL:[NSURL URLWithString:Url]];
    
    [request setHTTPMethod:@"GET"];
    
    [request setValue:@"application/x-www-form-urlencoded" forHTTPHeaderField:@"Content-Type"];
    
    NSHTTPURLResponse* urlResponse = nil;
    
    NSError *error = [[NSError alloc] init];
    
    NSData *responseData = [NSURLConnection sendSynchronousRequest:request returningResponse:&urlResponse error:&error];
    
   // NSString * result = [[NSString alloc] initWithData:responseData encoding:NSUTF8StringEncoding];
    
    return responseData;
    
    
}


@end
