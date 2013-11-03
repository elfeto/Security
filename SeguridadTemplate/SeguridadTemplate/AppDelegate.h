//
//  AppDelegate.h
//  SeguridadTemplate
//
//  Created by IronStark on 9/9/13.
//  Copyright (c) 2013 MARKS. All rights reserved.
//

#import <UIKit/UIKit.h>
#import "NewsFeedViewController.h"

@interface AppDelegate : UIResponder <UIApplicationDelegate>{
    
    UINavigationController *navController;
    
    
}

@property (strong, nonatomic) UIWindow *window;
@property (nonatomic, retain) NewsFeedViewController * viewController;
@property (nonatomic, retain) UINavigationController * navController;
@end
