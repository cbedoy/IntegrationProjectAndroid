package cbedoy.gymap.business.login;

import java.util.HashMap;

import cbedoy.gymap.artifacts.BusinessController;
import cbedoy.gymap.business.login.interfaces.ILoginInformationDelegate;
import cbedoy.gymap.business.login.interfaces.ILoginInformationHandler;
import cbedoy.gymap.business.login.interfaces.ILoginRepresentationDelegate;
import cbedoy.gymap.business.login.interfaces.ILoginRepresentationHandler;
import cbedoy.gymap.business.login.interfaces.ILoginTransactionDelegate;
import cbedoy.gymap.business.login.interfaces.ILoginTransactionHandler;
import cbedoy.gymap.interfaces.INotificationMessages;

import static cbedoy.gymap.interfaces.INotificationMessages.K_ERROR.K_INVALID_LOGIN;
import static cbedoy.gymap.interfaces.INotificationMessages.MessageRepresentationCallback;

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
        notificationMessages.hideLoader();
        Boolean status = (Boolean) response.get("status");
        if(status)
        {
            HashMap<String, Object> data = new HashMap<>();
            data.put("login_response", response);
            mementoHandler.setStateForOwner(data, this);
            transactionHandler.userWithSession();
        }
        else
        {
            notificationMessages.showCodeWithCallback(K_INVALID_LOGIN, new MessageRepresentationCallback() {
                @Override
                public void onAccept() {
                    representationHandler.clearPassword();
                    representationHandler.clearUsername();
                }

                @Override
                public void onCancel() {
                    transactionHandler.registerUser();
                }
            });

        }
    }

    @Override
    public void login(String username, String password)
    {
        HashMap<String, Object> data = new HashMap<>();
        data.put("username", username);
        data.put("password", password);
        mementoHandler.setStateForOwner(data, password);
        notificationMessages.showLoader();
        informationHandler.requestLogin();
    }

    @Override
    public void userNeedSignUp() {
        transactionHandler.registerUser();
    }

    @Override
    public void startLogin()
    {
        representationHandler.showLoginWithData();
    }
}
