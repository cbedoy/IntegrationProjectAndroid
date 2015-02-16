package cbedoy.gymap.services;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.HashMap;

import cbedoy.gymap.artifacts.Memento;
import cbedoy.gymap.business.login.interfaces.ILoginInformationDelegate;
import cbedoy.gymap.business.signup.interfaces.ISignUpInformationDelegate;
import cbedoy.gymap.interfaces.IMementoHandler;
import cbedoy.gymap.interfaces.INotificationMessages;
import cbedoy.gymap.interfaces.IUserProviderService;

/**
 * Created by Carlos Bedoy on 13/02/2015.
 * <p/>
 * Mobile App Developer
 * GyMap
 * <p/>
 * E-mail: carlos.bedoy@gmail.com
 * Facebook: https://www.facebook.com/carlos.bedoy
 * Github: https://github.com/cbedoy
 */
public class UserProviderService extends SQLiteOpenHelper implements IUserProviderService
{

    private IMementoHandler mementoHandler;
    private INotificationMessages notificationMessages;
    private ISignUpInformationDelegate signUpInformationDelegate;
    private ILoginInformationDelegate loginInformationDelegate;


    private final String sqlCreateScript = "CREATE TABLE Users (username TEXT, password TEXT, first_name TEXT, last_name TEXT)";
    private final String sqlDropScript = "DROP TABLE IF EXISTS Users";
    private String dataBaseName;
    private int version;

    public void setMementoHandler(IMementoHandler mementoHandler) {
        this.mementoHandler = mementoHandler;
    }

    public void setNotificationMessages(INotificationMessages notificationMessages) {
        this.notificationMessages = notificationMessages;
    }

    public void setSignUpInformationDelegate(ISignUpInformationDelegate signUpInformationDelegate) {
        this.signUpInformationDelegate = signUpInformationDelegate;
    }

    public void setLoginInformationDelegate(ILoginInformationDelegate loginInformationDelegate) {
        this.loginInformationDelegate = loginInformationDelegate;
    }

    public UserProviderService(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(sqlCreateScript);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //db.execSQL(sqlDropScript);
        db.execSQL(sqlCreateScript);
    }


    @Override
    public void findUserFromFromData(String username, String password) {
        SQLiteDatabase readableDatabase = getReadableDatabase();
        String[] filters = new String[]{username, password};
        Cursor cursor = readableDatabase.rawQuery("SELECT * FROM Users where username = ? and password = ? ", filters);
        HashMap<String, Object> response = new HashMap<>();
        response.put("status", false);

        while (cursor.moveToNext())
        {
            String _username = cursor.getString(0);
            String _password = cursor.getString(1);
            String _first_name = cursor.getString(2);
            String _last_name = cursor.getString(3);
            HashMap<String, Object> user_data = new HashMap<>();
            user_data.put("username", _username);
            user_data.put("password", _password);
            user_data.put("first_name", _first_name);
            user_data.put("last_name", _last_name);
            response.put("status", true);
            response.put("login_data", user_data);
        }
        loginInformationDelegate.loginResponse(response);

    }

    @Override
    public void saveUserOnDataBase()
    {
        Memento topMemento = mementoHandler.getTopMemento();
        HashMap<String, Object> mementoData = topMemento.getMementoData();
        HashMap<String, Object> sign_up_data = (HashMap<String, Object>) mementoData.get("sign_up_data");
        String firstName = sign_up_data.get("first_name").toString();
        String lastName = sign_up_data.get("last_name").toString();
        String userName = sign_up_data.get("username").toString();
        String password = sign_up_data.get("password").toString();

        ContentValues contentValues = new ContentValues();
        contentValues.put("first_name", firstName);
        contentValues.put("password", password);
        contentValues.put("last_name", lastName);
        contentValues.put("username", userName);

        SQLiteDatabase writableDatabase = getWritableDatabase();
        long resultTransaction = writableDatabase.insert("Users", null, contentValues);

        HashMap<String, Object> data = new HashMap<>();
        data.put("status", resultTransaction != -1);
        signUpInformationDelegate.signUpResponse(data);
    }
}
