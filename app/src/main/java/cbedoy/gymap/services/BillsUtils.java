package cbedoy.gymap.services;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.View;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class BillsUtils {

    private static SharedPreferences pref;
    private static Editor editor;
    private static BillsUtils instance;


    public static BillsUtils init(Activity activity){
        if (instance == null) {
            instance = new BillsUtils();
        }
        return instance;
    }

    public static BillsUtils getInstance(){
        return instance;
    }



    public String getGCMRegisterCode(Context c) {
        pref = c.getApplicationContext().getSharedPreferences("GCMRegisterCode", Context.MODE_PRIVATE);
        return pref.getString("tokenGCM", "");
    }


    public void setGCMRegisterCode(Context context,String CodeGCM){
        pref = context.getApplicationContext().getSharedPreferences("GCMRegisterCode", Context.MODE_PRIVATE);
        editor = pref.edit();
        editor.putString("tokenGCM", CodeGCM);
        editor.commit();
    }

    public static Bitmap takeScreenShot(Activity activity)
    {
        try{
            View view = activity.getWindow().getDecorView();
            view.setDrawingCacheEnabled(true);
            view.buildDrawingCache();
            Bitmap drawingCache = view.getDrawingCache();
            Rect rect = new Rect();
            activity.getWindow().getDecorView().getWindowVisibleDisplayFrame(rect);
            int statusBarHeight = rect.top;
            int width = activity.getWindowManager().getDefaultDisplay().getWidth();
            int height = activity.getWindowManager().getDefaultDisplay().getHeight();
            Bitmap bitmap = Bitmap.createBitmap(drawingCache, 0, statusBarHeight, width, height  - statusBarHeight);
            view.destroyDrawingCache();
            System.gc();
            return bitmap;
        }
        catch (Exception e)
        {
            System.gc();
            return null;
        }
    }



    public void setLastUserActive(String user, String code, Context c){
        pref = c.getApplicationContext().getSharedPreferences("UserInformation", Context.MODE_PRIVATE);
        editor = pref.edit();

        JSONObject jsonData = new JSONObject();
        try {
            jsonData.put("user",user);
            jsonData.put("countryCode",code);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        editor.putString("LastUserActive", jsonData.toString());
        editor.commit();
    }

    public JSONObject getLastUserActive(Context c){
        pref = c.getApplicationContext().getSharedPreferences("UserInformation", Context.MODE_PRIVATE);
        try {
            JSONObject jsonTemp = new JSONObject(pref.getString("LastUserActive", "{}"));
            return jsonTemp;

        } catch (JSONException e) {
            return new JSONObject();
        }
    }

}
