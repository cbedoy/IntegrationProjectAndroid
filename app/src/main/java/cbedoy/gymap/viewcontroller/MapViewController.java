package cbedoy.gymap.viewcontroller;

import android.view.View;

import java.util.ArrayList;
import java.util.HashMap;

import cbedoy.gymap.artifacts.AbstractViewController;
import cbedoy.gymap.business.map.interfaces.IMapRepresentationDelegate;
import cbedoy.gymap.business.map.interfaces.IMapRepresentationHandler;

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
public class MapViewController extends AbstractViewController implements IMapRepresentationHandler
{

    private IMapRepresentationDelegate representationDelegate;

    public void setRepresentationDelegate(IMapRepresentationDelegate representationDelegate) {
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
    public void showMapWithData(ArrayList<HashMap<String, Object>> points) {

    }
}
