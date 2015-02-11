package cbedoy.gymap;

import android.app.Activity;
import android.content.Context;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.inputmethod.InputMethodManager;
import android.widget.ViewFlipper;

import java.util.HashMap;
import java.util.Map;

import cbedoy.gymap.R;
import cbedoy.gymap.interfaces.IViewController;
import cbedoy.gymap.interfaces.IViewManager;

import static cbedoy.gymap.interfaces.IViewController.TAG;


public class MasterViewController extends ActionBarActivity implements IViewManager
{


    private ViewFlipper viewFlipper;
    private HashMap<IViewController.TAG, IViewController> viewModel;
    private IViewController currentViewController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_master_view_controlle);
        viewModel = new HashMap<>();
    }


    @Override
    public void onBackPressed() {
        boolean allowBack = true;
        int displayed_child = this.viewFlipper.getDisplayedChild();
        View view = this.viewFlipper.getChildAt(displayed_child);

        for(Map.Entry<IViewController.TAG, IViewController> entry : this.viewModel.entrySet()) {
            IViewController child = entry.getValue();

            if(child.getView() == view) {
                allowBack = child.onBackPressed();
                break;
            }
        }

        if(allowBack)
            super.onBackPressed();

    }

    @Override
    public void presentViewFromTag(IViewController.TAG tag) {
        final MasterViewController self = this;
        final IViewController.TAG final_tag = tag;

        this.runOnUiThread(new Runnable() {
            @Override
            public void run() {

                IViewController child = self.viewModel.get(final_tag);

                View view = child.getView();
                if(currentViewController != null)
                    currentViewController.onRemoveToWindow();

                ((InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE)).hideSoftInputFromWindow(view.getWindowToken(), 0);

                int child_index = self.viewFlipper.indexOfChild(view);
                if(child_index < 0) {
                    self.viewFlipper.addView(view);
                    child_index = self.viewFlipper.indexOfChild(view);
                }

                int displayed_child = self.viewFlipper.getDisplayedChild();
                if(child_index != displayed_child)
                {

                    AlphaAnimation in = new AlphaAnimation(0.0f, 1.0f);
                    in.setDuration(600);
                    in.setZAdjustment(Animation.ZORDER_TOP);
                    self.viewFlipper.setInAnimation(in);

                    AlphaAnimation out = new AlphaAnimation(1.0f, 0.0f);
                    out.setDuration(600);
                    out.setZAdjustment(Animation.ZORDER_TOP);
                    self.viewFlipper.setOutAnimation(out);

                    self.viewFlipper.setDisplayedChild(child_index);

                }

                child.onAttachToWindow();
                currentViewController = child;
            }
        });
    }

    @Override
    public void addViewControllerFromTag(IViewController.TAG tag, IViewController viewController) {
        viewModel.put(tag, viewController);
    }

    @Override
    public void reActivateCurrentView() {
        final MasterViewController self = this;

        this.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                int displayed_child = self.viewFlipper.getDisplayedChild();
                View view = self.viewFlipper.getChildAt(displayed_child);

                for(Map.Entry<TAG, IViewController> entry : self.viewModel.entrySet()) {
                    IViewController child = entry.getValue();

                    if(child.getView() == view) {
                        child.toogleButtons(true);
                        break;
                    }
                }
            }
        });
    }

    @Override
    public Activity getParentActivity() {
        return this;
    }
}
