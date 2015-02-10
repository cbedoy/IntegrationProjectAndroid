package cbedoy.gymap.viewcontroller;

import android.view.View;

import cbedoy.gymap.artifacts.AbstractViewController;
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

    private ISignUpRepresentationDelegate representationDelegate;

    public void setRepresentationDelegate(ISignUpRepresentationDelegate representationDelegate) {
        this.representationDelegate = representationDelegate;
    }

    @Override
    public View onCreateView() {
        return null;
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
}
