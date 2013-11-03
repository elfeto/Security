//
//  SecondViewController.m
//  SeguridadTemplate
//
//  Created by IronStark on 9/9/13.
//  Copyright (c) 2013 MARKS. All rights reserved.
//

#import "TrolleysViewController.h"


@implementation TrolleysViewController

@synthesize webView;

- (void)viewDidLoad
{
    [super viewDidLoad];
    
    NSURL *url = [NSURL URLWithString:@"http://ada.uprrp.edu/~ftorres/Seguridad/Trolley.html"];
    NSURLRequest *request = [NSURLRequest requestWithURL:url];
    [webView loadRequest:request];
	// Do any additional setup after loading the view, typically from a nib.
}

- (void)didReceiveMemoryWarning
{
    [super didReceiveMemoryWarning];
    // Dispose of any resources that can be recreated.
}

@end
