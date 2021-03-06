package cbedoy.gymap.business.login.interfaces;

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
public interface ILoginTransactionHandler
{
    public void avoidLogin();
    public void userWithSession();
    public void registerUser();
}
