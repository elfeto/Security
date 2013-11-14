package hello.tab.tabhello;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.TabActivity;
import android.content.Intent;
import android.content.res.Resources;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.webkit.WebView;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TabHost;
import android.widget.Toast;

import com.parse.Parse;
import com.parse.ParseACL;
import com.parse.ParseAnalytics;
import com.parse.ParseInstallation;
import com.parse.ParseUser;
import com.parse.PushService;

@SuppressWarnings("deprecation")
public class HelloTabActivity extends TabActivity {
    
    Button btnShowLocation;
    
    GPSTracker gps;
	
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        postData();
        Parse.initialize(this, "ngO0pJwU3VcWyanB4b2brukGVf8uBEkBLjwQCzYS", "eiaA5xVpO9FukZJyfRbkw6k2oL93OfqJH8bmTCAi"); 
        ParseAnalytics.trackAppOpened(getIntent());


        ParseUser.enableAutomaticUser();
		ParseACL defaultACL = new ParseACL();
	    
		// If you would like all objects to be private by default, remove this line.
		defaultACL.setPublicReadAccess(true);
		
		ParseACL.setDefaultACL(defaultACL, true);

		PushService.setDefaultPushCallback(this, HelloTabActivity.class);
		ParseInstallation.getCurrentInstallation().saveInBackground();
	
        setContentView(R.layout.main);
        
        Resources res = getResources();
       
        Intent i = new Intent(this,Simple.class);
        
        
        Intent m = new Intent(this, map.class);   
       
        Intent n = new Intent(this, mapi.class);

        
        TabHost mTabHst = getTabHost();
        mTabHst.setOnTabChangedListener(handler);
        
        WebView webview;
        webview = (WebView) findViewById(R.id.webView1);
        webview.getSettings().setJavaScriptEnabled(true);
        webview.loadUrl("http://ada.uprrp.edu/~ftorres/Seguridad/Trolley.html");
      
        
        WebView webview3;
        webview3 = (WebView) findViewById(R.id.webView3);
        webview3.getSettings().setJavaScriptEnabled(true);
        webview3.loadUrl("http://ada.uprrp.edu/~ftorres/Seguridad/Incidente.html");
        
        WebView webview4;
        webview4 = (WebView) findViewById(R.id.webView4);
        webview4.getSettings().setJavaScriptEnabled(true);
        webview4.loadUrl("http://ada.uprrp.edu/~ftorres/Seguridad/Telefono.php");        

        mTabHst.addTab(mTabHst.newTabSpec("tab_test1").setIndicator("News feed",res.getDrawable(R.drawable.three)).setContent(R.id.listView1));
        mTabHst.addTab(mTabHst.newTabSpec("tab_test2").setIndicator("Trolley").setContent(n));
        mTabHst.addTab(mTabHst.newTabSpec("tab_test3").setIndicator("Emergencias").setContent(m));
        mTabHst.addTab(mTabHst.newTabSpec("tab_test4").setIndicator("Incidentes").setContent(R.id.webView3)); 
        mTabHst.addTab(mTabHst.newTabSpec("tab_test5").setIndicator("Telefonos").setContent(R.id.webView4)); 
        mTabHst.addTab(mTabHst.newTabSpec("tab_test6").setIndicator("Reportar").setContent(i)); 
        
        mTabHst.setCurrentTab(0);
   
    }
    
    TabHost.OnTabChangeListener handler = new TabHost.OnTabChangeListener() {
		
		@Override
		public void onTabChanged(String tabId) {
			// TODO Auto-generated method stub
			if(tabId == "tab_test1")
			{
		        try {
					ListView myList = (ListView)findViewById(R.id.listView1);

			        Requester requester = new Requester();
					AsyncTask<String, String, String> result = requester.execute("http://136.145.181.66/~esantos/SecurityService/controllers/GetAllNews.php","");
			        			        
			        	JSONObject obj;
						JSONArray arr;
						try {
							obj = new JSONObject(result.get());
							arr = obj.getJSONArray("News");
							final String [] anArray;
							final String [] anArrayf;
							final String [] anArrayh;
							final String [] anArrayd;
							anArray = new String[arr.length()];
							anArrayf = new String[arr.length()];
							anArrayh = new String[arr.length()];
							anArrayd = new String[arr.length()];
							for (int j = 0; j < arr.length(); j++)
							{
							    String post_id0 = arr.getJSONObject(j).getString("Titulo");
							    String post_id1 = arr.getJSONObject(j).getString("Fecha");
							    String post_id2 = arr.getJSONObject(j).getString("Hora");
							    String post_id4 = arr.getJSONObject(j).getString("Data");
							   
							    anArray[j] = post_id0;
							    anArrayf[j] = post_id1;
							    anArrayh[j] = post_id2;
							    anArrayd[j] = post_id4;
							    
							}
							myList = (ListView) findViewById(R.id.listView1);
							myList.setAdapter(new ArrayAdapter<String>(HelloTabActivity.this,android.R.layout.simple_list_item_1, anArray));
							myList.setClickable(true);
							


							 myList.setOnItemClickListener(new OnItemClickListener() {
						            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
						              Intent intent = null;

						              int itemPosition     = position;

						              intent = new Intent(getBaseContext(), news.class);
						              String hola = anArray[itemPosition];
									  intent.putExtra("titulo", hola);
						              intent.putExtra("fecha", anArrayf[itemPosition]);
						              intent.putExtra("hora",  anArrayh[itemPosition]);
						              intent.putExtra("data", anArrayd[itemPosition]);
									  startActivity(intent);
						            }
						          });				
						} catch (JSONException e) {
							e.printStackTrace();
						}
		        } catch (Exception e) {
		            e.printStackTrace();
		        }
		    }
		}
	};
	/* Checks if external storage is available for read and write */
	public boolean isExternalStorageWritable() {
	    String state = Environment.getExternalStorageState();
	    if (Environment.MEDIA_MOUNTED.equals(state)) {
	        return true;
	    }
	    return false;
	}

	/* Checks if external storage is available to at least read */
	public boolean isExternalStorageReadable() {
	    String state = Environment.getExternalStorageState();
	    if (Environment.MEDIA_MOUNTED.equals(state) ||
	        Environment.MEDIA_MOUNTED_READ_ONLY.equals(state)) {
	        return true;
	    }
	    return false;
	}
	
	
	public void postData() {
	    // Create a new HttpClient and Post Header
	    double latitude = 0;
	    double longitude = 0;
	    btnShowLocation = (Button) findViewById(R.id.action_settings);
				     
		        gps = new GPSTracker(HelloTabActivity.this);
 		        if(gps.canGetLocation()){
		             
		            latitude = gps.getLatitude();
		            longitude = gps.getLongitude();
		             
		            Toast.makeText(getApplicationContext(), "Your Location is - \nLat: " + latitude + "\nLong: " + longitude, Toast.LENGTH_LONG).show();    
		            JSONObject json = new JSONObject();
		         JSONObject jsonlocation = new JSONObject();

		         try {
					 jsonlocation.put("lat", latitude);
			         jsonlocation.put("lng", longitude);
			         json.put("location",jsonlocation);

				} catch (JSONException e) {
					e.printStackTrace();
				}  

		        }else{
		            gps.showSettingsAlert();
		        }
	}
}