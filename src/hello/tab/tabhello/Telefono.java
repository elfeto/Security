package hello.tab.tabhello;

import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;



public class Telefono  extends Activity{

    private GoogleMap googleMap;
    Spinner spinner1;
	String [] type1;
	double [] lat;
	double [] lon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.telefono);
 
        try {
            initilizeMap();
 
            CameraUpdate center = CameraUpdateFactory.newLatLng(new LatLng(18.404731,-66.047779));
            CameraUpdate zoom = CameraUpdateFactory.zoomTo(15);
            googleMap.moveCamera(center);
            googleMap.animateCamera(zoom);
 
                Requester requester = new Requester();
				AsyncTask<String, String, String> result = requester.execute("http://136.145.181.66/Security/Markers/GetAllMarkers.php","");
	        	JSONObject obj;
				JSONArray arr;
				
		        List<String> SpinnerArray = new ArrayList<String>();
				try {
					obj = new JSONObject(result.get());
					arr = obj.getJSONArray("Phones");
					lat = new double[arr.length()];
					lon = new double[arr.length()];
					type1 = new String[arr.length()];
					for (int j = 0; j < arr.length(); j++)
					{ 
					    		lat[j] = Double.parseDouble(arr.getJSONObject(j).getString("Lat"));
					    		lon[j] = Double.parseDouble(arr.getJSONObject(j).getString("Lng"));
					    		type1[j] = arr.getJSONObject(j).getString("Type");
					    		
					    		if(j==0)
					    		{
							        SpinnerArray.add(type1[0]);
					    		}
					    		else
					    		{
					    			int i = 0;
					    			for (String s : SpinnerArray)
					    			    if (s.equals(type1[j]))
					    			    {
					    			    	i = 1;
					    			    }
					    			if(i == 0)
					    			{
					    				SpinnerArray.add(type1[j]);
					    			}
					    		}
					}	
					ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, SpinnerArray);
			        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
			        spinner1 = (Spinner) findViewById(R.id.spinner1);                                       
			        spinner1.setAdapter(adapter1);
			    	spinner1.setOnItemSelectedListener(new  AdapterView.OnItemSelectedListener() { 

			        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) { 
			             // TODO Auto-generated method stub
			            	 String Name = String.valueOf(spinner1.getSelectedItem());
			            	googleMap.clear();
			            	for (int nm = 0; nm < type1.length; nm++)
			      			{
			          	    	if(Name.equalsIgnoreCase(type1[nm]))
			            		{
			          	    		MarkerOptions marker = new MarkerOptions().position(new LatLng(lat[nm], lon[nm])).title(type1[nm]);
			          	    		googleMap.addMarker(marker);
			          	    	}
			          	    }
			               }
			                // If no option selected
			    public void onNothingSelected(AdapterView<?> arg0) {
			     // TODO Auto-generated method stub
			          
			    } 

			        });

					                
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