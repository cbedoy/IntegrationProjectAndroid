package cbedoy.gymap.artifacts;

import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.nineoldandroids.view.ViewHelper;

import java.util.HashMap;
import java.util.List;

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
public class SignUpViewCell extends AbstractViewCell {

    public SignUpViewCell(List<HashMap<String, Object>> dataModel) {
        super(dataModel);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder viewHolder;
        if(convertView == null){
            viewHolder = new ViewHolder();
            convertView = ApplicationLoader.mainLayoutInflater.inflate(-1, null);
            viewHolder.editText = (EditText) convertView.findViewById(-1);
            convertView.setTag(viewHolder);
        }
        else
        {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        HashMap<String, Object> information = dataModel.get(position);
        viewHolder.editText.setText(information.get("value").toString());
        viewHolder.editText.setHint(information.get("hint").toString());
        viewHolder.editText.setTypeface(null);
        viewHolder.editText.setTextColor(-1);
        return convertView;
    }

    private class ViewHolder{
        EditText editText;
    }
}
