package pbru.yaemsak.chonlakan.showpro;

import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    //Explicit
    private ManagTABLE objManagTABLE;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //connected database
        objManagTABLE = new ManagTABLE(this);

        //Test add value
        //testAddValue();

        //delete all SQLite
        deleteAllSQLite();

    }//main method

    private void deleteAllSQLite() {// ลบข้อมูลออก แต่ตารางยังคงอยู่
        SQLiteDatabase objSqLiteDatabase = openOrCreateDatabase(MyOpenHelper.DATABASE_NAME,
                MODE_PRIVATE, null);
        objSqLiteDatabase.delete(ManagTABLE.TABLE_USER,null, null);
        objSqLiteDatabase.delete(ManagTABLE.TABLE_promotion, null, null);

    }

    private void testAddValue() {
        objManagTABLE.addNewValueToUser("testUser", "testPassword","testName","testSurname","testAddress","testEmail","testPoint");

        objManagTABLE.addNewValueToPromotion("Promotion","Condition","Start","End","Place","Lat","Lng","Reward");
    }


}// Main class
