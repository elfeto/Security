package hello.tab.tabhello;

import java.util.Vector;
import java.util.concurrent.ExecutionException;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.TabActivity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
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
			TextView myText = (TextView)findViewById(R.id.textView1);
	        myText.setText(tabId);
	        myText.setTextColor(Color.BLUE);
	        
			System.out.print(tabId);
			if(tabId == "tab_test1")
			{
				Requester requester = new Requester();
				AsyncTask<String, String, String> result = requester.execute("http://136.145.181.66/~esantos/SecurityService/controllers/GetAllNews.php","");
				//JSONArray pageName;
					JSONObject obj;
					JSONArray arr;
					try {
						obj = new JSONObject(result.get());
						arr = obj.getJSONArray("News");
						for (int j = 0; j < arr.length(); j++)
						{
						    String post_id0 = arr.getJSONObject(j).getString("Titulo");
						    String post_id1 = arr.getJSONObject(j).getString("Fecha");
						    String post_id2 = arr.getJSONObject(j).getString("Hora");
						    String post_id3 = arr.getJSONObject(j).getString("Autor");
						    String post_id4 = arr.getJSONObject(j).getString("Data");
						    String post_id5 = arr.getJSONObject(j).getString("ID");
						    String post_id6 = arr.getJSONObject(j).getString("OID");
						   
							myText.setText(post_id0);
							
						}
					} catch (JSONException e) {
						myText.setText("JSONException");
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						myText.setText("InterruptedException");
						e.printStackTrace();
					} catch (ExecutionException e) {
						// TODO Auto-generated catch block
						myText.setText("ExecutionException");
						e.printStackTrace();
					}			
			}
		}
	};
}