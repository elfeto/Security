package hello.tab.tabhello;

import java.io.IOException;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicHeader;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.json.JSONException;
import org.json.JSONObject;

import android.os.AsyncTask;

public class Requester extends AsyncTask <String, String, String>{
	
	public String results = "ERROR";
	
	@Override
	protected String doInBackground(String... params) {
		
		boolean hasJson = true;
		
	    JSONObject json = null;
	            
		try {
					
			json = new JSONObject(params[1]);
				
		} catch (JSONException e1) {

			//e1.printStackTrace();
			
			hasJson = false;
			
		}
		
		if(hasJson){
		        
		    try{
			            
		    	HttpClient hc = new DefaultHttpClient();
			            
			    HttpPost post = new HttpPost(params[0]);
			            
			    StringEntity se = new StringEntity(json.toString());  
			            
			    se.setContentType(new BasicHeader(HTTP.CONTENT_TYPE, "application/json"));
			            
			    post.setEntity(se);
			            
			    HttpResponse rp = hc.execute(post);
			
			    if(rp.getStatusLine().getStatusCode() == HttpStatus.SC_OK){
			            
			    	results = EntityUtils.toString(rp.getEntity());
			            
			    }
	
		    }
			        
			catch(IOException e){
			        
				e.printStackTrace();
			        
			}
		    
		}
		
		else {
			
		    try{
	            
		    	HttpClient hc = new DefaultHttpClient();
			            
			    HttpPost post = new HttpPost(params[0]);
			            
			    HttpResponse rp = hc.execute(post);
			
			    if(rp.getStatusLine().getStatusCode() == HttpStatus.SC_OK){
			            
			    	results = EntityUtils.toString(rp.getEntity());
			            
			    }
	
		    }
			        
			catch(IOException e){
			        
				e.printStackTrace();
			        
			}
			
		}
	        	
	    return results;
	
	}
	
}