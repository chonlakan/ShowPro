package pbru.yaemsak.chonlakan.showpro;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.LineNumberReader;

public class DetailPromotionActivity extends FragmentActivity implements OnMapReadyCallback {

    //Explicit

    private GoogleMap mMap;
    private String idString;
    private String[] resultStrings;// ตัวแปลที่จะใช้ข้อมูล cursor 1 แถว listview
    private TextView namePromoteTextView,
            placeTextView, startTextView,
            endTextView, conditionTextView;
    private LatLng[] markLatLngs;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.my_detail_activity);

        //bind widget
        bindWidget();


        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        //Receive ID
        receiveID();

        //Show TextView
        showTextView();


    }//Main method

    private void showTextView() {
        namePromoteTextView.setText(resultStrings[1]);
        placeTextView.setText(resultStrings[6]);
        startTextView.setText(resultStrings[4]);
        endTextView.setText(resultStrings[5]);

    }//show Method

    private void bindWidget() {
        namePromoteTextView = (TextView) findViewById(R.id.textView24);
        placeTextView = (TextView) findViewById(R.id.textView25);
        startTextView = (TextView) findViewById(R.id.textView26);
        endTextView = (TextView) findViewById(R.id.textView27);

    }//Bind widget

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

            double douLat = Double.parseDouble(resultStrings[7]);
            double douLng = Double.parseDouble(resultStrings[8]);

            markLatLngs[i] = new LatLng(douLat, douLng);

        }//for
        cursor.close();

    }//Receive ID

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        double douLatCenter = Double.parseDouble(resultStrings[7]);
        double douLngCenter = Double.parseDouble(resultStrings[8]);

        LatLng centerLatLng = new LatLng(douLatCenter, douLngCenter);
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(centerLatLng, 17)); // ระยะมุมมอง


        //For Create Marker
//        for (int i = 0; i < markLatLngs.length; i++) {/////////////////////////////////ติดปัญหา
//
//            mMap.addMarker(new MarkerOptions().position(markLatLngs[i]));//ตำแหน่ง marker
//
//       }//For

    }//on MapReady
}//Main Class