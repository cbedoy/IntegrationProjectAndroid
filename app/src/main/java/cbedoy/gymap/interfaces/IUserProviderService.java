package cbedoy.gymap.interfaces;

import java.util.HashMap;

/**
 * Created by Carlos Bedoy on 15/02/2015.
 * <p/>
 * Mobile App Developer
 * GyMap
 * <p/>
 * E-mail: carlos.bedoy@gmail.com
 * Facebook: https://www.facebook.com/carlos.bedoy
 * Github: https://github.com/cbedoy
 */
public interface IUserProviderService
{
    public void findUserFromFromData(String username, String password);
    public void saveUserOnDataBase();
}
