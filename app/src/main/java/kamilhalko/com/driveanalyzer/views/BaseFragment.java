package kamilhalko.com.driveanalyzer.views;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.view.View;

import kamilhalko.com.driveanalyzer.dependency_injection.component.ActivityComponent;

public abstract class BaseFragment extends Fragment implements MvpView {
    private BaseActivity activity;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof BaseActivity) {
            this.activity = (BaseActivity) context;
        }
    }

    protected BaseActivity getBaseActivity() {
        return activity;
    }

    @Override
    public void showLoading(String message) {
        if (activity != null) {
            activity.showLoading(message);
        }
    }

    @Override
    public void hideLoading() {
        if (activity != null) {
            activity.hideLoading();
        }
    }

    @Override
    public void onError(String message) {
        if (activity != null) {
            activity.onError(message);
        }
    }

    @Override
    public void onError(String message, String buttonMessage, View.OnClickListener onClickListener) {
        if (activity != null) {
            activity.onError(message, buttonMessage, onClickListener);
        }
    }

    public ActivityComponent getActivityComponent() {
        if (activity != null) {
            return activity.getActivityComponent();
        }
        return null;
    }
}
