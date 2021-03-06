package kamilhalko.com.driveanalyzer.views.fragments.settings;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import kamilhalko.com.driveanalyzer.databinding.FragmentSettingsBinding;
import kamilhalko.com.driveanalyzer.views.BaseFragment;

public class SettingsFragment extends BaseFragment {
    private FragmentSettingsBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentSettingsBinding.inflate(inflater, container, false);
        setUpView();
        return binding.getRoot();
    }

    private void setUpView() {

    }
}
