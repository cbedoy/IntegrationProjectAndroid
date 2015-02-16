package cbedoy.gymap.widgets;

import android.app.Activity;
import android.view.View;
import android.widget.EditText;

import java.util.HashMap;

import cbedoy.gymap.R;
import cbedoy.gymap.artifacts.AbstractDialog;
import cbedoy.gymap.artifacts.ApplicationLoader;

/**
 * Created by Carlos Bedoy on 16/02/2015.
 * <p/>
 * Mobile App Developer
 * GyMap
 * <p/>
 * E-mail: carlos.bedoy@gmail.com
 * Facebook: https://www.facebook.com/carlos.bedoy
 * Github: https://github.com/cbedoy
 */
public class LocationDialog extends AbstractDialog
{
    private View mActionConfirm;
    private ILocationDialogCallback callback;
    private EditText mName;
    private EditText mFullName;
    private EditText mDescription;
    private EditText mPhone;
    public LocationDialog(Activity activity) {
        super(activity);
    }

    @Override
    public View init()
    {
        view = ApplicationLoader.mainLayoutInflater.inflate(R.layout.location_dialog, null);
        mName = (EditText) view.findViewById(R.id.mName);
        mPhone = (EditText) view.findViewById(R.id.mPhone);
        mFullName = (EditText) view.findViewById(R.id.mFullname);
        mDescription = (EditText) view.findViewById(R.id.mDescription);
        mActionConfirm = view.findViewById(R.id.button);
        mActionConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                String name = mName.getText().toString();
                String phone = mPhone.getText().toString();
                String fullName = mFullName.getText().toString();
                String description = mDescription.getText().toString();

                callback.run(name, phone, fullName, description);
            }
        });
        return view;
    }

    @Override
    public void reload()
    {

    }

    public void setCallback(ILocationDialogCallback callback) {
        this.callback = callback;
    }

    public interface  ILocationDialogCallback {
        public void run(String... values);
    }
}
