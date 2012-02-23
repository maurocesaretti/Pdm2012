package esercizio.pdm;

import android.app.Activity;
import android.os.Bundle;
import android.provider.ContactsContract.CommonDataKinds.Im;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.view.ViewGroup.MarginLayoutParams;
import android.widget.ImageView;
import android.widget.RelativeLayout;

public class DragViewActivity extends Activity {
    /** Called when the activity is first created. */
	
	private View selected_item=null;
	private int offset_x = 0;
	private int offset_y = 0;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        ImageView iv2=(ImageView)findViewById(R.id.imageView2);
        ImageView iv=(ImageView) findViewById(R.id.imageView1);
        iv.setOnTouchListener(new OnTouchListener() {
			
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				// TODO Auto-generated method stub
				
				if(event.getAction()==MotionEvent.ACTION_DOWN){
					offset_x = (int) event.getX();
					offset_y = (int) event.getY();
					selected_item = v;
				}
					return false;
	
			}
		});
        
        iv2.setOnTouchListener(new OnTouchListener() {
			
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				// TODO Auto-generated method stub
				
				if(event.getAction()==MotionEvent.ACTION_DOWN){
					offset_x = (int) event.getX();
					offset_y = (int) event.getY();
					selected_item = v;
				}
				
				return false;
			}
		});
        
        RelativeLayout r1=(RelativeLayout)findViewById(R.id.relativeLayout1);
        r1.setOnTouchListener(new OnTouchListener() {
			
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				
				if(event.getAction()==MotionEvent.ACTION_MOVE){
					if(selected_item==null) return false;
					int w = getWindowManager().getDefaultDisplay().getWidth() -128;
					int h = getWindowManager().getDefaultDisplay().getHeight() -128;
					int x = (int)event.getX()-offset_x;
					int y = (int)event.getY()-offset_y;
					if(x>w)
						x=w;
					if(y>h)
						y=h;
					RelativeLayout.LayoutParams lp=new RelativeLayout.LayoutParams(new ViewGroup.MarginLayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT));
					lp.setMargins(x, y, 0, 0);
					selected_item.setLayoutParams(lp);
				}
				if(event.getAction()==MotionEvent.ACTION_UP){
					selected_item=null;
				}
				
				return true;
			}
		});
    }
}