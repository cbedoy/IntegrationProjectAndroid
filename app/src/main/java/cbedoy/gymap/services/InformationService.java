package cbedoy.gymap.services;

import cbedoy.gymap.business.login.interfaces.ILoginInformationDelegate;
import cbedoy.gymap.business.login.interfaces.ILoginInformationHandler;
import cbedoy.gymap.business.map.interfaces.IMapInformationDelegate;
import cbedoy.gymap.business.map.interfaces.IMapInformationHandler;
import cbedoy.gymap.business.signup.interfaces.ISignUpInformationDelegate;
import cbedoy.gymap.business.signup.interfaces.ISignUpInformationHandler;
import cbedoy.gymap.interfaces.IMementoHandler;
import cbedoy.gymap.interfaces.INotificationMessages;
import cbedoy.gymap.interfaces.IRestService;

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
public class InformationService implements ILoginInformationHandler, ISignUpInformationHandler, IMapInformationHandler
{
    private IRestService restService;
    private IMementoHandler mementoHandler;
    private INotificationMessages notificationMessages;
    private IMapInformationDelegate mapInformationDelegate;
    private ILoginInformationDelegate loginInformationDelegate;
    private ISignUpInformationDelegate signUpInformationDelegate;

    public void setMapInformationDelegate(IMapInformationDelegate mapInformationDelegate) {
        this.mapInformationDelegate = mapInformationDelegate;
    }

    public void setLoginInformationDelegate(ILoginInformationDelegate loginInformationDelegate) {
        this.loginInformationDelegate = loginInformationDelegate;
    }

    public void setSignUpInformationDelegate(ISignUpInformationDelegate signUpInformationDelegate) {
        this.signUpInformationDelegate = signUpInformationDelegate;
    }

    public void setRestService(IRestService restService) {
        this.restService = restService;
    }

    public void setMementoHandler(IMementoHandler mementoHandler) {
        this.mementoHandler = mementoHandler;
    }

    @Override
    public void requestLogin() {

    }

    @Override
    public void requestMapInformation() {

    }

    @Override
    public void registerUser() {

    }
}
