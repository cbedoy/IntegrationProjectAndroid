package cbedoy.gymap.business.map;

import android.content.Intent;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Random;

import cbedoy.gymap.GoogleMapViewController;
import cbedoy.gymap.artifacts.ApplicationLoader;
import cbedoy.gymap.artifacts.BusinessController;
import cbedoy.gymap.business.map.interfaces.IMapInformationDelegate;
import cbedoy.gymap.business.map.interfaces.IMapInformationHandler;
import cbedoy.gymap.business.map.interfaces.IMapRepresentationDelegate;
import cbedoy.gymap.business.map.interfaces.IMapRepresentationHandler;
import cbedoy.gymap.business.map.interfaces.IMapTransactionDelegate;
import cbedoy.gymap.business.map.interfaces.IMapTransactionHandler;
import cbedoy.gymap.services.InformationService;

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
public class MapBusinessController extends BusinessController implements IMapInformationDelegate, IMapRepresentationDelegate, IMapTransactionDelegate
{
    private IMapInformationHandler informationHandler;
    private IMapTransactionHandler transactionHandler;
    private IMapRepresentationHandler representationHandler;

    public void setInformationHandler(IMapInformationHandler informationHandler) {
        this.informationHandler = informationHandler;
    }

    public void setTransactionHandler(IMapTransactionHandler transactionHandler) {
        this.transactionHandler = transactionHandler;
    }

    public void setRepresentationHandler(IMapRepresentationHandler representationHandler) {
        this.representationHandler = representationHandler;
    }

    @Override
    public void mapResponse(HashMap<String, Object> response)
    {
        boolean status = (boolean) response.get("status");
        if(status)
        {
            mementoHandler.setStateForOwner(response, "map_response");
            ArrayList<HashMap<String, Object>> random_locations = (ArrayList<HashMap<String, Object>>) response.get("random_locations");
            ArrayList<HashMap<String, Object>> selected_locations = new ArrayList<>();
            Collections.shuffle(random_locations);
            for(int i = 0 ; i < 25 ; i++)
            {
                selected_locations.add(random_locations.get(i));
            }
            HashMap<String, Object> data = new HashMap<>();
            data.put("selected_locations", selected_locations);
            mementoHandler.setStateForOwner(data, this);

            Intent intent = new Intent(ApplicationLoader.mainContext, GoogleMapViewController.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            ApplicationLoader.mainContext.startActivity(intent);
        }
    }

    @Override
    public void addNewPointWithData(HashMap<String, Object> pointData) {

    }

    @Override
    public void loadLocations()
    {
        informationHandler.requestMapInformation();
    }
}
