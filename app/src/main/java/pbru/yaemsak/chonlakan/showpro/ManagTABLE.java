package pbru.yaemsak.chonlakan.showpro;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by chonlakan on 5/1/2559.
 */
public class ManagTABLE {

    //Explicit
    private MyOpenHelper objMyOpenHelper;
    private SQLiteDatabase WritSqLiteDatabase, readSqLiteDatabase;

    public ManagTABLE(Context context) {

        //Connected database
        objMyOpenHelper = new MyOpenHelper(context);
        WritSqLiteDatabase = objMyOpenHelper.getWritableDatabase();
        readSqLiteDatabase = objMyOpenHelper.getReadableDatabase();


    }//Constructor

}//Main class
