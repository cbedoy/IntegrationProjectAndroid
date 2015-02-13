package cbedoy.gymap.viewcontroller;

import android.view.View;
import android.widget.EditText;
import android.widget.ListView;

import com.melnykov.fab.FloatingActionButton;

import java.util.HashMap;

import cbedoy.gymap.R;
import cbedoy.gymap.artifacts.AbstractViewController;
import cbedoy.gymap.artifacts.ApplicationLoader;
import cbedoy.gymap.artifacts.Memento;
import cbedoy.gymap.artifacts.SignUpViewCell;
import cbedoy.gymap.business.signup.interfaces.ISignUpRepresentationDelegate;
import cbedoy.gymap.business.signup.interfaces.ISignUpRepresentationHandler;

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
public class SignUpViewController extends AbstractViewController implements ISignUpRepresentationHandler
{

    private FloatingActionButton mFloatinActionButton;
    private ISignUpRepresentationDelegate representationDelegate;
    private EditText mUsername;
    private EditText mPassword;
    private EditText mConfirmPassword;
    private EditText mFirstName;
    private EditText mLastName;

    public void setRepresentationDelegate(ISignUpRepresentationDelegate representationDelegate) {
        this.representationDelegate = representationDelegate;
    }

    @Override
    public View onCreateView() {
        view = ApplicationLoader.mainLayoutInflater.inflate(R.layout.signup_view_controller, null);
        mUsername = (EditText) view.findViewById(R.id.mUsername);
        mPassword = (EditText) view.findViewById(R.id.mPassword);
        mConfirmPassword = (EditText) view.findViewById(R.id.mConfirmPassword);
        mFirstName = (EditText) view.findViewById(R.id.mFirstName);
        mLastName = (EditText) view.findViewById(R.id.mLastName);

        mFloatinActionButton = (FloatingActionButton) view.findViewById(R.id.mSignUpActionFinish);
        mFloatinActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fillData();
            }
        });
        return view;
    }

    private void fillData() {
        String username = mUsername.getText().toString();
        String password = mPassword.getText().toString();
        String confirmPassword = mConfirmPassword.getText().toString();
        String firstName = mFirstName.getText().toString();
        String lastName = mLastName.getText().toString();

        if(password.equals(confirmPassword))
        {
            HashMap<String, Object> data = new HashMap<>();
            data.put("username", username);
            data.put("password", password);
            data.put("fistName", firstName);
            data.put("lastName", lastName);
            representationDelegate.signUserWithData(data);
        }
        else
        {

        }
    }

    @Override
    public void onAttachToWindow() {
        HashMap<String, Object> mementoData = getMementoData();

    }

    @Override
    public void onRemoveToWindow() {

    }

    @Override
    public boolean onBackPressed()
    {
        parentActivity.presentViewFromTag(TAG.LOGIN);
        return false;
    }

    @Override
    public void showSignUp() {
        parentActivity.presentViewFromTag(tag);
    }

    @Override
    public void clearFields() {

    }
}
