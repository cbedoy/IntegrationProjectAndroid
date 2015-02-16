package cbedoy.gymap.services;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.app.Activity;
import android.app.Dialog;
import android.graphics.Color;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import cbedoy.gymap.R;
import cbedoy.gymap.artifacts.AbstractDialog;
import cbedoy.gymap.artifacts.ApplicationLoader;
import cbedoy.gymap.interfaces.INotificationMessages;
import cbedoy.gymap.widgets.CBCircularProgressBar;

import static cbedoy.gymap.R.drawable.ic_error;
import static cbedoy.gymap.R.drawable.ic_network;
import static cbedoy.gymap.R.drawable.ic_success;
import static cbedoy.gymap.interfaces.INotificationMessages.K_ERROR.K_NETWORK;
import static cbedoy.gymap.interfaces.INotificationMessages.K_ERROR.K_SUCCCESS;

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
    private View notificationView;
    private TextView mTitle;
    private TextView mMessage;
    private View mAccept;
    private ImageView mNotificationIcon;

    public NotificationMessages(Activity activity) {
        super(activity);
    }


    @Override
    public View init()
    {
        if(view == null)
        {
            this.view = ApplicationLoader.mainLayoutInflater.inflate(R.layout.loader_view, null);
            this.mLoaderImage = (ImageView) view.findViewById(R.id.notification_view_icon);
            this.mLoaderText = (TextView) view.findViewById(R.id.txtLoading);
            this.mCircularProgressBar = (CBCircularProgressBar) view.findViewById(R.id.notification_view_circle_dialog);
            this.mCircularProgressBar.setWheelSize(12);
            this.mCircularProgressBar.setProgressColor(Color.parseColor("#00796B"));
            this.mCircularProgressBar.setProgressBackgroundColor(Color.parseColor("#004D40"));
            if (this.mProgressBarAnimator != null) {
                this.mProgressBarAnimator.cancel();
            }
            animate(this.mCircularProgressBar, null, 1f, 1500);
        }
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
    public void show() {
        final AbstractDialog weakSelf = this;
        this.activity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (weakSelf.dialog == null)
                    weakSelf.createDialogView();
                weakSelf.takeCurrentScreamShot();
                weakSelf.dialog.setContentView(isLoaderView ? view : notificationView);
                weakSelf.reload();
                weakSelf.dialog.show();
            }
        });
    }

    @Override
    public void showCodeWithCallback(K_ERROR error, final MessageRepresentationCallback callback) {

        initNotification();
        mTitle.setText(error == K_SUCCCESS ? "Success!!" : "Error");
        mNotificationIcon.setImageResource(error == K_SUCCCESS ? ic_success : error == K_NETWORK ? ic_network : ic_error);
        mMessage.setText(error + "");
        mAccept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(callback != null)
                    callback.onAccept();
                hide();
            }
        });
        show();
    }


    public  void createDialogView()
    {
        this.dialog = new Dialog(this.activity);
        this.dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.dialog.setContentView(isLoaderView ? init() : initNotification());
        this.dialog.setCanceledOnTouchOutside(true);
        this.dialog.getWindow().setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.MATCH_PARENT);
        this.dialog.getWindow().setWindowAnimations(R.style.other_apps_animation);
    }

    public View initNotification(){
        if(this.notificationView == null)
        {
            this.notificationView = activity.getLayoutInflater().inflate(R.layout.notification_view, null);
            this.mTitle = (TextView) notificationView.findViewById(R.id.mNotificationTitle);
            this.mMessage = (TextView) notificationView.findViewById(R.id.mNotificationMessage);
            this.mAccept = notificationView.findViewById(R.id.mNotificationAction);
            this.mNotificationIcon = (ImageView) notificationView.findViewById(R.id.mNotificationIcon);

        }
        return notificationView;
    }

    @Override
    public void reload() {

    }
}
