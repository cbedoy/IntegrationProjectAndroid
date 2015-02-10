package cbedoy.gymap.artifacts;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.HashMap;
import java.util.List;

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
public abstract class AbstractViewCell extends BaseAdapter
{
    protected List<HashMap<String, Object>> dataModel;

    public AbstractViewCell(List<HashMap<String, Object>> dataModel){
        this.dataModel = dataModel;
    }

    @Override
    public int getCount() {
        return dataModel.size();
    }

    @Override
    public Object getItem(int position) {
        return dataModel.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return null;
    }
}
