package cbedoy.gymap.business.signup;

import cbedoy.gymap.artifacts.BusinessController;
import cbedoy.gymap.business.signup.interfaces.ISignUpInformationDelegate;
import cbedoy.gymap.business.signup.interfaces.ISignUpInformationHandler;
import cbedoy.gymap.business.signup.interfaces.ISignUpRepresentationDelegate;
import cbedoy.gymap.business.signup.interfaces.ISignUpRepresentationHandler;
import cbedoy.gymap.business.signup.interfaces.ISignUpTransactionDelegate;
import cbedoy.gymap.business.signup.interfaces.ISignUpTransactionHandler;

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
}
