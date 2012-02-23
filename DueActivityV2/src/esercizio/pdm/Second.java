package esercizio.pdm;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class Second extends Activity {
	/** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.second);
        String id = "Metodo On Create";
		Log.d("Metodo", id);
        TextView label = (TextView) findViewById(R.id.textView1);
        String iltestoricevuto = getIntent().getExtras().getString("iltestonelbox");
        label.setText(iltestoricevuto);
        
    }
    public void onStart(){
    	super.onStart();
    	String id="Metodo onStart";
    	Log.d("Second",id);
    }
    public void onReStart(){
    	super.onRestart();
    	String id="Metodo onRestart";
    	Log.d("Second",id);
    }
    public void onResume(){
    	super.onResume();
    	String id="Metodo onResume";
    	Log.d("Second",id);
    }
    public void onPause(){
    	super.onPause();
    	String id="Metodo onPause";
    	Log.d("Second",id);
    }
    public void onStop(){
    	super.onStop();
    	String id="Metodo onStop";
    	Log.d("Second",id);
    }
    @Override
    protected void onDestroy() {
    	// TODO Auto-generated method stub
    	super.onDestroy();
    	String id="Metodo onDestroy";
    	Log.d("Second",id);
    }
}
