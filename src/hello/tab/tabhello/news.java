package hello.tab.tabhello;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class news extends Activity{
	    @Override
	    public void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.news);
			TextView mYText = (TextView)findViewById(R.id.textView1);
	        //mYText.setTextColor(Color.BLUE);
			TextView mYText1 = (TextView)findViewById(R.id.textView2);
	        //mYText.setTextColor(Color.BLUE);
			//TextView mYText2 = (TextView)findViewById(R.id.textView3);
	        //mYText.setTextColor(Color.BLUE);
			TextView mYText3 = (TextView)findViewById(R.id.textView4);
	        //mYText.setTextColor(Color.BLUE);
	        // you have to create game.xml 
	        
	        			Intent intent = getIntent();
	        			String titulo = intent.getStringExtra("titulo");
						mYText.setText(titulo);
						String fecha = intent.getStringExtra("fecha");
						String hora = intent.getStringExtra("hora");
						mYText1.setText(fecha + ",  " + hora);
						//mYText2.setText(hora);
						String data = intent.getStringExtra("data");
						mYText3.setText(data);

	    }
}