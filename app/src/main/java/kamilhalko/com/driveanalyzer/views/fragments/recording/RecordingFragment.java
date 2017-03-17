package kamilhalko.com.driveanalyzer.views.fragments.recording;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.gson.Gson;

import javax.inject.Inject;

import kamilhalko.com.driveanalyzer.R;
import kamilhalko.com.driveanalyzer.data.models.Trip;
import kamilhalko.com.driveanalyzer.databinding.FragmentRecordingBinding;
import kamilhalko.com.driveanalyzer.presenters.fragments.RecordingPresenter;
import kamilhalko.com.driveanalyzer.utils.GpsUtils;
import kamilhalko.com.driveanalyzer.views.BaseFragment;

public class RecordingFragment extends BaseFragment implements RecordingView {
    @Inject RecordingPresenter<RecordingFragment> recordingPresenter;
    private FragmentRecordingBinding binding;
    private RecordingFragmentCallbacks callback;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        try {
            callback = (RecordingFragment.RecordingFragmentCallbacks) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString()
                    + " must implement RecordingFragmentCallbacks");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentRecordingBinding.inflate(inflater, container, false);
        getActivityComponent().inject(this);
        setUpView();
        return binding.getRoot();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        recordingPresenter.onDetach();
    }

    @Override
    public void onStop() {
        super.onStop();
    }

    @Override
    public void setUpView() {
        recordingPresenter.onAttach(this);
        recordingPresenter.onStartRecording();
        setUpButtons();
    }

    @Override
    public void setUpButtons() {
        binding.startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                callback.startService();
                recordingPresenter.onStartRecording();
            }
        });

        binding.stopButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                callback.stopService();
            }
        });
    }

    @Override
    public void showStartLayout() {
        binding.recordingLinearLayout.setVisibility(View.GONE);
        binding.startLinearLayout.setVisibility(View.VISIBLE);
    }

    @Override
    public void showRecordingLayout() {
        binding.startLinearLayout.setVisibility(View.GONE);
        binding.recordingLinearLayout.setVisibility(View.VISIBLE);
    }

    @Override
    public void updateData(Trip trip) {
        if (!binding.recordingLinearLayout.isShown()) {
            showRecordingLayout();
        }
        Log.i("RecordingFragment", new Gson().toJson(trip));
    }

    @Override
    public void onRecordingFinished() {
        if (binding.recordingLinearLayout.isShown()) {
            showStartLayout();
        }
        if (!GpsUtils.isGpsActive(getBaseActivity())) {
            onError(getString(R.string.RecordingFragment_gps_disabled));
        }
    }

    public interface RecordingFragmentCallbacks {
        void startService();
        void stopService();
    }
}
