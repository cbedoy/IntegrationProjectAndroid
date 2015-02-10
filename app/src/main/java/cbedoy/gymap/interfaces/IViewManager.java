package cbedoy.gymap.interfaces;

import static cbedoy.gymap.interfaces.IViewController.*;

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
public interface IViewManager
{
    public void presentViewFromTag(TAG tag);
    public void addViewControllerFromTag(TAG tag, IViewController viewController);

}
