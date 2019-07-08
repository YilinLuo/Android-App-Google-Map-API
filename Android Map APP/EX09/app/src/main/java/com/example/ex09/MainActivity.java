package com.example.ex09;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

/*import com.google.android.maps.ItemizedOverlay;
import com.google.android.maps.MapView;
import com.google.android.maps.OverlayItem;
*/
public class MainActivity extends FragmentActivity implements OnMapReadyCallback{

private GoogleMap mMap;
private LocationManager lm;

@Override
    protected void onCreate(Bundle savedInstanceState){
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

   //SupportMapFragment mapFragment = (SupportMapFragment)getSupportFragmentManager().findFragmentById(R.id.frg);
   SupportMapFragment mapFragment = SupportMapFragment.newInstance();
    FragmentTransaction fragmentTransaction =
            getSupportFragmentManager().beginTransaction();
    fragmentTransaction.replace(R.id.container_frame_back, mapFragment);
    fragmentTransaction.commitNowAllowingStateLoss();

    if (mapFragment != null) {
        mapFragment.getMapAsync(this);
    }

    lm=(LocationManager)getSystemService(LOCATION_SERVICE);

    if(ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this,Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED){

        return;
}
    lm.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0, 0, new LocationListener() {
        @Override
        public void onLocationChanged(Location location) {
            double lat = location.getLatitude();
            double log = location.getLongitude();

            LatLng Yilin = new LatLng(lat, log);
            mMap.addMarker(new MarkerOptions().position(Yilin).title("Yilin"));
            CameraUpdate camUpdate = CameraUpdateFactory.newLatLng(Yilin);
            mMap.animateCamera(camUpdate);

        }

        @Override
        public void onStatusChanged(String provider, int status, Bundle extras) {

        }

        @Override
        public void onProviderEnabled(String provider) {

        }

        @Override
        public void onProviderDisabled(String provider) {

        }
    });

}

@Override
    public void onMapReady(GoogleMap googleMap){
    mMap = googleMap;
    LatLng sydney = new LatLng(-34,151);
    mMap.addMarker(new MarkerOptions().position(sydney).title("Sydney"));
    mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));

}



}
