package pbru.yaemsak.chonlakan.showpro;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

public class PromotionActivity extends AppCompatActivity {

    //Explicit
    private ListView promotionListView;
    SwipeRefreshLayout mSwipeRefreshLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_promotion);

        //Bind Widget
        promotionListView = (ListView)findViewById(R.id.listView2);
        mSwipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.srf);
        mSwipeRefreshLayout.setColorSchemeResources(android.R.color.holo_blue_bright,
                android.R.color.holo_green_light,
                android.R.color.holo_orange_light,
                android.R.color.holo_red_light);
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override            public void onRefresh() {
                // update data here
                new refresh_data().execute();
            }

        });
        //CreateListView
        CreateListView();

    }//Main method

    private void CreateListView() {
        SQLiteDatabase sqLiteDatabase = openOrCreateDatabase(MyOpenHelper.DATABASE_NAME,
                MODE_PRIVATE, null);

        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM " + ManagTABLE.TABLE_PROMOTION +
                " ORDER BY _id DESC", null);//เรียงลำดับจากข้อมูลมากไปน้อย , ASC น้อยไปมาก
        cursor.moveToFirst();

        String[] namePromotion = new String[cursor.getCount()];
        String[] picPromotion = new String[cursor.getCount()];
        String[] startPromotion = new String[cursor.getCount()];
        String[] endPromotion = new String[cursor.getCount()];
        final String[] idStrings = new String[cursor.getCount()];

        for (int i = 0; i < cursor.getCount(); i++) {

            namePromotion[i] = cursor.getString(cursor.getColumnIndex(ManagTABLE.COLUMN_NamePromotion));
            picPromotion[i] = cursor.getString(cursor.getColumnIndex(ManagTABLE.COLUMN_PictPromotion));
            startPromotion[i] = cursor.getString(cursor.getColumnIndex(ManagTABLE.COLUMN_TimeStart));
            endPromotion[i] = cursor.getString(cursor.getColumnIndex(ManagTABLE.COLUMN_TimeEnd));
            idStrings[i] = cursor.getString(cursor.getColumnIndex(ManagTABLE.COLUMN_id));

            cursor.moveToNext();//สั่งให้ cursor เลื่อนตำแหน่ง
        }//for
        cursor.close();

        //create list view
        PromoteAdapter promoteAdapter = new PromoteAdapter(PromotionActivity.this,
                picPromotion, namePromotion, startPromotion, endPromotion);
        promotionListView.setAdapter(promoteAdapter);

        promotionListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                Intent intent = new Intent(PromotionActivity.this, DetailPromotionActivity.class);
                intent.putExtra("ID", idStrings[i]);
                startActivity(intent);

            }
        });

    }//create list view
    class refresh_data extends AsyncTask<Void, Void, Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }
        @Override
        protected Void doInBackground(Void... params) {
            for (int i = 0; i < 4; i++) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            if (mSwipeRefreshLayout.isRefreshing()) {
                mSwipeRefreshLayout.setRefreshing(false);
            }
        }
    }

    public void clickBackMenu(View view) {
        startActivity(new Intent(PromotionActivity.this, MenuActivity.class));
    }
}// Main Class
