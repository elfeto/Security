package hello.tab.tabhello;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;


public class Telefono  extends Activity{

    // Google Map
    private GoogleMap googleMap;

    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.telefono);
 
        try {
            // Loading map
            initilizeMap();
 
            CameraUpdate center = CameraUpdateFactory.newLatLng(new LatLng(18.404731,-66.047779));
            CameraUpdate zoom = CameraUpdateFactory.zoomTo(15);
                googleMap.moveCamera(center);
                googleMap.animateCamera(zoom);
 
                
                Requester requester = new Requester();
				AsyncTask<String, String, String> result = requester.execute("http://136.145.181.66/Security/GetAllNews.php","");
	        	JSONObject obj;
				JSONArray arr;
				final double [] lat;
				final double [] lon;
				final String [] type;
				try {
					obj = new JSONObject(result.get());
					arr = obj.getJSONArray("Phones");
					lat = new double[arr.length()];
					lon = new double[arr.length()];
					type = new String[arr.length()];
					for (int j = 0; j < arr.length(); j++)
					{
					    		String q = arr.getJSONObject(j).getString("Lat");
					    		String a = arr.getJSONObject(j).getString("Lng");
					    		type[j] = arr.getJSONObject(j).getString("Type");
					    		lat[j] = Double.parseDouble(q);
					    		lon[j] = Double.parseDouble(a);
					    
					}
					for (int h = 0; h < arr.length(); h++)
					{
		                MarkerOptions marker = new MarkerOptions().position(new LatLng(lat[h], lon[h])).title("Center of the map ");
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
                    R.id.telefono)).getMap();
 
            // check if map is created successfully or not
            if (googleMap == null) {
                Toast.makeText(getApplicationContext(),
                        "Sorry! unable to create maps", Toast.LENGTH_SHORT)
                        .show();
            }
        }
    }
 
    @Override
    protected void onResume() {
        super.onResume();
        initilizeMap();
    }
 
}