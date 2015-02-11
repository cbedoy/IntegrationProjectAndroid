package cbedoy.gymap.viewcontroller;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import cbedoy.gymap.R;
import cbedoy.gymap.artifacts.AbstractViewController;
import cbedoy.gymap.artifacts.ApplicationLoader;
import cbedoy.gymap.business.login.interfaces.ILoginRepresentationDelegate;
import cbedoy.gymap.business.login.interfaces.ILoginRepresentationHandler;

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
public class LoginViewController extends AbstractViewController implements ILoginRepresentationHandler
{

    private EditText mUsernameView;
    private EditText mPasswordView;
    private Button mActionLoginView;
    private Button mActionSignUpView;

    private ILoginRepresentationDelegate representationDelegate;

    public void setRepresentationDelegate(ILoginRepresentationDelegate representationDelegate) {
        this.representationDelegate = representationDelegate;
    }

    @Override
    public View onCreateView()
        view = ApplicationLoader.mainLayoutInflater.inflate(R.layout.login_view_controller, null);
        mUsernameView = (EditText) view.findViewById(R.id.mUsernameField);
        mPasswordView = (EditText) view.findViewById(R.id.mPasswordField);
        mActionLoginView = (Button) view.findViewById(R.id.mLoginAction);
        mActionSignUpView = (Button) view.findViewById(R.id.mSignUpAction);

        return view;
    }

    @Override
    public void onAttachToWindow() {

    }

    @Override
    public void onRemoveToWindow() {

    }

    @Override
    public boolean onBackPressed() {
        return super.onBackPressed();
    }

    @Override
    public void clearUsername() {

    }

    @Override
    public void clearPassword() {

    }

    @Override
    public void showLoginWithData() {

    }
}
