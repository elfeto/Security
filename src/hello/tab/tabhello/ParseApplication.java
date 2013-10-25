package hello.tab.tabhello;

import android.app.Application;

import com.parse.Parse;
import com.parse.ParseACL;
import com.parse.ParseInstallation;
import com.parse.ParseUser;
import com.parse.PushService;

public class ParseApplication extends Application {

	@Override
	public void onCreate() {
		super.onCreate();

		// Add your initialization code here
		Parse.initialize(this, "OV2F4PDgqOyzCIWhGtqCesRsKmIIr2BAn18IOAQE", "VVfj9uf2NQIoNWpcdoY51x5KaiC4s0aFPcdkMnjh"); 


		ParseUser.enableAutomaticUser();
		ParseACL defaultACL = new ParseACL();
	    
		// If you would like all objects to be private by default, remove this line.
		defaultACL.setPublicReadAccess(true);
		
		ParseACL.setDefaultACL(defaultACL, true);

		PushService.setDefaultPushCallback(this, HelloTabActivity.class);
		ParseInstallation.getCurrentInstallation().saveInBackground();
	}

}

