package esercizio.pdm;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class DatiUtente extends Activity{
	
	EditText Username;
	EditText pass;
	EditText utente;
	
	 @Override
	 public void onCreate(Bundle savedInstanceState) {
		 super.onCreate(savedInstanceState);
	        setContentView(R.layout.login);
	        
	Username=(EditText)findViewById(R.id.username);
	pass=(EditText)findViewById(R.id.pass);
	utente=(EditText)findViewById(R.id.utente);
	
	Button login=(Button)findViewById(R.id.button1);
	login.setOnClickListener(new OnClickListener() {
        
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			Intent intent=new Intent(DatiUtente.this,Chat2Activity.class);
			String usernamestr = Username.getText().toString();
    		String passstr = pass.getText().toString();
    		String utentestr = utente.getText().toString();
    		intent.putExtra("Username",usernamestr);
    		intent.putExtra("Password",passstr);
    		intent.putExtra("Utente", utentestr);
    		startActivity(intent);      
			}
		});
	 }

}        
