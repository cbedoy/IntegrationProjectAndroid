package cbedoy.gymap.assambly;

import cbedoy.gymap.business.MasterBusinessController;
import cbedoy.gymap.business.login.LoginBusinessController;
import cbedoy.gymap.business.map.MapBusinessController;
import cbedoy.gymap.business.signup.SignUpBusinessController;
import cbedoy.gymap.interfaces.IViewController;
import cbedoy.gymap.interfaces.IViewManager;
import cbedoy.gymap.services.InformationService;
import cbedoy.gymap.services.MementoHandler;
import cbedoy.gymap.services.NotificationMessages;
import cbedoy.gymap.services.RestService;
import cbedoy.gymap.viewcontroller.LoginViewController;
import cbedoy.gymap.viewcontroller.MapViewController;
import cbedoy.gymap.viewcontroller.SignUpViewController;

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
public class MainAssambly
{

    private static MainAssambly instance;

    public static MainAssambly getInstance(){
        if(instance == null)
            instance = new MainAssambly();
        return instance;
    }


    public void init(IViewManager viewManager)
    {

        MasterBusinessController masterBusinessController = new MasterBusinessController();
        RestService restService = new RestService();
        InformationService informationService = new InformationService();
        MementoHandler mementoHandler = new MementoHandler();
        NotificationMessages notificationMessages = new NotificationMessages();


        //LOGIN
        LoginViewController loginViewController = new LoginViewController();
        LoginBusinessController loginBusinessController = new LoginBusinessController();
        loginViewController.setMementoHandler(mementoHandler);
        loginViewController.setRepresentationDelegate(loginBusinessController);
        loginViewController.setNotificationMessages(notificationMessages);
        loginViewController.setTag(IViewController.TAG.LOGIN);
        loginViewController.setParentActivity(viewManager);
        loginBusinessController.setNotificationMessages(notificationMessages);
        loginBusinessController.setMementoHandler(mementoHandler);
        loginBusinessController.setInformationHandler(informationService);
        loginBusinessController.setRepresentationHandler(loginViewController);
        loginBusinessController.setTransactionHandler(masterBusinessController);


        //MAP
        MapViewController mapViewController = new MapViewController();
        MapBusinessController mapBusinessController = new MapBusinessController();
        mapViewController.setMementoHandler(mementoHandler);
        mapViewController.setRepresentationDelegate(mapBusinessController);
        mapViewController.setNotificationMessages(notificationMessages);
        mapViewController.setTag(IViewController.TAG.MAP);
        mapViewController.setParentActivity(viewManager);
        mapBusinessController.setNotificationMessages(notificationMessages);
        mapBusinessController.setMementoHandler(mementoHandler);
        mapBusinessController.setInformationHandler(informationService);
        mapBusinessController.setRepresentationHandler(mapViewController);
        mapBusinessController.setTransactionHandler(masterBusinessController);

        //SIGN UP
        SignUpViewController signUpViewController = new SignUpViewController();
        SignUpBusinessController signUpBusinessController = new SignUpBusinessController();
        signUpViewController.setMementoHandler(mementoHandler);
        signUpViewController.setRepresentationDelegate(signUpBusinessController);
        signUpViewController.setNotificationMessages(notificationMessages);
        signUpViewController.setTag(IViewController.TAG.SIGNUP);
        signUpViewController.setParentActivity(viewManager);
        signUpBusinessController.setNotificationMessages(notificationMessages);
        signUpBusinessController.setMementoHandler(mementoHandler);
        signUpBusinessController.setInformationHandler(informationService);
        signUpBusinessController.setRepresentationHandler(signUpViewController);
        signUpBusinessController.setTransactionHandler(masterBusinessController);

        //INFORMATION SERVICE
        informationService.setSignUpInformationDelegate(signUpBusinessController);
        informationService.setLoginInformationDelegate(loginBusinessController);
        informationService.setMapInformationDelegate(mapBusinessController);
        informationService.setMementoHandler(mementoHandler);
        informationService.setRestService(restService);


        //MASTER BUSINESS CONTROLLER
        masterBusinessController.setSignUpTransactionDelegate(signUpBusinessController);
        masterBusinessController.setLoginTransactionDelegate(loginBusinessController);
        masterBusinessController.setMapTransactionDelegate(mapBusinessController);
        masterBusinessController.setNotificationMessages(notificationMessages);
        masterBusinessController.setMementoHandler(mementoHandler);

        viewManager.addViewControllerFromTag(IViewController.TAG.LOGIN, loginViewController);
        viewManager.addViewControllerFromTag(IViewController.TAG.SIGNUP, signUpViewController);
        viewManager.addViewControllerFromTag(IViewController.TAG.MAP, mapViewController);

        //START APP FLOW
        masterBusinessController.startApplication();

    }
}
