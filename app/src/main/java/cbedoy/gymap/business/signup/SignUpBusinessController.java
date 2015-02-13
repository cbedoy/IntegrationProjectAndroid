package cbedoy.gymap.business.signup;

import java.util.HashMap;

import cbedoy.gymap.artifacts.BusinessController;
import cbedoy.gymap.business.signup.interfaces.ISignUpInformationDelegate;
import cbedoy.gymap.business.signup.interfaces.ISignUpInformationHandler;
import cbedoy.gymap.business.signup.interfaces.ISignUpRepresentationDelegate;
import cbedoy.gymap.business.signup.interfaces.ISignUpRepresentationHandler;
import cbedoy.gymap.business.signup.interfaces.ISignUpTransactionDelegate;
import cbedoy.gymap.business.signup.interfaces.ISignUpTransactionHandler;
import cbedoy.gymap.interfaces.INotificationMessages;

import static cbedoy.gymap.interfaces.INotificationMessages.K_ERROR.K_ERROR;
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
public class SignUpBusinessController extends BusinessController implements ISignUpTransactionDelegate, ISignUpRepresentationDelegate, ISignUpInformationDelegate
{
    private ISignUpTransactionHandler transactionHandler;
    private ISignUpInformationHandler informationHandler;
    private ISignUpRepresentationHandler representationHandler;

    public void setTransactionHandler(ISignUpTransactionHandler transactionHandler) {
        this.transactionHandler = transactionHandler;
    }

    public void setInformationHandler(ISignUpInformationHandler informationHandler) {
        this.informationHandler = informationHandler;
    }

    public void setRepresentationHandler(ISignUpRepresentationHandler representationHandler) {
        this.representationHandler = representationHandler;
    }

    @Override
    public void signUpResponse(HashMap<String, Object> response) {
        notificationMessages.hideLoader();
        boolean status = (boolean) response.get("status");
        if(status)
        {
            HashMap<String, Object> data = new HashMap<>();
            data.put("sign_up_response", response);
            mementoHandler.setStateForOwner(data, this);
            transactionHandler.signedUser();
        }
        else
        {
            notificationMessages.showCodeWithCallback(K_ERROR, new MessageRepresentationCallback(){

                @Override
                public void onAccept() {
                    representationHandler.clearFields();
                }

                @Override
                public void onCancel() {

                }
            });
        }
    }

    @Override
    public void signUserWithData(HashMap<String, Object> userData) {
        HashMap<String, Object> data = new HashMap<>();
        data.put("sign_up_data", userData);
        mementoHandler.setStateForOwner(data, this);
        notificationMessages.showLoader();
        informationHandler.registerUser();
    }

    @Override
    public void startSignUp() {
        representationHandler.showSignUp();
        representationHandler.clearFields();
    }
}
