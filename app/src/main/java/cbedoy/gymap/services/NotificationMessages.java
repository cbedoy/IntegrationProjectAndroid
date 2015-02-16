package cbedoy.gymap.services;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.preference.DialogPreference;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import cbedoy.gymap.R;
import cbedoy.gymap.artifacts.AbstractDialog;
import cbedoy.gymap.artifacts.ApplicationLoader;
import cbedoy.gymap.interfaces.INotificationMessages;
import cbedoy.gymap.widgets.CBCircularProgressBar;

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
public class NotificationMessages extends AbstractDialog implements INotificationMessages
{

    private boolean isLoaderView;
    private ImageView mLoaderImage;
    private TextView mLoaderText;
    private CBCircularProgressBar mCircularProgressBar;
    private ObjectAnimator mProgressBarAnimator;
    private String mTextLoader;
    private int mIconResource;
    private Color mProgressColor;
    private Color mProgressBackgroundColor;

    public NotificationMessages(Activity activity) {
        super(activity);
    }


    @Override
    public View init()
    {
        this.view = ApplicationLoader.mainLayoutInflater.inflate(R.layout.loader_view, null);
        this.mLoaderImage = (ImageView)view.findViewById(R.id.notification_view_icon);
        this.mLoaderText = (TextView)view.findViewById(R.id.txtLoading);
        this.mCircularProgressBar = (CBCircularProgressBar)view.findViewById(R.id.notification_view_circle_dialog);
        this.mCircularProgressBar.setWheelSize(12);
        this.mCircularProgressBar.setProgressColor(Color.parseColor("#00796B"));
        this.mCircularProgressBar.setProgressBackgroundColor(Color.parseColor("#004D40"));
        if (this.mProgressBarAnimator != null) {
            this.mProgressBarAnimator.cancel();
        }
        animate(this.mCircularProgressBar, null, 1f, 1500);
        return view;
    }

    private void animate(final CBCircularProgressBar progressBar, final Animator.AnimatorListener listener, final float progress, final int duration) {

        this.mProgressBarAnimator = ObjectAnimator.ofFloat(progressBar, "progress", progress);
        this.mProgressBarAnimator.setDuration(duration);

        this.mProgressBarAnimator.addListener(new Animator.AnimatorListener() {

            @Override
            public void onAnimationCancel(final Animator animation) {
            }

            @Override
            public void onAnimationEnd(final Animator animation) {
                progressBar.setProgress(progress);
                progressBar.setProgress(0f);
            }

            @Override
            public void onAnimationRepeat(final Animator animation) {
            }

            @Override
            public void onAnimationStart(final Animator animation) {
            }
        });
        if (listener != null) {
            this.mProgressBarAnimator.addListener(listener);
        }
        this.mProgressBarAnimator.reverse();
        this.mProgressBarAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {

            @Override
            public void onAnimationUpdate(final ValueAnimator animation) {
                progressBar.setProgress((Float) animation.getAnimatedValue());
            }
        });
        this.mProgressBarAnimator.setRepeatCount(ValueAnimator.INFINITE);
        this.mProgressBarAnimator.start();


    }

    @Override
    public void showLoader() {
        isLoaderView = true;
        show();
    }

    @Override
    public void hideLoader() {
        isLoaderView = false;
        hide();
    }

    @Override
    public void showCodeWithCallback(K_ERROR error, final MessageRepresentationCallback callback) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setMessage("Error : "+error);
        builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                callback.onAccept();
            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                callback.onCancel();
            }
        });
        builder.show();

    }


    @Override
    public void reload() {

    }
}
