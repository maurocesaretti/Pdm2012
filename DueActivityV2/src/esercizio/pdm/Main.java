package esercizio.pdm;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class Main extends Activity {

	/** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        final EditText text = ((EditText) findViewById(R.id.editText1));
        Button startButton = (Button) findViewById(R.id.START);
        startButton.setOnClickListener(new OnClickListener() {
        	public void onClick(View v){
        		startActivity(new Intent(Main.this, Second.class));
        		Intent intent = new Intent(Main.this,Second.class);
        		String iltesto = text.getText().toString();
        		intent.putExtra("iltestonelbox", iltesto);
        		startActivity(intent);
        	}
        });
  	}
}
