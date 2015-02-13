package cbedoy.gymap.business;

import cbedoy.gymap.artifacts.BusinessController;
import cbedoy.gymap.business.login.interfaces.ILoginTransactionDelegate;
import cbedoy.gymap.business.login.interfaces.ILoginTransactionHandler;
import cbedoy.gymap.business.map.interfaces.IMapTransactionDelegate;
import cbedoy.gymap.business.map.interfaces.IMapTransactionHandler;
import cbedoy.gymap.business.signup.interfaces.ISignUpTransactionDelegate;
import cbedoy.gymap.business.signup.interfaces.ISignUpTransactionHandler;
import cbedoy.gymap.interfaces.IMementoHandler;

/**
 * Created by Carlos Bedoy on 2/10/15.
 * <p/>
 * Mobile App Developer - Bills Android
 * <p/>
 * Pademobile
 */
public class MasterBusinessController extends BusinessController implements ILoginTransactionHandler, ISignUpTransactionHandler, IMapTransactionHandler
{
    private ILoginTransactionDelegate loginTransactionDelegate;
    private ISignUpTransactionDelegate signUpTransactionDelegate;
    private IMapTransactionDelegate mapTransactionDelegate;

    public void setLoginTransactionDelegate(ILoginTransactionDelegate loginTransactionDelegate) {
        this.loginTransactionDelegate = loginTransactionDelegate;
    }

    public void setMapTransactionDelegate(IMapTransactionDelegate mapTransactionDelegate) {
        this.mapTransactionDelegate = mapTransactionDelegate;
    }

    public void setSignUpTransactionDelegate(ISignUpTransactionDelegate signUpTransactionDelegate) {
        this.signUpTransactionDelegate = signUpTransactionDelegate;
    }

    public void startApplication() {
        loginTransactionDelegate.startLogin();
    }

    @Override
    public void avoidLogin() {
        mapTransactionDelegate.loadLocations();
    }

    @Override
    public void userWithSession() {
        mapTransactionDelegate.loadLocations();
    }

    @Override
    public void registerUser() {
        signUpTransactionDelegate.startSignUp();
    }

    @Override
    public void signedUser() {
        loginTransactionDelegate.startLogin();
    }
}
