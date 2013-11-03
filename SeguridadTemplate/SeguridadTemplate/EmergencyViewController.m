//
//  FourthViewController.m
//  SeguridadTemplate
//
//  Created by IronStark on 9/12/13.
//  Copyright (c) 2013 MARKS. All rights reserved.
//

#import "EmergencyViewController.h"

@implementation EmergencyViewController

@synthesize webView;

- (id)initWithNibName:(NSString *)nibNameOrNil bundle:(NSBundle *)nibBundleOrNil
{
    self = [super initWithNibName:nibNameOrNil bundle:nibBundleOrNil];
    if (self) {
        // Custom initialization
    }
    return self;
}

- (void)viewDidLoad
{
    
    [super viewDidLoad];
    
    NSURL *url = [NSURL URLWithString:@"http://ada.uprrp.edu/~ftorres/Seguridad/Emergencia.html"];
    NSURLRequest *request = [NSURLRequest requestWithURL:url];
    [webView loadRequest:request];
	// Do any additional setup after loading the view.
}

- (void)didReceiveMemoryWarning
{
    [super didReceiveMemoryWarning];
    // Dispose of any resources that can be recreated.
}

@end
