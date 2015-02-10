package cbedoy.gymap.business;

import cbedoy.gymap.artifacts.BusinessController;
import cbedoy.gymap.business.login.interfaces.ILoginTransactionDelegate;
import cbedoy.gymap.business.login.interfaces.ILoginTransactionHandler;
import cbedoy.gymap.business.map.interfaces.IMapTransactionDelegate;
import cbedoy.gymap.business.map.interfaces.IMapTransactionHandler;
import cbedoy.gymap.business.signup.interfaces.ISignUpTransactionDelegate;
import cbedoy.gymap.business.signup.interfaces.ISignUpTransactionHandler;

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

}
