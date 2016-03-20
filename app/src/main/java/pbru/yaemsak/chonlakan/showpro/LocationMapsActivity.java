package pbru.yaemsak.chonlakan.showpro;

import android.content.Context;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.util.Log;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class LocationMapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private LocationManager locationManager;
    private Criteria criteria;//แกนโลก
    private boolean GPSABoolean, networkABoolean;
    private double myLatADouble, myLngADouble;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        //Open service
        openServiceLocation();


    }//Main method

    @Override
    protected void onResume() {
        super.onResume();

        //เช็คเมื่อโทรศัพอยู่ ปิด แล้วเริ่มใหม่
        locationManager.removeUpdates(locationListener);
        myLatADouble = 0;
        myLngADouble = 0;

        Location networkLocation = requestLocation(LocationManager.NETWORK_PROVIDER, "Cannot Connected Internet");
        if (networkLocation != null) {
            myLatADouble = networkLocation.getLatitude();
            myLngADouble = networkLocation.getLongitude();
        }

        Location GPSLocation = requestLocation(locationManager.GPS_PROVIDER, "No card GPS");
        if (GPSLocation != null) {
            myLatADouble = GPSLocation.getLatitude();
            myLngADouble = GPSLocation.getLongitude();
        }

        Log.d("15March", "My ==> " + myLatADouble);
        Log.d("15March", "My ==> " + myLngADouble);

    }// onResume

    @Override
    protected void onStart() {
        super.onStart();

        //เปิดแอพ
        GPSABoolean = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);//

        if (!GPSABoolean) {
            networkABoolean = locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER);//ออก netได้
            if (!networkABoolean) {

                Log.d("15march", "cannot find my location");

            }//if 2


        }//If 1
    }

    @Override
    protected void onStop() {
        super.onStop();

        locationManager.removeUpdates(locationListener);
        //เลิกแอพ

    }

    public Location requestLocation(String strProvider, String strError) {

        Location location = null;

        if (locationManager.isProviderEnabled(strProvider)) {

            locationManager.requestLocationUpdates(strProvider,
                    1000, 10, locationListener);//1000 คือ 1 วินาที ให้หา ตำแหน่งหรือ เปลี่ยน 10เมตร

            location = locationManager.getLastKnownLocation(strProvider);

        } else {
            //ต่อไม่มีมาทำที่นี่
            Log.d("15March", "Error ==>" + strError);

        } //If

        return location;
    }//requestLocation

    public LocationListener locationListener = new LocationListener() {
        @Override
        public void onLocationChanged(Location location) {
            myLatADouble = location.getLatitude();
            myLngADouble = location.getLongitude();

        }//OnLocationChange

        @Override
        public void onStatusChanged(String s, int i, Bundle bundle) {

        }

        @Override
        public void onProviderEnabled(String s) {

        }

        @Override
        public void onProviderDisabled(String s) {

        }
    };

    private void openServiceLocation() {
        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);//เปิด service
        criteria = new Criteria();
        criteria.setAccuracy(Criteria.ACCURACY_FINE);
        criteria.setAltitudeRequired(false);
        criteria.setBearingRequired(false); //ตัดแกน z


    }//openServiceLocation

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Sydney and move the camera
        LatLng sydney = new LatLng(-34, 151);
        mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));

    }//on already

}//Main Class
