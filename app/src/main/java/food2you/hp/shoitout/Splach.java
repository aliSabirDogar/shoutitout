package food2you.hp.shoitout;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;

import com.crashlytics.android.Crashlytics;

import io.fabric.sdk.android.Fabric;


/**
 * Created by admin on 11/29/2017.
 */

public class Splach extends Activity {


    int progress;
    ProgressBar progressBar;

    private SharedPreferences pref;
    String login;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Fabric.with(this, new Crashlytics());

        setContentView(R.layout.splach);




       /* pref =getApplicationContext().getSharedPreferences("ShoutOutApp", 0);
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                SharedPreferences.Editor editor = pref.edit();

               login= pref.getString(Constants.IS_LOGGED_IN, "");
                // Do something after 5s = 5000ms



                if(login.matches("false"))

                {
                    Intent intent = new Intent(Splach.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                }
                else
                {
                    Intent intent = new Intent(Splach.this, Tabs.class);
                    startActivity(intent);
                    finish();

                }
            }
        }, 1500);*/

        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
               /* SharedPreferences.Editor editor = pref.edit();

                login= pref.getString(Constants.IS_LOGGED_IN, "");*/
                // Do something after 5s = 5000ms




                    Intent intent = new Intent(Splach.this, Login.class);
                    startActivity(intent);
                    finish();

            }
        }, 2500);


    }
}
