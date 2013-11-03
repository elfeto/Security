//
//  FirstViewController.h
//  SeguridadTemplate
//
//  Created by IronStark on 9/9/13.
//  Copyright (c) 2013 MARKS. All rights reserved.
//

#import <UIKit/UIKit.h>

@interface NewsFeedViewController : UIViewController <UITableViewDataSource, UITableViewDelegate>{

    NSArray * theArray;
    
    NSArray * theIDS;
    
}

@property (nonatomic, strong) IBOutlet UITableView *tableView;

@end
