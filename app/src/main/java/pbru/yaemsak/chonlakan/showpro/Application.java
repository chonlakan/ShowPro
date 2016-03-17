package pbru.yaemsak.chonlakan.showpro;

import com.parse.Parse;
import com.parse.PushService;

/**
 * Created by chonlakan on 17/3/2559.
 */
public class Application extends android.app.Application {

    public Application() {
    }

    @Override
    public void onCreate() {
        super.onCreate();


        Parse.initialize(getApplicationContext(),
                "zXtILUUWthv7TaezFUhH80dYg9NyjZS60hdov6Vc",
                "hcsypysQtfijTyRYnnqpBELwVk2JrUC4jf2ttB5q");

        PushService.setDefaultPushCallback(this, MainActivity.class);

    }
}
