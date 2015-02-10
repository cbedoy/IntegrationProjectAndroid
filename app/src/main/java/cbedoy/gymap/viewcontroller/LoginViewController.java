package cbedoy.gymap.viewcontroller;

import android.view.View;

import cbedoy.gymap.artifacts.AbstractViewController;
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

    private ILoginRepresentationDelegate representationDelegate;

    public void setRepresentationDelegate(ILoginRepresentationDelegate representationDelegate) {
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

    @Override
    public void clearUsername() {

    }

    @Override
    public void clearPassword() {

    }
}
