//
//  NewsViewController.h
//  SeguridadTemplate
//
//  Created by IronStark on 10/14/13.
//  Copyright (c) 2013 MARKS. All rights reserved.
//

#import <UIKit/UIKit.h>

@interface NewsViewController : UIViewController
    
    @property (nonatomic, strong) NSNumber *Id;
    @property (nonatomic, strong) IBOutlet UILabel *newsLabel;
    @property (nonatomic, strong) IBOutlet UITextView *newsData;
    @property (nonatomic, strong) NSString * Title;
    @property (nonatomic, strong) IBOutlet UILabel *newsDate;

@end
