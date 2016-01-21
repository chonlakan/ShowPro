package pbru.yaemsak.chonlakan.showpro;

import android.support.annotation.VisibleForTesting;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

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

        }  //if

    }//click save data

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
