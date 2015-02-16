package cbedoy.gymap.artifacts;

import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.HashMap;
import java.util.List;
import java.util.Locale;

/**
 * Created by Carlos Bedoy on 10/02/2015.
 * <p/>
 * Mobile App Developer
 * GyMap
 * <p/>
 * E-mail: carlos.bedoy@gmail.com
 * Facebook: https://www.facebook.com/carlos.bedoy
 * Github: https://github.com/cbedoy
 */
public class LocationViewCell extends AbstractViewCell
{

    public LocationViewCell(List<HashMap<String, Object>> dataModel) {
        super(dataModel);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if(convertView == null){
            viewHolder = new ViewHolder();
            convertView = ApplicationLoader.mainLayoutInflater.inflate(-1, null);
            viewHolder.titleView = (TextView) convertView.findViewById(-1);
            viewHolder.latitudeView = (TextView) convertView.findViewById(-1);
            viewHolder.longitudeView = (TextView) convertView.findViewById(-1);
            viewHolder.descriptionView = (TextView) convertView.findViewById(-1);
            convertView.setTag(viewHolder);
        }else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        HashMap<String, Object> information = dataModel.get(position);
        float longitude                     = Float.parseFloat(information.get("longitude").toString());
        float latitude                      = Float.parseFloat(information.get("latitude").toString());
        String latitudeFormatted            = String.format(Locale.US, "%.4f", latitude);
        String longitudeFormatted           = String.format(Locale.US, "%.4f", longitude);
        viewHolder.titleView.setText(information.get("title").toString());
        viewHolder.latitudeView.setText(latitudeFormatted);
        viewHolder.longitudeView.setText(longitudeFormatted);
        viewHolder.descriptionView.setText(information.get("description").toString());

        return convertView;

    }

    private class ViewHolder{
        TextView titleView;
        TextView latitudeView;
        TextView longitudeView;
        TextView descriptionView;
    }
}
