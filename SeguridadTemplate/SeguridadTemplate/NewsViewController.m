//
//  NewsViewController.m
//  SeguridadTemplate
//
//  Created by IronStark on 10/14/13.
//  Copyright (c) 2013 MARKS. All rights reserved.
//

#import "NewsViewController.h"
#import "Requester.h"

@implementation NewsViewController

@synthesize Id;

@synthesize Title;

@synthesize newsLabel;

@synthesize newsData;

@synthesize newsDate;

- (id)initWithNibName:(NSString *)nibNameOrNil bundle:(NSBundle *)nibBundleOrNil
{
    self = [super initWithNibName:nibNameOrNil bundle:nibBundleOrNil];
    if (self) {
        // Custom initialization
    }
    return self;
}

- (BOOL)textViewShouldBeginEditing:(UITextView *)textView
{
    return NO;
}

- (void)viewDidLoad
{
    
    Requester *requester = [[Requester alloc] init];
    
    NSDictionary * json  = [[NSDictionary alloc] initWithObjectsAndKeys:Id, @"NewsID", nil];
    
    NSData *result = [requester SendJSONRequest:@"http://ada.uprrp.edu/~esantos/SecurityService/controllers/GetNews.php" :json];
    
    NSArray * res = [requester ConvertToArray:result];
    
    NSString * Date = [res valueForKey:@"Fecha"];
    
    NSString * Text = [res valueForKey:@"Data"];
    
    newsLabel.text = Title;
    
    newsData.delegate = self;
    
    newsData.text = Text;
    
    newsDate.text = Date;
    
    [super viewDidLoad];

	
}

-(void)willMoveToParentViewController:(UIViewController *)parent {
    
    if (!parent)
        self.navigationController.navigationBarHidden = true;
}

- (void)didReceiveMemoryWarning
{
    [super didReceiveMemoryWarning];
    // Dispose of any resources that can be recreated.
}

@end
