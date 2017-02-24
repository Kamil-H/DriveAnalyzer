package kamilhalko.com.driveanalyzer.views.fragments.recording;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import kamilhalko.com.driveanalyzer.databinding.FragmentRecordingBinding;
import kamilhalko.com.driveanalyzer.views.BaseFragment;

public class RecordingFragment extends BaseFragment {
    private FragmentRecordingBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentRecordingBinding.inflate(inflater, container, false);
        setUpView();
        return binding.getRoot();
    }

    private void setUpView() {

    }
}
