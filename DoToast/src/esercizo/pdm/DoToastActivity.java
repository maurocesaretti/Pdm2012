package esercizo.pdm;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextWatcher;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

public class DoToastActivity extends Activity implements OnClickListener{
    /** Called when the activity is first created. */
	
	EditText ed;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        
        LinearLayout ll = new LinearLayout(DoToastActivity.this);
        ll.setOrientation(LinearLayout.VERTICAL);
        ll.setLayoutParams(new LayoutParams(LayoutParams.FILL_PARENT, LayoutParams.FILL_PARENT));
        
        ed = new EditText(this);
        ed.setLayoutParams(new LinearLayout.LayoutParams(LayoutParams.FILL_PARENT,LayoutParams.WRAP_CONTENT)); 

        Button btn = new Button(this);
        btn.setText("saluta");
        btn.setLayoutParams(new LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
        
        ll.addView(ed);
        ll.addView(btn);
		setContentView(ll);
		
        btn.setOnClickListener(this);
    }

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub

		
		
		Toast.makeText(getApplicationContext(),ed.getText().toString(), Toast.LENGTH_LONG).show();
	}
}