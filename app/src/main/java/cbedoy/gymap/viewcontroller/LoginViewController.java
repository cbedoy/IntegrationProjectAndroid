package cbedoy.gymap.viewcontroller;

import android.content.Intent;
import android.text.Editable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.HashMap;

import cbedoy.gymap.R;
import cbedoy.gymap.artifacts.AbstractViewController;
import cbedoy.gymap.artifacts.ApplicationLoader;
import cbedoy.gymap.business.login.interfaces.ILoginRepresentationDelegate;
import cbedoy.gymap.business.login.interfaces.ILoginRepresentationHandler;
import cbedoy.gymap.services.CBUtils;

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
    public View onCreateView(){
        view = ApplicationLoader.mainLayoutInflater.inflate(R.layout.login_view_controller, null);
        mUsernameView = (EditText) view.findViewById(R.id.mUsernameField);
        mPasswordView = (EditText) view.findViewById(R.id.mPasswordField);
        mActionLoginView = (Button) view.findViewById(R.id.mLoginAction);
        mActionSignUpView = (Button) view.findViewById(R.id.mSignUpAction);
        mActionLoginView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {

                String username = mUsernameView.getText().toString();
                String password = mPasswordView.getText().toString();
                representationDelegate.login(username, password);

                //Intent intent = new Intent(ApplicationLoader.mainContext, MapViewController.class);
                //ApplicationLoader.mainContext.startActivity(intent);
            }
        });
        mActionSignUpView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                representationDelegate.userNeedSignUp();
            }
        });
        return view;
    }

    @Override
    public void onAttachToWindow() {
        HashMap<String, Object> lastSession = CBUtils.getLastSession();
        mUsernameView.setText(lastSession.get("username").toString());
        mPasswordView.setText(lastSession.get("password").toString());
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
       mUsernameView.setText("");
    }

    @Override
    public void clearPassword() {
        mPasswordView.setText("");
    }

    @Override
    public void showLoginWithData() {
        parentActivity.presentViewFromTag(this.tag);
    }
}
