package pbru.yaemsak.chonlakan.showpro;

import android.content.Context;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.AsyncTask;
import android.speech.tts.Voice;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.util.Log;

import com.google.android.gms.drive.internal.StringListResponse;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import org.json.JSONArray;
import org.json.JSONObject;

public class LocationMapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private LocationManager locationManager;
    private Criteria criteria;//แกนโลก
    private boolean GPSABoolean, networkABoolean;
    private double myLatADouble, myLngADouble;
    private static double centerLatADouble = 14.089958;
    private static double centerLngADouble = 100.067562;
    private static final String urlPlace = "http://chonlakan.com/max/get_place.php";
    private SynLatLng synLatLng;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location_maps);

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

    //
    public class SynLatLng extends AsyncTask<Void, Void, String> {

        @Override
        protected String doInBackground(Void... voids) {

            try {
                OkHttpClient okHttpClient = new OkHttpClient();
                Request.Builder builder = new Request.Builder();
                Request request = builder.url(urlPlace).build();
                Response response = okHttpClient.newCall(request).execute();
                return response.body().string();

            } catch (Exception e) {
                e.printStackTrace();
                 Log.d("8June", "error DoIn ==> " + e.toString());
                return null;
            }
        }//DoInBack

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);

            try {

                Log.d("8June", "JSON ==> " + s);

                JSONArray jsonArray = new JSONArray(s);

                String[] PlaceStrings = new String[jsonArray.length()];
                String[] LatStrings = new String[jsonArray.length()];
                String[] LngStrings = new String[jsonArray.length()];
                String[] PhoneString = new String[jsonArray.length()];


                for (int i=0;i<jsonArray.length();i++) {

                    JSONObject jsonObject = jsonArray.getJSONObject(i);

                    PlaceStrings[i] = jsonObject.getString("Place");
                    LatStrings[i] = jsonObject.getString("Lat");
                    LngStrings[i] = jsonObject.getString("Lng");
                    PhoneString[i] = jsonObject.getString("Phone");

                    //Create All Marker
                    double lat = Double.parseDouble(LatStrings[i]);
                    double lng = Double.parseDouble(LngStrings[i]);

                    LatLng latLng = new LatLng(lat, lng);
                    mMap.addMarker(new MarkerOptions()
                            .position(latLng)
                            .title("Place")
                            .snippet("Phone")
                            .icon(BitmapDescriptorFactory.fromResource(R.drawable.mak1)));

                }   //for

            } catch (Exception e) {
                e.printStackTrace();
            }
        }//onPreE
    }//SynLatLng

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        mMap.setMapType(googleMap.MAP_TYPE_HYBRID);

        mMap.setMyLocationEnabled(true);
        mMap.getUiSettings().setZoomControlsEnabled(true);
        mMap.getUiSettings().isCompassEnabled();
        mMap.getUiSettings().setScrollGesturesEnabled(true);
        mMap.getUiSettings().setMapToolbarEnabled(true);

        //Setup Center
        LatLng latLng = new LatLng(centerLatADouble, centerLngADouble);
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, 7));

        SynLatLng synLatLng = new SynLatLng();
        synLatLng.execute();


    }//onMap
}//Main Class
