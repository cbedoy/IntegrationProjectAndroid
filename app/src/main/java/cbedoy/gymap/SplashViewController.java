package cbedoy.gymap;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import cbedoy.gymap.artifacts.BlackActivity;

/**
 * Created by Carlos Bedoy on 09/02/2015.
 * <p/>
 * Mobile App Developer
 * GyMap
 * <p/>
 * E-mail: carlos.bedoy@gmail.com
 * Facebook: https://www.facebook.com/carlos.bedoy
 * Github: https://github.com/cbedoy
 */
public class SplashViewController extends BlackActivity
{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_view_controller);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(SplashViewController.this, MasterViewController.class);
                startActivity(intent);
                finish();
            }
        }, 3500);

    }

}
