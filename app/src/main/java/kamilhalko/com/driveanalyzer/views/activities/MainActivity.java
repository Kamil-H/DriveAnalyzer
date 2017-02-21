package kamilhalko.com.driveanalyzer.views.activities;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import kamilhalko.com.driveanalyzer.R;
import kamilhalko.com.driveanalyzer.databinding.ActivityMainBinding;
import kamilhalko.com.driveanalyzer.dependency_injection.Injector;
import kamilhalko.com.driveanalyzer.presenters.activities.MainPresenter;
import kamilhalko.com.driveanalyzer.views.BaseActivity;

public class MainActivity extends BaseActivity implements MainView {
    private MainPresenter mainPresenter;
    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        mainPresenter = Injector.injectPresenter(this);

        setUpToolbar();
    }

    private void setUpToolbar() {
        binding.toolbar.setTitle(getString(R.string.app_name));
        setSupportActionBar(binding.toolbar);
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
