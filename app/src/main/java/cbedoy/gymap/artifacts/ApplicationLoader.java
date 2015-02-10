package cbedoy.gymap.artifacts;

import android.app.Application;
import android.content.Context;
import android.os.Handler;
import android.view.LayoutInflater;

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
public class ApplicationLoader extends Application
{
    public static volatile Context mainContext;
    public static volatile LayoutInflater mainLayoutInflater;
    public static volatile Handler mainHandler;

    @Override
    public void onCreate() {
        super.onCreate();
        mainContext = getApplicationContext();
        mainLayoutInflater = (LayoutInflater) mainContext.getSystemService(LAYOUT_INFLATER_SERVICE);
        mainHandler = new Handler(getMainLooper());
    }
}
