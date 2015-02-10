package cbedoy.gymap.interfaces;

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
public interface  INotificationMessages
{
    public void showLoader();
    public void hideLoader();

    public void showCodeWithCallback(K_ERROR error, MessageRepresentationCallback callback);

    public interface MessageRepresentationCallback
    {
        public void onAccept();
        public void onCancel();
    }

    public enum K_ERROR{
        K_INVALID_LOGIN,
        K_INVALID_CITY,
        K_ERROR
    }
}
