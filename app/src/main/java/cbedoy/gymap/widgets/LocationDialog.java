package cbedoy.gymap.widgets;

import android.app.Activity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
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
        mName.addTextChangedListener(new MyWatcher());
        mPhone.addTextChangedListener(new MyWatcher());
        mFullName.addTextChangedListener(new MyWatcher());
        mDescription.addTextChangedListener(new MyWatcher());
        mActionConfirm.setVisibility(View.INVISIBLE);
        return view;
    }

    private void validateFields() {
        if(mName.getText().length() > 0 && mPhone.getText().length() > 0 &&
                mFullName.getText().length() > 0 && mDescription.getText().length() > 0){

            Animation animation = new AlphaAnimation(0.00f, 1.00f);
            animation.setDuration(400);
            animation.setAnimationListener(new Animation.AnimationListener()
            {
                public void onAnimationStart(Animation animation) {
                    mActionConfirm.setVisibility(View.INVISIBLE);
                    mActionConfirm.setOnClickListener(null);
                }
                public void onAnimationRepeat(Animation animation) {}
                public void onAnimationEnd(Animation animation)
                {
                    mActionConfirm.setVisibility(View.VISIBLE);
                    mActionConfirm.setOnClickListener(listener);
                    ApplicationLoader.hideKeyboard(getActivity());
                }
            });
            mActionConfirm.startAnimation(animation);
        }
    }

    @Override
    public void reload()
    {

    }

    View.OnClickListener listener = new View.OnClickListener() {
        @Override
        public void onClick(View v)
        {
            String name = mName.getText().toString();
            String phone = mPhone.getText().toString();
            String fullName = mFullName.getText().toString();
            String description = mDescription.getText().toString();

            callback.run(name, phone, fullName, description);

            hide();
        }
    };

    public void setCallback(ILocationDialogCallback callback) {
        this.callback = callback;
    }

    public interface  ILocationDialogCallback {
        public void run(String... values);
    }

    private class MyWatcher implements TextWatcher{

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @Override
        public void afterTextChanged(Editable s) {
            validateFields();
        }
    }


}
