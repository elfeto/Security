package hello.tab.tabhello;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.concurrent.ExecutionException;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.R.string;
import android.app.TabActivity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TabHost;
import android.widget.TextView;

@SuppressWarnings("deprecation")
public class HelloTabActivity extends TabActivity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        setContentView(R.layout.main);
        
        Resources res = getResources();
        Intent i = new Intent(this,Simple.class);
        
        TabHost mTabHst = getTabHost();
        //,res.getDrawable(R.drawable.one)
        mTabHst.setOnTabChangedListener(handler);

        
        
        mTabHst.addTab(mTabHst.newTabSpec("tab_test1").setIndicator("News feed").setContent(i));
        mTabHst.addTab(mTabHst.newTabSpec("tab_test2").setIndicator("Trolley").setContent(i));
        mTabHst.addTab(mTabHst.newTabSpec("tab_test3").setIndicator("Emergencias").setContent(i));
        mTabHst.setCurrentTab(0);

    }
    
    TabHost.OnTabChangeListener handler = new TabHost.OnTabChangeListener() {
		
		@Override
		public void onTabChanged(String tabId) {
			// TODO Auto-generated method stub
	        
			//System.out.print(tabId);
			if(tabId == "tab_test1")
			{
		        try {
					ListView myList = (ListView)findViewById(R.id.listView1);

			        Requester requester = new Requester();
					AsyncTask<String, String, String> result = requester.execute("http://136.145.181.66/~esantos/SecurityService/controllers/GetAllNews.php","");
			        writeToFile(result.get());
			        String S = readFromFile();
			        
						JSONObject obj;
						JSONArray arr;
						try {
							obj = new JSONObject(S);
							arr = obj.getJSONArray("News");
							String [] anArray;
							anArray = new String[arr.length()];
							for (int j = 0; j < arr.length(); j++)
							{
							    String post_id0 = arr.getJSONObject(j).getString("Titulo");
							    //String post_id1 = arr.getJSONObject(j).getString("Fecha");
							    //String post_id2 = arr.getJSONObject(j).getString("Hora");
							    //String post_id3 = arr.getJSONObject(j).getString("Autor");
							    //String post_id4 = arr.getJSONObject(j).getString("Data");
							    //String post_id5 = arr.getJSONObject(j).getString("ID");
							    //String post_id6 = arr.getJSONObject(j).getString("OID");
							   
							    anArray[j] = post_id0;
							}
							myList = (ListView) findViewById(R.id.listView1);
							myList.setAdapter(new ArrayAdapter<String>(HelloTabActivity.this,android.R.layout.simple_list_item_1, anArray));
							myList.setClickable(true);
							
							//Helper.getListViewSize(myList);

							
							
							myList.setOnItemClickListener(new OnItemClickListener() {
						        public void onItemClick(AdapterView<?> parent, View view,
						            int position, long id) {
						          // When clicked, show a toast with the TextView text
						if(position == 1)
						{
						//code specific to first list item    
						         Intent myIntent = new Intent(view.getContext(), HelloTabActivity.class);
						             startActivityForResult(myIntent, 0);
						}

						if(position == 2)
						{
						//code specific to 2nd list item    
						         Intent myIntent = new Intent(view.getContext(), HelloTabActivity.class);
						             startActivityForResult(myIntent, 0);
						}
						    }
						  });
							
							
							
						} catch (JSONException e) {
							e.printStackTrace();
						//} catch (InterruptedException e) {
						//	e.printStackTrace();
						//} catch (ExecutionException e) {
						//	e.printStackTrace();
						}
		        } catch (Exception e) {
		            e.printStackTrace();
		        }			}
			else if(tabId == "tab_test2")
			{
				
				//TextView myTExt = (TextView)findViewById(R.id.textView1);
		        //myTExt.setTextColor(Color.BLUE);
				//myTExt.setText(tabId);
		        
				
			}
			else if(tabId == "tab_test3")
			{
				//TextView mYText = (TextView)findViewById(R.id.textView1);
		        //mYText.setTextColor(Color.BLUE);
				//mYText.setText(tabId);
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
	private void writeToFile(String data) {
	    try {
	        OutputStreamWriter outputStreamWriter = new OutputStreamWriter(openFileOutput("config.txt", Context.MODE_PRIVATE));
	        outputStreamWriter.write(data);
	        outputStreamWriter.close();
	    }
	    catch (IOException e) {
	        Log.e("Exception", "File write failed: " + e.toString());
	    } 
	}
	private String readFromFile() {

	    String ret = "";

	    try {
	        InputStream inputStream = openFileInput("config.txt");

	        if ( inputStream != null ) {
	            InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
	            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
	            String receiveString = "";
	            StringBuilder stringBuilder = new StringBuilder();

	            while ( (receiveString = bufferedReader.readLine()) != null ) {
	                stringBuilder.append(receiveString);
	            }

	            inputStream.close();
	            ret = stringBuilder.toString();
	        }
	    }
	    catch (FileNotFoundException e) {
	        Log.e("login activity", "File not found: " + e.toString());
	    } catch (IOException e) {
	        Log.e("login activity", "Can not read file: " + e.toString());
	    }

	    return ret;
	}
	
}