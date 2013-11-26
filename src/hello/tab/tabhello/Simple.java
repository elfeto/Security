package hello.tab.tabhello;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.text.DateFormat;
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
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.provider.Settings.Secure;

public class Simple extends Activity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.simple);
 



          Button  rtn = (Button)findViewById(R.id.button1);
          final EditText edit_text   = (EditText)findViewById(R.id.editText1);

            rtn .setOnClickListener(
                new View.OnClickListener()
                {
                    public void onClick(View view)
                    {
                        String value = edit_text.getText().toString();
                        String android_id = Secure.getString(getBaseContext().getContentResolver(),Secure.ANDROID_ID);

                        Date anotherCurDate = new Date();  
                        SimpleDateFormat formatter = new SimpleDateFormat("EEEE, MMMM d 'at' hh:mm a 'year' yyyy");  
                        String formattedDateString = formatter.format(anotherCurDate); 
                        
                        
                        //Toast.makeText(getApplicationContext(), formattedDateString, Toast.LENGTH_LONG).show();

                        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
                        StrictMode.setThreadPolicy(policy);
                        String url = "http://136.145.181.66/~ftorres/Seguridad/recv.php";
                        List<NameValuePair> parameters = new ArrayList<NameValuePair>();
                                    parameters.add(new BasicNameValuePair("Reportar",value));
                                    parameters.add(new BasicNameValuePair("ID",android_id));
                                    parameters.add(new BasicNameValuePair("Date",formattedDateString));
                                    // These are the namevalue pairs which you may want to send to your php file. Below is the method post used to send these parameters

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