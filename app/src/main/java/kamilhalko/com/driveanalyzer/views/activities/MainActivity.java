package kamilhalko.com.driveanalyzer.views.activities;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import kamilhalko.com.driveanalyzer.R;
import kamilhalko.com.driveanalyzer.databinding.ActivityMainBinding;
import kamilhalko.com.driveanalyzer.dependency_injection.Injector;
import kamilhalko.com.driveanalyzer.presenters.activities.MainPresenter;
import kamilhalko.com.driveanalyzer.services.DriveAnalyzeService;
import kamilhalko.com.driveanalyzer.utils.GpsUtils;
import kamilhalko.com.driveanalyzer.views.BaseActivity;
import kamilhalko.com.driveanalyzer.views.fragments.history.HistoryFragment;
import kamilhalko.com.driveanalyzer.views.fragments.recording.RecordingFragment;
import kamilhalko.com.driveanalyzer.views.fragments.settings.SettingsFragment;

public class MainActivity extends BaseActivity implements MainView, BottomNavigationView.OnNavigationItemSelectedListener, RecordingFragment.RecordingFragmentCallbacks {
    private MainPresenter mainPresenter;
    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        setUpView();
    }

    @Override
    public void setUpView() {
        mainPresenter = Injector.injectPresenter(this);

        setUpToolbar();
        setUpNavigationBottomView();
    }

    @Override
    public void setUpToolbar() {
        binding.toolbar.setTitle(getString(R.string.app_name));
        setSupportActionBar(binding.toolbar);
    }

    @Override
    public void setUpNavigationBottomView() {
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

    @Override
    public void startService() {
        if (hasPermission(Manifest.permission.ACCESS_FINE_LOCATION)){
            startDriveAnalyzeService();
        } else {
            requestPermissionsSafely(new String[] {Manifest.permission.ACCESS_FINE_LOCATION}, 0);
        }
    }

    @Override
    public void stopService() {
        if (DriveAnalyzeService.isRunning(this)) {
            DriveAnalyzeService.stopService(this);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 0) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                startDriveAnalyzeService();
            } else {
                onError(getString(R.string.MainActivity_gps_permission_needed), getString(R.string.MainActivity_allow), new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        requestPermissionsSafely(new String[] {Manifest.permission.ACCESS_FINE_LOCATION}, 0);
                    }
                });
            }
        }
    }

    @Override
    public void startDriveAnalyzeService() {
        if (isGpsEnabled()) {
            if (!DriveAnalyzeService.isRunning(this)) {
                DriveAnalyzeService.startService(this);
            }
        }
    }

    @Override
    public boolean isGpsEnabled() {
        if (GpsUtils.isGpsActive(this)) {
            return true;
        } else {
            onError(getString(R.string.MainActivity_gps_needed), getString(R.string.MainActivity_enable), new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                    startActivity(intent);
                }
            });
            return false;
        }
    }
}
