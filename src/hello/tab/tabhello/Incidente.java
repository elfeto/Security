package hello.tab.tabhello;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.Spinner;
import android.widget.Toast;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class Incidente  extends Activity{

    private GoogleMap googleMap;
    Spinner spinner1;
	String [] type1;
	double [] lat;
	double [] lon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.map);
 
        try {
            initilizeMap();
            CameraUpdate center = CameraUpdateFactory.newLatLng(new LatLng(18.404731,-66.047779));
            CameraUpdate zoom = CameraUpdateFactory.zoomTo(15);
            googleMap.moveCamera(center);
            googleMap.animateCamera(zoom);
 
                Requester requester = new Requester();
				AsyncTask<String, String, String> result = requester.execute("http://136.145.181.66/Security/NewsMarkers/GetAllNewsMarkers.php","");
	        	JSONObject obj;
				JSONArray arr;
				
				try {
					obj = new JSONObject(result.get());
					arr = obj.getJSONArray("News");
					lat = new double[arr.length()];
					lon = new double[arr.length()];
					type1 = new String[arr.length()];
					for (int j = 0; j < arr.length(); j++)
					{ 
					    		lat[j] = Double.parseDouble(arr.getJSONObject(j).getString("Lat"));
					    		lon[j] = Double.parseDouble(arr.getJSONObject(j).getString("Lng"));
					    		type1[j] = arr.getJSONObject(j).getString("Titulo");
					}
			            	for (int nm = 0; nm < type1.length; nm++)
			      			{
			          	    		MarkerOptions marker = new MarkerOptions().position(new LatLng(lat[nm], lon[nm])).title(type1[nm]);
			          	    		googleMap.addMarker(marker);
			          	    }
				} catch (JSONException e) {
					e.printStackTrace();
				}
        } catch (Exception e) {
            e.printStackTrace();
        } 
    }

      
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
                googleMap = ((MapFragment) getFragmentManager().findFragmentById(
                        R.id.map)).getMap();
            }
            if (googleMap == null) {
                Toast.makeText(getApplicationContext(),
                        "Sorry! unable to create maps", Toast.LENGTH_SHORT)
                        .show();
                googleMap = ((MapFragment) getFragmentManager().findFragmentById(
                        R.id.map)).getMap();
            }
        }
    }
 
    @Override
    protected void onResume() {
        super.onResume();
        initilizeMap();
    }
 
}