package cbedoy.gymap.services;

import java.util.HashMap;

import cbedoy.gymap.artifacts.Memento;
import cbedoy.gymap.business.login.interfaces.ILoginInformationDelegate;
import cbedoy.gymap.business.login.interfaces.ILoginInformationHandler;
import cbedoy.gymap.business.map.interfaces.IMapInformationDelegate;
import cbedoy.gymap.business.map.interfaces.IMapInformationHandler;
import cbedoy.gymap.business.signup.interfaces.ISignUpInformationDelegate;
import cbedoy.gymap.business.signup.interfaces.ISignUpInformationHandler;
import cbedoy.gymap.interfaces.IMementoHandler;
import cbedoy.gymap.interfaces.INotificationMessages;
import cbedoy.gymap.interfaces.IRestService;
import cbedoy.gymap.interfaces.IUserProviderService;

import static cbedoy.gymap.interfaces.IRestService.*;
import static cbedoy.gymap.services.CBUtils.saveLastSession;

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
    private IUserProviderService userProviderService;
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

    public void setUserProviderService(IUserProviderService userProviderService) {
        this.userProviderService = userProviderService;
    }

    public void setNotificationMessages(INotificationMessages notificationMessages) {
        this.notificationMessages = notificationMessages;
    }

    @Override
    public void requestLogin() {
        Memento topMemento = mementoHandler.getTopMemento();
        HashMap<String, Object> mementoData = topMemento.getMementoData();
        String username = mementoData.get("username").toString();
        String password = mementoData.get("password").toString();
        userProviderService.findUserFromFromData(username, password);
    }

    @Override
    public void requestLoginAfterSignUp()
    {
        Memento topMemento = mementoHandler.getTopMemento();
        HashMap<String, Object> mementoData = topMemento.getMementoData();
        HashMap<String, Object>  sign_up_data = (HashMap<String, Object>) mementoData.get("sign_up_data");
        String username = sign_up_data.get("username").toString();
        String password = sign_up_data.get("password").toString();
        saveLastSession(username, password);
        userProviderService.findUserFromFromData(username, password);
    }

    @Override
    public void requestMapInformation()
    {
        String url = "https://raw.githubusercontent.com/cbedoy/IntegrationProjectAndroid/master/mock_location.json";
        IRestCallback callback = new IRestCallback() {
            @Override
            public void run(HashMap<String, Object> response) {
                mapInformationDelegate.mapResponse(response);
            }
        };
        restService.request(url, callback);
    }

    @Override
    public void registerUser() {
        userProviderService.saveUserOnDataBase();
    }
}
