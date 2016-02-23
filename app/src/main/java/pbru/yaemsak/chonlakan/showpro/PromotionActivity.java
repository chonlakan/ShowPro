package pbru.yaemsak.chonlakan.showpro;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

public class PromotionActivity extends AppCompatActivity {

    //Explicit
    private ListView promotionListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_promotion);

        //Bind Widget
        promotionListView = (ListView)findViewById(R.id.listView2);

    }//Main method

    public void clickBackPromotion(View view) {
        finish();
    }
}// Main Class
