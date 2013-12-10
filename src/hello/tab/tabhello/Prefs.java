package hello.tab.tabhello;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.SharedPreferences.OnSharedPreferenceChangeListener;
import android.os.Bundle;
import android.os.StrictMode;
import android.preference.CheckBoxPreference;
import android.preference.EditTextPreference;
import android.preference.Preference;
import android.preference.PreferenceActivity;
import android.preference.PreferenceManager;
import android.provider.Settings.Secure;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

import com.parse.ParseInstallation;
import com.parse.PushService;

public class Prefs extends PreferenceActivity implements OnSharedPreferenceChangeListener {
	final Context context = this;
	
	
	
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
	
		super.onCreate(savedInstanceState);
		addPreferencesFromResource(R.layout.settings);
		
		
	}
	
	
	
		
		
		
	
	
	
	
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		 case 0:
			 
			
			 return true;
		}
	
		return false;
	}
	@Override
	protected void onResume() {
	    super.onResume();
	    // Set up a listener whenever a key changes
	    getPreferenceScreen().getSharedPreferences()
	            .registerOnSharedPreferenceChangeListener(this);
	}

	@Override
	protected void onPause() {
	    super.onPause();
	    // Unregister the listener whenever a key changes
	    getPreferenceScreen().getSharedPreferences()
	            .unregisterOnSharedPreferenceChangeListener(this);
	}
	@Override
	 public void onSharedPreferenceChanged(SharedPreferences sharedPreferences,String key) {
		
		
		LayoutInflater layoutInflater = LayoutInflater.from(context);
		
		View promptView = layoutInflater.inflate(R.layout.prompts, null);

		AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);

		// set prompts.xml to be the layout file of the alertdialog builder
		alertDialogBuilder.setView(promptView);

		final EditText input = (EditText) promptView.findViewById(R.id.X);
		
		

		
		
		if (key.equals("Notifications")){
			CheckBoxPreference pref = (CheckBoxPreference) findPreference("Text");
			Preference connectionPref = findPreference(key);
			
			if(pref.isChecked()){
				PushService.subscribe(context, "Security",HelloTabActivity.class);
				ParseInstallation.getCurrentInstallation().saveInBackground();
			}
			else {
				PushService.unsubscribe(context, "Security");
				ParseInstallation.getCurrentInstallation().saveInBackground();
				
			}
			
		}
		if(key.equals("Text")){
			Preference Pref = findPreference("Text");
			Preference connectionPref = findPreference("Phone");
			CheckBoxPreference pref = (CheckBoxPreference) findPreference("Text");
			EditTextPreference editTextPreference =  (EditTextPreference)connectionPref; 
			
			
			
             if(pref.isChecked())  { 
           
			if (editTextPreference.getText().trim().length() == 0){
					//\\while(){
                	
                	alertDialogBuilder
					.setCancelable(false)
					.setTitle("Put phone number")
					.setPositiveButton("insert", new DialogInterface.OnClickListener() {
								public void onClick(DialogInterface dialog, int id) {
									Preference connectionPref = findPreference("Phone");
									EditTextPreference editTextPreference =  (EditTextPreference)connectionPref;
									
									editTextPreference.setText(input.getText().toString());
								}}
							)
					.setNegativeButton("Cancel",
							new DialogInterface.OnClickListener() {
								public void onClick(DialogInterface dialog,	int id) {
				
									dialog.cancel();
								}
							});

			// create an alert dialog
			AlertDialog alertD = alertDialogBuilder.create();
			alertD.show();
			}
			StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
	        StrictMode.setThreadPolicy(policy);
			  String url = "http://136.145.181.66/~onieves/JASON/recv.php";
			  String android_id = Secure.getString(getBaseContext().getContentResolver(),Secure.ANDROID_ID);
	          
				List<NameValuePair> parameters = new ArrayList<NameValuePair>();
	          
			  parameters.add(new BasicNameValuePair("ID",android_id));
	          parameters.add(new BasicNameValuePair("BOOL","1"));
	          DefaultHttpClient httpClient = new DefaultHttpClient();
	          HttpPost httpPost = new HttpPost(url);
	          try {
	  			httpPost.setEntity(new UrlEncodedFormEntity(parameters));
	  	          HttpResponse httpResponse = httpClient.execute(httpPost);

	  	          HttpEntity httpEntity = httpResponse.getEntity();

	  	          InputStream is = httpEntity.getContent();

	  	           BufferedReader reader = new BufferedReader(new InputStreamReader(is, "iso-8859-1"), 8); // From here you can extract the data that you get from your php file..

	  	                StringBuilder builder = new StringBuilder();

	  	                String line = null;

	  	                while ((line = reader.readLine()) != null) {
	  	                    builder.append(line + "\n");
	  	                }

	  	                is.close();
	  		}catch (UnsupportedEncodingException e) {
	  			// TODO Auto-generated catch block
	  			e.printStackTrace();
	  		} catch (IOException e) {
	  			// TODO Auto-generated catch block
	  			e.printStackTrace();
	  		}
			
		
			
			
			

                	//editTextPreference.get
			}
             else{ 
				StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
		        StrictMode.setThreadPolicy(policy);
				  String url = "http://136.145.181.66/~onieves/JASON/recv.php";
				  String android_id = Secure.getString(getBaseContext().getContentResolver(),Secure.ANDROID_ID);
		          
					List<NameValuePair> parameters = new ArrayList<NameValuePair>();
		          
				  parameters.add(new BasicNameValuePair("ID",android_id));
		          parameters.add(new BasicNameValuePair("BOOL","0"));
		          DefaultHttpClient httpClient = new DefaultHttpClient();
		          HttpPost httpPost = new HttpPost(url);
		          try {
		  			httpPost.setEntity(new UrlEncodedFormEntity(parameters));
		  	          HttpResponse httpResponse = httpClient.execute(httpPost);

		  	          HttpEntity httpEntity = httpResponse.getEntity();

		  	          InputStream is = httpEntity.getContent();

		  	           BufferedReader reader = new BufferedReader(new InputStreamReader(is, "iso-8859-1"), 8); // From here you can extract the data that you get from your php file..

		  	                StringBuilder builder = new StringBuilder();

		  	                String line = null;

		  	                while ((line = reader.readLine()) != null) {
		  	                    builder.append(line + "\n");
		  	                }

		  	                is.close();
		  		}catch (UnsupportedEncodingException e) {
		  			// TODO Auto-generated catch block
		  			e.printStackTrace();
		  		} catch (IOException e) {
		  			// TODO Auto-generated catch block
		  			e.printStackTrace();
		  		}
				
			}
          
           
             }
		if(key.equals("Email")){
			Preference Pref = findPreference("Email");
			Preference connectionPref = findPreference("Emailaccount");
			CheckBoxPreference pref = (CheckBoxPreference) findPreference("Email");
			EditTextPreference editTextPreference =  (EditTextPreference)connectionPref;
             if(pref.isChecked())  { 
			if (editTextPreference.getText().trim().length() == 0){
                	
                	alertDialogBuilder
					.setCancelable(false)
					.setTitle("Insert email account ")
					.setPositiveButton("Insert", new DialogInterface.OnClickListener() {
								public void onClick(DialogInterface dialog, int id) {
									Preference connectionPref = findPreference("Emailaccount");
									EditTextPreference editTextPreference =  (EditTextPreference)connectionPref;
									// get user input and set it to result
									editTextPreference.setText(input.getText().toString());
								}
							})
					.setNegativeButton("Cancel",
							new DialogInterface.OnClickListener() {
								public void onClick(DialogInterface dialog,	int id) {
									dialog.cancel();
								}
							});

			// create an alert dialog
			AlertDialog alertD = alertDialogBuilder.create();
			alertD.show();
			

                	//editTextPreference.get
                }
			StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
	        StrictMode.setThreadPolicy(policy);
			  String url = "http://136.145.181.66/~onieves/JASON/recv.php";
			  String android_id = Secure.getString(getBaseContext().getContentResolver(),Secure.ANDROID_ID);
	          
				List<NameValuePair> parameters = new ArrayList<NameValuePair>();
	          
			  parameters.add(new BasicNameValuePair("ID",android_id));
	          parameters.add(new BasicNameValuePair("EBOOL","1"));
	          DefaultHttpClient httpClient = new DefaultHttpClient();
	          HttpPost httpPost = new HttpPost(url);
	          try {
	  			httpPost.setEntity(new UrlEncodedFormEntity(parameters));
	  	          HttpResponse httpResponse = httpClient.execute(httpPost);

	  	          HttpEntity httpEntity = httpResponse.getEntity();

	  	          InputStream is = httpEntity.getContent();

	  	           BufferedReader reader = new BufferedReader(new InputStreamReader(is, "iso-8859-1"), 8); // From here you can extract the data that you get from your php file..

	  	                StringBuilder builder = new StringBuilder();

	  	                String line = null;

	  	                while ((line = reader.readLine()) != null) {
	  	                    builder.append(line + "\n");
	  	                }

	  	                is.close();
	  		}catch (UnsupportedEncodingException e) {
	  			// TODO Auto-generated catch block
	  			e.printStackTrace();
	  		} catch (IOException e) {
	  			// TODO Auto-generated catch block
	  			e.printStackTrace();
	  		}
			
                
			
			
			
			
}else{ StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
StrictMode.setThreadPolicy(policy);
  String url = "http://136.145.181.66/~onieves/JASON/recv.php";
  String android_id = Secure.getString(getBaseContext().getContentResolver(),Secure.ANDROID_ID);
  
	List<NameValuePair> parameters = new ArrayList<NameValuePair>();
  
  parameters.add(new BasicNameValuePair("ID",android_id));
  parameters.add(new BasicNameValuePair("EBOOL","0"));
  DefaultHttpClient httpClient = new DefaultHttpClient();
  HttpPost httpPost = new HttpPost(url);
  try {
		httpPost.setEntity(new UrlEncodedFormEntity(parameters));
        HttpResponse httpResponse = httpClient.execute(httpPost);

        HttpEntity httpEntity = httpResponse.getEntity();

        InputStream is = httpEntity.getContent();

         BufferedReader reader = new BufferedReader(new InputStreamReader(is, "iso-8859-1"), 8); // From here you can extract the data that you get from your php file..

              StringBuilder builder = new StringBuilder();

              String line = null;

              while ((line = reader.readLine()) != null) {
                  builder.append(line + "\n");
              }

              is.close();
	}catch (UnsupportedEncodingException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
}
			
			
			
		}
		if(key.equals("Phone")){
			Preference connectionPref = findPreference("Phone");
			EditTextPreference editTextPreference =  (EditTextPreference)connectionPref;
			if(editTextPreference.getText().trim().length() != 0){
			StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
	        StrictMode.setThreadPolicy(policy);
			  String url = "http://136.145.181.66/~onieves/JASON/recv.php";
			  String android_id = Secure.getString(getBaseContext().getContentResolver(),Secure.ANDROID_ID);
	          
				List<NameValuePair> parameters = new ArrayList<NameValuePair>();
	          parameters.add(new BasicNameValuePair("ID",android_id));
	          parameters.add(new BasicNameValuePair("Phone",editTextPreference.getText().toString()));
	          parameters.add(new BasicNameValuePair("BOOL","1"));
	          DefaultHttpClient httpClient = new DefaultHttpClient();
	          HttpPost httpPost = new HttpPost(url);
	          try {
	  			httpPost.setEntity(new UrlEncodedFormEntity(parameters));
	  	          HttpResponse httpResponse = httpClient.execute(httpPost);

	  	          HttpEntity httpEntity = httpResponse.getEntity();

	  	          InputStream is = httpEntity.getContent();

	  	           BufferedReader reader = new BufferedReader(new InputStreamReader(is, "iso-8859-1"), 8); // From here you can extract the data that you get from your php file..

	  	                StringBuilder builder = new StringBuilder();

	  	                String line = null;

	  	                while ((line = reader.readLine()) != null) {
	  	                    builder.append(line + "\n");
	  	                }

	  	                is.close();
	  		}catch (UnsupportedEncodingException e) {
	  			// TODO Auto-generated catch block
	  			e.printStackTrace();
	  		} catch (IOException e) {
	  			// TODO Auto-generated catch block
	  			e.printStackTrace();
	  		}}
			
		}
		if(key.equals("Emailaccount")){
			Preference connectionPref = findPreference("Emailaccount");
			EditTextPreference editTextPreference =  (EditTextPreference)connectionPref;
			if(editTextPreference.getText().trim().length() != 0){
			StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
	        StrictMode.setThreadPolicy(policy);
			  String url = "http://136.145.181.66/~onieves/JASON/recv.php";
			  String android_id = Secure.getString(getBaseContext().getContentResolver(),Secure.ANDROID_ID);
	          
				List<NameValuePair> parameters = new ArrayList<NameValuePair>();
	          parameters.add(new BasicNameValuePair("ID",android_id));
	          parameters.add(new BasicNameValuePair("Email",editTextPreference.getText().toString()));
	          parameters.add(new BasicNameValuePair("EBOOL","1"));
	          DefaultHttpClient httpClient = new DefaultHttpClient();
	          HttpPost httpPost = new HttpPost(url);
	          try {
	  			httpPost.setEntity(new UrlEncodedFormEntity(parameters));
	  	          HttpResponse httpResponse = httpClient.execute(httpPost);

	  	          HttpEntity httpEntity = httpResponse.getEntity();

	  	          InputStream is = httpEntity.getContent();

	  	           BufferedReader reader = new BufferedReader(new InputStreamReader(is, "iso-8859-1"), 8); // From here you can extract the data that you get from your php file..

	  	                StringBuilder builder = new StringBuilder();

	  	                String line = null;

	  	                while ((line = reader.readLine()) != null) {
	  	                    builder.append(line + "\n");
	  	                }

	  	                is.close();
	  		}catch (UnsupportedEncodingException e) {
	  			// TODO Auto-generated catch block
	  			e.printStackTrace();
	  		} catch (IOException e) {
	  			// TODO Auto-generated catch block
	  			e.printStackTrace();
	  		}}
			
		}
		
		
		
	}}
	
	
	

