package cbedoy.gymap.business.login;

import java.util.HashMap;

import cbedoy.gymap.artifacts.BusinessController;
import cbedoy.gymap.business.login.interfaces.ILoginInformationDelegate;
import cbedoy.gymap.business.login.interfaces.ILoginInformationHandler;
import cbedoy.gymap.business.login.interfaces.ILoginRepresentationDelegate;
import cbedoy.gymap.business.login.interfaces.ILoginRepresentationHandler;
import cbedoy.gymap.business.login.interfaces.ILoginTransactionDelegate;
import cbedoy.gymap.business.login.interfaces.ILoginTransactionHandler;

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
public class LoginBusinessController extends BusinessController implements ILoginInformationDelegate, ILoginTransactionDelegate, ILoginRepresentationDelegate
{
    private ILoginInformationHandler informationHandler;
    private ILoginTransactionHandler transactionHandler;
    private ILoginRepresentationHandler representationHandler;

    public void setInformationHandler(ILoginInformationHandler informationHandler) {
        this.informationHandler = informationHandler;
    }

    public void setTransactionHandler(ILoginTransactionHandler transactionHandler) {
        this.transactionHandler = transactionHandler;
    }

    public void setRepresentationHandler(ILoginRepresentationHandler representationHandler) {
        this.representationHandler = representationHandler;
    }

    @Override
    public void loginResponse(HashMap<String, Object> response) {

    }

    @Override
    public void login(String username, String password) {

    }
}
