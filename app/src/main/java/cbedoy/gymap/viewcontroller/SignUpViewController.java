package cbedoy.gymap.viewcontroller;

import android.view.View;
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
    private ListView mListView;
    private SignUpViewCell signUpViewCell;
    private ISignUpRepresentationDelegate representationDelegate;

    public void setRepresentationDelegate(ISignUpRepresentationDelegate representationDelegate) {
        this.representationDelegate = representationDelegate;
    }

    @Override
    public View onCreateView() {
        view = ApplicationLoader.mainLayoutInflater.inflate(R.layout.signup_view_controller, null);
        mFloatinActionButton = (FloatingActionButton) view.findViewById(R.id.mSignUpActionFinish);
        mListView = (ListView) view.findViewById(R.id.mSignUpListView);

        mFloatinActionButton.attachToListView(mListView);
        return view;
    }

    @Override
    public void onAttachToWindow() {
        HashMap<String, Object> mementoData = getMementoData();

    }

    @Override
    public void onRemoveToWindow() {

    }

    @Override
    public boolean onBackPressed() {
        return super.onBackPressed();
    }

    @Override
    public void showSignUp() {

    }

    @Override
    public void clearFields() {

    }
}
