package kamilhalko.com.driveanalyzer.views;

import android.annotation.TargetApi;
import android.app.ProgressDialog;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public abstract class BaseActivity extends AppCompatActivity implements MvpView {
    private ProgressDialog progressDialog;

    @TargetApi(Build.VERSION_CODES.M)
    protected void requestPermissionsSafely(String[] permissions, int requestCode) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            requestPermissions(permissions, requestCode);
        }
    }

    @TargetApi(Build.VERSION_CODES.M)
    protected boolean hasPermission(String permission) {
        return Build.VERSION.SDK_INT < Build.VERSION_CODES.M ||
                checkSelfPermission(permission) == PackageManager.PERMISSION_GRANTED;
    }

    @Override
    public void showLoading(String message) {
        progressDialog = ProgressDialog.show(this, "", message, true);
    }

    @Override
    public void hideLoading() {
        if (progressDialog != null && progressDialog.isShowing()) {
            progressDialog.cancel();
        }
    }

    @Override
    public void onError(String message) {
        Snackbar snackbar = Snackbar.make(findViewById(android.R.id.content), message, Snackbar.LENGTH_SHORT);
        snackbar.show();
    }

    @Override
    public void onError(String message, String buttonMessage, View.OnClickListener onClickListener) {
        Snackbar snackbar = Snackbar.make(findViewById(android.R.id.content), message, Snackbar.LENGTH_SHORT);
        snackbar.setAction(buttonMessage, onClickListener);
        snackbar.show();
    }

    private Fragment createFragmentInstance(Class fragmentClass){
        Fragment fragment = null;
        try {
            fragment = (Fragment) fragmentClass.newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return fragment;
    }

    protected void displayFragment(Class fragmentClass, int containerId) {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(containerId, createFragmentInstance(fragmentClass), fragmentClass.getName())
                .commitAllowingStateLoss();
    }
}
