//
//  Requester.h
//  SeguridadTemplate
//
//  Created by IronStark on 9/26/13.
//  Copyright (c) 2013 MARKS. All rights reserved.
//

#import <Foundation/Foundation.h>

@interface Requester : NSURLRequest

-(NSData*) SendJSONRequest:(NSString*) Url:(NSDictionary*) Json;

-(NSData*) SendRequest:(NSString*) Url;

-(NSArray*) ConvertToArray:(NSData*) Data;

@end
