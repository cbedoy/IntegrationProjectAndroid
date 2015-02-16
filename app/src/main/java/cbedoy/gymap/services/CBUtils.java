package cbedoy.gymap.services;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.graphics.Bitmap;
import android.graphics.Rect;
import android.view.View;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

import cbedoy.gymap.artifacts.ApplicationLoader;

public class CBUtils {

    private static SharedPreferences pref;
    private static Editor editor;
    private static CBUtils instance;


    public static CBUtils init(Activity activity){
        if (instance == null) {
            instance = new CBUtils();
        }
        return instance;
    }

    public static CBUtils getInstance(){
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



    public static void saveLastSession(String username, String password)
    {
        SharedPreferences session = ApplicationLoader.mainContext.getSharedPreferences("session", Context.MODE_PRIVATE);
        Editor edit = session.edit();
        edit.putString("username", username);
        edit.putString("password", password);
        edit.commit();
    }

    public static HashMap<String, Object> getLastSession()
    {
        SharedPreferences session = ApplicationLoader.mainContext.getSharedPreferences("session", Context.MODE_PRIVATE);
        String username = session.getString("username", "");
        String password = session.getString("password", "");
        HashMap<String, Object> data = new HashMap<>();
        data.put("username", username);
        data.put("password", password);
        return data;
    }

}
