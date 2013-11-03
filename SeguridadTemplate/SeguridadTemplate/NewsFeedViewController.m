//
//  FirstViewController.m
//  SeguridadTemplate
//
//  Created by IronStark on 9/9/13.
//  Copyright (c) 2013 MARKS. All rights reserved.
//

#import "NewsFeedViewController.h"
#import "NewsViewController.h"
#import "Requester.h"


@implementation NewsFeedViewController:UIViewController

@synthesize tableView;

-(void) createData:(NSData*) data{

    Requester * requester = [[Requester alloc] init];
    
    NSArray * res = [requester ConvertToArray:data];
    
    NSString * tmp;
    
    NSArray * narray = [[NSArray alloc] init];
    
    NSArray * ids = [[NSArray alloc] init];
    
    for (int i=0; i<[res count]; i++) {
        
        tmp = [res[i] valueForKey:@"Titulo"];
        
        narray = [narray arrayByAddingObject:tmp];
        
        tmp = [res[i] valueForKey:@"ID"];
        
        ids = [ids arrayByAddingObject:tmp];

        
    }
    
    theArray = narray;
    
    theIDS = ids;
    
}

-(NSInteger)numberOfSectionsInTableView:(UITableView *)tableView{
    
    return 1;
    
}

-(NSInteger)tableView:(UITableView *)tableView numberOfRowsInSection:(NSInteger)section{
    
    return [theArray count];
    
}


- (UITableViewCell *)tableView:(UITableView *)tableView cellForRowAtIndexPath:(NSIndexPath *)indexPath
{
    static NSString *simpleTableIdentifier = @"Entry";
    
    UITableViewCell *cell = [tableView dequeueReusableCellWithIdentifier:simpleTableIdentifier];
    
    if (cell == nil) {
        
        cell = [[UITableViewCell alloc] initWithStyle:UITableViewCellStyleDefault reuseIdentifier:simpleTableIdentifier];
    }
    
    cell.textLabel.text = [theArray objectAtIndex:indexPath.row];
    
    return cell;
}

- (void)prepareForSegue:(UIStoryboardSegue *)segue sender:(id)sender {
    
    if ([segue.identifier isEqualToString:@"nfton"]) {
       
        NSIndexPath *indexPath = [self.tableView indexPathForSelectedRow];
        
        NewsViewController *destViewController = segue.destinationViewController;
        
        destViewController.Title = [theArray objectAtIndex:indexPath.row];
        
        destViewController.Id = [theIDS objectAtIndex:indexPath.row];
        
    }
}

- (void)viewDidLoad
{
    
    Requester *requester = [[Requester alloc] init];
    
    NSDictionary * json  = [[NSDictionary alloc] initWithObjectsAndKeys:@"Fecha", @"OrderBy", nil];
    
    NSData *result = [requester SendJSONRequest:@"http://ada.uprrp.edu/~esantos/SecurityService/controllers/GetAllNews.php" :json];
    
    [self createData:result];
    
    [super viewDidLoad];
    
    // Set the gesture
    
}

- (void)didReceiveMemoryWarning
{
    [super didReceiveMemoryWarning];
    // Dispose of any resources that can be recreated.
}

@end
