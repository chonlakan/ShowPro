package pbru.yaemsak.chonlakan.showpro;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.util.Log;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class DetailPromotionActivity extends FragmentActivity implements OnMapReadyCallback {

    //Explicit

    private GoogleMap mMap;
    private String idString;
    private String[] resultStrings;// ตัวแปลที่จะใช้ข้อมูล cursor 1 แถว listview

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.my_detail_activity);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        //Receive ID
        receiveID();

    }//Main method

    private void receiveID() {

        idString = getIntent().getStringExtra("ID");//เช็คค่าเมื่อคลิก list view
        Log.d("23Feb", "ID =>" + idString);

        SQLiteDatabase sqLiteDatabase = openOrCreateDatabase(MyOpenHelper.DATABASE_NAME,
                MODE_PRIVATE,null);
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM promotionTABLE WHERE _id = " + "'" + idString + "'"
                , null);
        cursor.moveToFirst();
        resultStrings = new String[cursor.getColumnCount()];//จองหน่วยความจำ
        for (int i = 0; i < cursor.getColumnCount(); i++) {

            resultStrings[i] = cursor.getString(i);
            Log.d("23Feb", "resultStings["+ i +"] = " + resultStrings[i]);

        }//for
        cursor.close();

    }//Receive ID

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Sydney and move the camera
        LatLng sydney = new LatLng(-34, 151);
        mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));

    }//on MapReady
}//Main Class