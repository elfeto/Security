package hello.tab.tabhello;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import android.app.Activity;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.provider.Settings.Secure;

public class Simple extends Activity {
    Spinner spinner1;
    GPSTracker gps;
    double latitude = 0;
    double longitude = 0;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.simple);
 
        List<String> SpinnerArray = new ArrayList<String>();

        SpinnerArray.add("Administracion de Empresas");
        SpinnerArray.add("Arquitectura");
        SpinnerArray.add("Arte");
        SpinnerArray.add("Ciencias naturales");
        SpinnerArray.add("Ciencias Sociales");
        SpinnerArray.add("Complejo deportivo");
        SpinnerArray.add("Derecho");
        SpinnerArray.add("Educacion");
        SpinnerArray.add("Escuela de Comunicaci—n");
        SpinnerArray.add("Escuela Graduada de Planificaci—n");
        SpinnerArray.add("Estudios Generales");
        SpinnerArray.add("Humanidades");
        SpinnerArray.add("Museo");
        SpinnerArray.add("ROTC");

		ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, SpinnerArray);
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner1 = (Spinner) findViewById(R.id.spinner2);                                       
        spinner1.setAdapter(adapter1);

        gps = new GPSTracker(Simple.this);
	        if(gps.canGetLocation()){
             
            latitude = gps.getLatitude();
            longitude = gps.getLongitude();
	        }        
        
          Button  rtn = (Button)findViewById(R.id.button1);
          final EditText edit_text   = (EditText)findViewById(R.id.editText1);

          final EditText edit_text2   = (EditText)findViewById(R.id.editText2);
            rtn .setOnClickListener(
                new View.OnClickListener()
                {
                    public void onClick(View view)
                    {
                    	
                    	
                   	 	String Faculty = String.valueOf(spinner1.getSelectedItem());

                        String value = edit_text.getText().toString();
                        String value2 = edit_text2.getText().toString();
                        String android_id = Secure.getString(getBaseContext().getContentResolver(),Secure.ANDROID_ID);

                        Date anotherCurDate = new Date();  
                        SimpleDateFormat formatter = new SimpleDateFormat("EEEE, MMMM d 'at' hh:mm a 'year' yyyy");  
                        String formattedDateString = formatter.format(anotherCurDate); 

                        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
                        StrictMode.setThreadPolicy(policy);
                        String url = "http://136.145.181.66/Security/recv.php";
                        List<NameValuePair> parameters = new ArrayList<NameValuePair>();
                                    parameters.add(new BasicNameValuePair("Reportar",value));
                                    parameters.add(new BasicNameValuePair("Tittle",value2));
                                    parameters.add(new BasicNameValuePair("ID",android_id));
                                    parameters.add(new BasicNameValuePair("Date",formattedDateString));
                                    parameters.add(new BasicNameValuePair("Faculty",Faculty));

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
                	                //Toast.makeText(getApplicationContext(), "Reporte enviado!", Toast.LENGTH_LONG).show();
                	                is.close();
                		} catch (UnsupportedEncodingException e) {
                			// TODO Auto-generated catch block
                			e.printStackTrace();
                		} catch (IOException e) {
                			// TODO Auto-generated catch block
                			e.printStackTrace();
                		}
                    }
                });         
    }
}