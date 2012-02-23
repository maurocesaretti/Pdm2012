package pdm.test.mappe;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapActivity;
import com.google.android.maps.MapView;
import com.google.android.maps.MyLocationOverlay;
import com.google.android.maps.Overlay;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Color;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

public class RunnerActivity extends MapActivity {
    /** Called when the activity is first created. */
	
	MapView mv;
	MyLocationOverlay myLocationOverlay;
	RadiusOverlay prima;
	RadiusOverlay seconda;
	RadiusOverlay terza;
	RadiusOverlay quarta;
	PendingIntent mPendingstazionetermini;
	PendingIntent mPendingpiazzadellarepubblica;
	PendingIntent mPendingcolosseo;
	PendingIntent mPendingcasadiromoloeremo;
	GeoPoint stazionetermini;
	GeoPoint piazzadellarepubblica;
	GeoPoint colosseo;
	GeoPoint casadiromoloeremo;
	LocationManager locationManager;
	ProximityBroadcast mProximityBroadcast=new ProximityBroadcast();
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        mv=(MapView)findViewById(R.id.map1);
        mv.setClickable(true);
        mv.setBuiltInZoomControls(true);
        mv.setSatellite(true);
        myLocationOverlay = new MyLocationOverlay(this, mv);
        stazionetermini = new GeoPoint(41902022, 12500882);
		prima=new RadiusOverlay(stazionetermini, 400, android.graphics.Color.BLUE);
		piazzadellarepubblica = new GeoPoint(41902622, 12495482);
		seconda=new RadiusOverlay(piazzadellarepubblica, 300, android.graphics.Color.BLUE);
		colosseo = new GeoPoint(41890310, 12492410);
		terza=new RadiusOverlay(colosseo, 500, android.graphics.Color.BLUE);
		casadiromoloeremo = new GeoPoint(41890492, 12484823);
		quarta=new RadiusOverlay(casadiromoloeremo, 450, android.graphics.Color.BLUE);
		mv.getOverlays().add(prima);
		mv.getOverlays().add(seconda);
		mv.getOverlays().add(terza);
		mv.getOverlays().add(quarta);
		mv.getOverlays().add(myLocationOverlay);
        myLocationOverlay.runOnFirstFix(new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				mv.getController().animateTo(myLocationOverlay.getMyLocation());
			}
		});

        
    }

	@Override
	protected boolean isRouteDisplayed() {
		// TODO Auto-generated method stub
		return false;
	}
	 @Override
	    protected void onResume() {
	    	// TODO Auto-generated method stub
	    	super.onResume();
	    	myLocationOverlay.enableMyLocation();
	    	Intent intentstazionetermini=new Intent("pdm.test.mappe");
	    	intentstazionetermini.putExtra("overlay", 1);
	    	mPendingstazionetermini=PendingIntent.getBroadcast(this, 1, intentstazionetermini, PendingIntent.FLAG_CANCEL_CURRENT);
	    	Intent intentpiazzadellarepubblica=new Intent("pdm.test.mappe");
	    	intentpiazzadellarepubblica.putExtra("overlay", 2);
	    	mPendingpiazzadellarepubblica=PendingIntent.getBroadcast(this, 2, intentpiazzadellarepubblica, PendingIntent.FLAG_CANCEL_CURRENT);
	    	Intent intentcolosseo=new Intent("pdm.test.mappe");
	    	intentcolosseo.putExtra("overlay", 3);
	    	mPendingcolosseo=PendingIntent.getBroadcast(this, 3, intentcolosseo, PendingIntent.FLAG_CANCEL_CURRENT);
	    	Intent intentcasadiromoloeremo=new Intent("pdm.test.mappe");
	    	intentcasadiromoloeremo.putExtra("overlay", 4);
	    	mPendingcasadiromoloeremo=PendingIntent.getBroadcast(this, 4, intentcasadiromoloeremo, PendingIntent.FLAG_CANCEL_CURRENT);
	    	locationManager=(LocationManager)getSystemService(LOCATION_SERVICE);
	    	locationManager.addProximityAlert(stazionetermini.getLatitudeE6()*0.000001, stazionetermini.getLongitudeE6()*0.000001, 400, -1, mPendingstazionetermini);
	    	locationManager.addProximityAlert(piazzadellarepubblica.getLatitudeE6()*0.000001, piazzadellarepubblica.getLongitudeE6()*0.000001, 300, -1, mPendingpiazzadellarepubblica);
	    	locationManager.addProximityAlert(colosseo.getLatitudeE6()*0.000001, colosseo.getLongitudeE6()*0.000001, 500, -1, mPendingcolosseo);
	    	locationManager.addProximityAlert(casadiromoloeremo.getLatitudeE6()*0.000001, casadiromoloeremo.getLongitudeE6()*0.000001, 450, -1, mPendingcasadiromoloeremo);
	    	registerReceiver(mProximityBroadcast, new IntentFilter("pdm.test.mappe"));
	 }
	 @Override
	    protected void onPause() {
	    	// TODO Auto-generated method stub
	    	super.onPause();
	    	myLocationOverlay.disableMyLocation();
	    	unregisterReceiver(mProximityBroadcast);
	    	locationManager.removeProximityAlert(mPendingstazionetermini);
	    	locationManager.removeProximityAlert(mPendingpiazzadellarepubblica);
	    	locationManager.removeProximityAlert(mPendingcolosseo);
	    	locationManager.removeProximityAlert(mPendingcasadiromoloeremo);
	    }
	 	class ProximityBroadcast extends BroadcastReceiver {

			@Override
			public void onReceive(Context arg0, Intent arg1) {
				// TODO Auto-generated method stub
				Log.d("TAG", "Proximity Alert");
				//Toast.makeText(getApplicationContext(), "Alert di prossimità", Toast.LENGTH_LONG).show();
				boolean stoEntrando=arg1.getBooleanExtra(LocationManager.KEY_PROXIMITY_ENTERING, true);
				int area = arg1.getIntExtra("overlay", -1);
				if (stoEntrando){
					switch (area) {
					case 1:
						Toast.makeText(getApplicationContext(), "Benvenuto a Termini", Toast.LENGTH_SHORT).show();
						prima.setColor(Color.GREEN);
						break;
					case 2:
						Toast.makeText(getApplicationContext(), "Benvenuto a Piazza della Repubblica", Toast.LENGTH_SHORT).show();
						seconda.setColor(Color.GREEN);
						break;
					case 3:
						Toast.makeText(getApplicationContext(), "Benvenuto al Colosseo", Toast.LENGTH_SHORT).show();
						terza.setColor(Color.GREEN);
						break;
					case 4:
						Toast.makeText(getApplicationContext(), "Benvenuto a Casa di Romolo e Remo", Toast.LENGTH_SHORT).show();
						quarta.setColor(Color.GREEN);
						break;
					}
				}
				else {
					Toast.makeText(getApplicationContext(), "Arrivederci", Toast.LENGTH_SHORT).show();
					switch (area) {
					case 1:
						prima.setColor(Color.GRAY);
						break;
					case 2:
						seconda.setColor(Color.GRAY);
						break;
					case 3:
						terza.setColor(Color.GRAY);
						break;
					case 4:
						quarta.setColor(Color.GRAY);
						break;
					}
				}
			}
	 	}
}