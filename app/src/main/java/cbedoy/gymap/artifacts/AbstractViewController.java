package cbedoy.gymap.artifacts;

import android.view.View;

import java.util.HashMap;

import cbedoy.gymap.interfaces.IMementoHandler;
import cbedoy.gymap.interfaces.INotificationMessages;
import cbedoy.gymap.interfaces.IViewController;
import cbedoy.gymap.interfaces.IViewManager;

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
public abstract class AbstractViewController implements IViewController
{

    protected TAG tag;
    protected View view;
    protected boolean actionBack;
    protected IMementoHandler mementoHandler;
    protected IViewManager parentActivity;
    protected INotificationMessages notificationMessages;


    public abstract View onCreateView();


    public void setNotificationMessages(INotificationMessages notificationMessages) {
        this.notificationMessages = notificationMessages;
    }

    public void setMementoHandler(IMementoHandler mementoHandler) {
        this.mementoHandler = mementoHandler;
    }

    public void setTag(TAG tag) {
        this.tag = tag;
    }

    @Override
    public View getView()
    {
        if(view == null)
            view = onCreateView();
        return view;
    }

    @Override
    public boolean onBackPressed() {
       return true;
    }

    @Override
    public void toogleButtons(boolean b) {
        actionBack = b;
    }

    public HashMap<String, Object> getMementoData(){
        Memento topMemento = mementoHandler.getTopMemento();
        HashMap<String, Object> mementoData = topMemento.getMementoData();
        return mementoData;
    }

    public void setParentActivity(IViewManager parentActivity) {
        this.parentActivity = parentActivity;
    }
}
