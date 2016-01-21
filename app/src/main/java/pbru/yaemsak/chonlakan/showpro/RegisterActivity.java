package pbru.yaemsak.chonlakan.showpro;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.location.Address;
import android.os.StrictMode;
import android.provider.ContactsContract;
import android.provider.Settings;
import android.support.annotation.VisibleForTesting;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import java.util.ArrayList;

public class RegisterActivity extends AppCompatActivity {


    //Explicit
    private EditText userEditText,passwordEditText,nameEditText,surnameEditText,addressEditText, emailEditText;
    private String userString, passwordString,nameString, surnameString, addressString, emailString;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        //Bide widget
        bindWidget();

    }//Main Method

    public void clickSaveData(View view) {

        userString = userEditText.getText().toString().trim(); // เช็คค่าที่กรอก string
        passwordString = passwordEditText.getText().toString().trim();
        nameString = nameEditText.getText().toString().trim();
        surnameString = surnameEditText.getText().toString().trim();
        addressString = addressEditText.getText().toString().trim();
        emailString = emailEditText.getText().toString().trim();

        if (checkSpace()) {
            //Have Space
            MyAlertDialog objMyAlertDialog = new MyAlertDialog();
            objMyAlertDialog.myDialog(RegisterActivity.this, R.drawable.icon_question,
                    "มีช่องว่าง", "กรุณากรอกข้อมูลให้ครบทุกช่อง");

        } else {
            // No Space
            confirmRegister();

        }  //if

    }//click save data

    private void confirmRegister() {
        AlertDialog.Builder objBuilder = new AlertDialog.Builder(this);
        objBuilder.setIcon(R.drawable.icon_myaccount);
        objBuilder.setTitle("โปรดตรวจสอบข้อมูล");
        objBuilder.setMessage("User = " + userString + "\n" +
                "Password = " + passwordString + "\n" +
                "Name = " + nameString + "\n" +
                "Surname = " + surnameString + "\n" +
                "Address = " + addressString + "\n" +
                "Email = " + emailString);

        objBuilder.setPositiveButton("Confirm", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // Update to MySQL
                updateToMySQL();
                dialog.dismiss();
            }
        });
        objBuilder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                dialog.dismiss();
            }
        });
        objBuilder.show();// show data

    }//ConfirmRegister

    private void updateToMySQL() {

        StrictMode.ThreadPolicy myPolicy = new StrictMode.ThreadPolicy.Builder()
                .permitAll().build();//กำหนด permission ให้เข้าถึงได้ทุก โปรโตคอล
        StrictMode.setThreadPolicy(myPolicy);

        try {

            ArrayList<NameValuePair> objNameValuePairs = new ArrayList<NameValuePair>();
            objNameValuePairs.add(new BasicNameValuePair("isAdd", "true"));/// ทำตาม PHP
            objNameValuePairs.add(new BasicNameValuePair(ManagTABLE.COLUMN_User, userString));
            objNameValuePairs.add(new BasicNameValuePair(ManagTABLE.COLUMN_Password, passwordString));
            objNameValuePairs.add(new BasicNameValuePair(ManagTABLE.COLUMN_Name, nameString));
            objNameValuePairs.add(new BasicNameValuePair(ManagTABLE.COLUMN_Surname, surnameString));
            objNameValuePairs.add(new BasicNameValuePair(ManagTABLE.COLUMN_Address, addressString));
            objNameValuePairs.add(new BasicNameValuePair(ManagTABLE.COLUMN_Email, emailString));

            HttpClient objHttpClient = new DefaultHttpClient();
            HttpPost objHttpPost = new HttpPost("http://swiftcodingthai.com/mac/php_add_data_max.php");
            objHttpPost.setEntity(new UrlEncodedFormEntity(objNameValuePairs,"UTF-8"));
            objHttpClient.execute(objHttpPost);

            MyAlertDialog obj2MyAlertDialog = new MyAlertDialog();
            obj2MyAlertDialog.myDialog(RegisterActivity.this,R.drawable.icon_myaccount,
                    "อัพโหลดได้แล้ว",
                    "ข้อมูลได้ขึ้นบน server เรียบร้อยแล้ว");

            finish();// เมื่อกด confirm จะกลับมาหน้า แรก

        } catch (Exception e) {
            MyAlertDialog objMyAlertDialog = new MyAlertDialog();
            objMyAlertDialog.myDialog(RegisterActivity.this,R.drawable.icon_myaccount,
                    "ไม่สามารถอัพเดตข้อมูลได้",
                    "เกิดความผิดพลาดไม่สามารถอัพเดตข้อมูลขึ้น Server ได้");
        }
    }// update to mySQL

    private boolean checkSpace() {
        return userString.equals("") ||
                passwordString.equals("") ||
                nameString.equals("") ||
                surnameString.equals("") ||
                addressString.equals("") ||
                emailString.equals("");
    }


    private void bindWidget() {

        userEditText = (EditText) findViewById(R.id.editText3);
        passwordEditText = (EditText) findViewById(R.id.editText4);
        nameEditText = (EditText) findViewById(R.id.editText5);
        surnameEditText = (EditText) findViewById(R.id.editText6);
        addressEditText = (EditText) findViewById(R.id.editText7);
        emailEditText = (EditText) findViewById(R.id.editText8);



    }//Bide widget

} //Main Class
