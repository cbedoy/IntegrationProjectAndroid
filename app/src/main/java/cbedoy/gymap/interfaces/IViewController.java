package cbedoy.gymap.interfaces;

import android.view.View;

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
public interface IViewController
{
    public View getView();
    public void onAttachToWindow();
    public void onRemoveToWindow();
    public void onBackPressed();


    public enum TAG
    {
        LOGIN,
        SIGNUP,
        MAP,
        LIST
    }
}
