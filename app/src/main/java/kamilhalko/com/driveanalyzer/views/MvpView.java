package kamilhalko.com.driveanalyzer.views;

import android.view.View;

public interface MvpView {
    void showLoading(String message);
    void hideLoading();
    void onError(String message);
    void onError(String message, String buttonMessage, View.OnClickListener onClickListener);
}
