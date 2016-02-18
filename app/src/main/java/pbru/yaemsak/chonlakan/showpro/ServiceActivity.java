package pbru.yaemsak.chonlakan.showpro;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

public class ServiceActivity extends AppCompatActivity {


    //Explicit
    private TextView showNameTextView, showPointTextView;
    private ListView rewardListView;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_service);
        // Bind widget
        bindWidget();

        //Show view
        showView();

        //Create list view
        createListView();


    }//main method

    private void createListView() {
        //connect database
        SQLiteDatabase sqLiteDatabase = openOrCreateDatabase(MyOpenHelper.DATABASE_NAME,MODE_PRIVATE,null);
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM " + ManagTABLE.TABLE_REWARD, null);
        cursor.moveToFirst();
        int[] iconInts = new int[cursor.getCount()];
        String[] nameRewardStrings = new String[cursor.getCount()];
        String[] pointRewardStrings = new String[cursor.getCount()];

        for (int i = 0; i < cursor.getCount(); i++) {


        }

    }//create listview

    private void showView() {

        String[] resultSting = getIntent().getStringArrayExtra("Result");
        showNameTextView.setText(resultSting[3] + " " + resultSting[4]);
        showPointTextView.setText(resultSting[7] + " " + "คะแนน");

    }//show view

    private void bindWidget() {
        showNameTextView = (TextView) findViewById(R.id.textView9);
        showPointTextView = (TextView) findViewById(R.id.textView10);
        rewardListView = (ListView) findViewById(R.id.listView);
    }
}//main class
