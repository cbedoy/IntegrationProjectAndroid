package cbedoy.gymap.artifacts;

import cbedoy.gymap.interfaces.IBackCore;
import cbedoy.gymap.interfaces.IMementoHandler;
import cbedoy.gymap.interfaces.INotificationMessages;

public class BusinessController implements IBackCore {

    protected IMementoHandler mementoHandler;
    protected INotificationMessages notificationMessages;

    public void setMementoHandler(IMementoHandler mementoHandler) {
        this.mementoHandler = mementoHandler;
    }

    public void setNotificationMessages(INotificationMessages notificationMessages) {
        this.notificationMessages = notificationMessages;
    }

    @Override
    public void backRequested() {
    }

}
