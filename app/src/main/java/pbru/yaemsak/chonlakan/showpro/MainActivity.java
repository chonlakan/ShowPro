package pbru.yaemsak.chonlakan.showpro;

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
        testAddValue();

    }//main method

    private void testAddValue() {
        objManagTABLE.addNewValueToUser("testUser", "testPassword","testName","testSurname","testAddress","testEmail","testPoint");
    }


}// Main class
