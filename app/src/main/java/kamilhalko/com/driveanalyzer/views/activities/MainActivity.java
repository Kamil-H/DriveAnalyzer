package kamilhalko.com.driveanalyzer.views.activities;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.view.Menu;
import android.view.MenuItem;

import kamilhalko.com.driveanalyzer.R;
import kamilhalko.com.driveanalyzer.databinding.ActivityMainBinding;
import kamilhalko.com.driveanalyzer.dependency_injection.Injector;
import kamilhalko.com.driveanalyzer.presenters.activities.MainPresenter;
import kamilhalko.com.driveanalyzer.views.BaseActivity;
import kamilhalko.com.driveanalyzer.views.fragments.history.HistoryFragment;
import kamilhalko.com.driveanalyzer.views.fragments.recording.RecordingFragment;
import kamilhalko.com.driveanalyzer.views.fragments.settings.SettingsFragment;

public class MainActivity extends BaseActivity implements MainView, BottomNavigationView.OnNavigationItemSelectedListener {
    private MainPresenter mainPresenter;
    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        setUpView();
    }

    private void setUpView() {
        mainPresenter = Injector.injectPresenter(this);

        setUpToolbar();
        setUpNavigationBottomView();
    }

    private void setUpToolbar() {
        binding.toolbar.setTitle(getString(R.string.app_name));
        setSupportActionBar(binding.toolbar);
    }

    private void setUpNavigationBottomView() {
        binding.bottomNavigation.setOnNavigationItemSelectedListener(this);
        onNavigationItemSelected(binding.bottomNavigation.getMenu().getItem(0));
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.bottom_recording:
                displayFragment(RecordingFragment.class, R.id.container);
                break;

            case R.id.bottom_history:
                displayFragment(HistoryFragment.class, R.id.container);
                break;

            case R.id.bottom_settings:
                displayFragment(SettingsFragment.class, R.id.container);
                break;
        }
        return true;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menuBluetooth:
                return true;
            case R.id.menuSettings:
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
