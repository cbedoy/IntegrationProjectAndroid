package cbedoy.gymap.business.map;

import java.util.HashMap;

import cbedoy.gymap.artifacts.BusinessController;
import cbedoy.gymap.business.map.interfaces.IMapInformationDelegate;
import cbedoy.gymap.business.map.interfaces.IMapInformationHandler;
import cbedoy.gymap.business.map.interfaces.IMapRepresentationDelegate;
import cbedoy.gymap.business.map.interfaces.IMapRepresentationHandler;
import cbedoy.gymap.business.map.interfaces.IMapTransactionDelegate;
import cbedoy.gymap.business.map.interfaces.IMapTransactionHandler;

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
    public void mapResponse(HashMap<String, Object> response) {

    }

    @Override
    public void addNewPointWithData(HashMap<String, Object> pointData) {

    }
}
