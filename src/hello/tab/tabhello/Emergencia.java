package hello.tab.tabhello;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.location.LocationManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.StrictMode;
import android.provider.Settings;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.model.LatLng;


public class Emergencia extends Activity{
    double latitude = 0;
    double longitude = 0;
    Button btnShowLocation;
    GPSTracker gps;
    private GoogleMap googleMap;

    

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.map);
        LocationManager service = (LocationManager) getSystemService(LOCATION_SERVICE);
        boolean enabled = service.isProviderEnabled(LocationManager.GPS_PROVIDER);
        if (!enabled) {
          Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
          startActivity(intent);
        } 
        gps = new GPSTracker(Emergencia.this);
		if(gps.canGetLocation()){
		 
		latitude = gps.getLatitude();
		longitude = gps.getLongitude();
		}
		String lat = Double.toString(latitude);
		String lng = Double.toString(longitude);
		PostData(lat, lng);
		try{
        initilizeMap();
        CameraUpdate center = CameraUpdateFactory.newLatLng(new LatLng(18.404731,-66.047779));
        CameraUpdate zoom = CameraUpdateFactory.zoomTo(15);
        googleMap.moveCamera(center);
        googleMap.animateCamera(zoom);
		} catch (Exception e) {
            e.printStackTrace();
        } 

    }
    public void PostData(String Lat, String Lng) {
    	// Create a new HttpClient and Post Header
    	StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        String url = "http://136.145.181.66/Security/SendEvacNow.php";


    	// Add your data
    		List<NameValuePair> parameters = new ArrayList<NameValuePair>();
            parameters.add(new BasicNameValuePair("Lat",Lat));
            parameters.add(new BasicNameValuePair("Lng", Lng));

          DefaultHttpClient httpClient = new DefaultHttpClient();

          HttpPost httpPost = new HttpPost(url); // You can use get method too here if you use get at the php scripting side to receive any values.

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
	                Toast.makeText(getApplicationContext(), (CharSequence) builder, Toast.LENGTH_LONG).show();
	                is.close();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

    	
    	}//end postData()
    /**
     * function to load map. If map is not created it will create it for you
     * */
    private void initilizeMap() {
        if (googleMap == null) {
            googleMap = ((MapFragment) getFragmentManager().findFragmentById(
                    R.id.map)).getMap();
 
            // check if map is created successfully or not
            if (googleMap == null) {
                Toast.makeText(getApplicationContext(),
                        "Sorry! unable to create maps", Toast.LENGTH_SHORT)
                        .show();
            }
        }
    }
    private static String convertStreamToString(InputStream is) {
        /*
         * To convert the InputStream to String we use the BufferedReader.readLine()
         * method. We iterate until the BufferedReader return null which means
         * there's no more data to read. Each line will appended to a StringBuilder
         * and returned as String.
         */
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        StringBuilder sb = new StringBuilder();

        String line = null;
        try {
            while ((line = reader.readLine()) != null) {
                sb.append(line + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return sb.toString();
    }//END convertStreamToString()
    @Override
    protected void onResume() {
        super.onResume();
        initilizeMap();
    }
	public void postData() {
	    // Create a new HttpClient and Post Header
	    btnShowLocation = (Button) findViewById(R.id.action_settings);
				     
		        gps = new GPSTracker(Emergencia.this);
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