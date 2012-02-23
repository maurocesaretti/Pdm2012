package esercizio.pdm;


import android.app.Activity;
import android.os.Bundle;

public class DragImageActivity extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        MyView mv=(new MyView(this));
        setContentView(mv);
    }
}
