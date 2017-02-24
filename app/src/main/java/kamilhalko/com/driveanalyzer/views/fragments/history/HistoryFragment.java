package kamilhalko.com.driveanalyzer.views.fragments.history;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import kamilhalko.com.driveanalyzer.databinding.FragmentHistoryBinding;
import kamilhalko.com.driveanalyzer.views.BaseFragment;

public class HistoryFragment extends BaseFragment {
    private FragmentHistoryBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentHistoryBinding.inflate(inflater, container, false);
        setUpView();
        return binding.getRoot();
    }

    private void setUpView() {

    }
}
