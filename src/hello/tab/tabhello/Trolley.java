package hello.tab.tabhello;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.LatLng;

 
public class Trolley  extends Activity{

    // Google Map
    private GoogleMap googleMap;
 
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.map);
 
        try {
            // Loading map
            initilizeMap();
            CameraUpdate center = CameraUpdateFactory.newLatLng(new LatLng(18.404731,-66.047779));
            CameraUpdate zoom = CameraUpdateFactory.zoomTo(15);
                googleMap.moveCamera(center);
                googleMap.animateCamera(zoom);
 
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
            }
        }
    }
 
    @Override
    protected void onResume() {
        super.onResume();
        initilizeMap();
    }
 
}


/*
 * Para enviar al usuario a la pagina de settings para que ponga el GPS
 * LocationManager service = (LocationManager) getSystemService(LOCATION_SERVICE);
boolean enabled = service
  .isProviderEnabled(LocationManager.GPS_PROVIDER);

// check if enabled and if not send user to the GSP settings
// Better solution would be to display a dialog and suggesting to 
// go to the settings
if (!enabled) {
  Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
  startActivity(intent);
}  
 */
