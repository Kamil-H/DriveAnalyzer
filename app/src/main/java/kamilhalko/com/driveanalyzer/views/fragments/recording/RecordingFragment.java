package kamilhalko.com.driveanalyzer.views.fragments.recording;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import javax.inject.Inject;

import kamilhalko.com.driveanalyzer.R;
import kamilhalko.com.driveanalyzer.data.models.Trip;
import kamilhalko.com.driveanalyzer.data.models.sensors.Accelerometer;
import kamilhalko.com.driveanalyzer.data.models.sensors.Gps;
import kamilhalko.com.driveanalyzer.data.models.sensors.Gyroscope;
import kamilhalko.com.driveanalyzer.databinding.FragmentRecordingBinding;
import kamilhalko.com.driveanalyzer.presenters.fragments.RecordingPresenter;
import kamilhalko.com.driveanalyzer.utils.DateUtils;
import kamilhalko.com.driveanalyzer.utils.GpsUtils;
import kamilhalko.com.driveanalyzer.utils.UnitUtils;
import kamilhalko.com.driveanalyzer.views.BaseFragment;

public class RecordingFragment extends BaseFragment implements RecordingView {
    @Inject RecordingPresenter<RecordingFragment> recordingPresenter;
    private FragmentRecordingBinding binding;
    private RecordingFragmentCallbacks callback;
    private Handler handler;

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

        binding.recordingLayout.stopButton.setOnClickListener(new View.OnClickListener() {
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
        if (trip == null) {
            return;
        }
        startTimer(trip.getMillis());
        if (trip.getAccelerometerList() != null) {
            List<Accelerometer> list = trip.getAccelerometerList();
            if (list.size() > 0) {
                binding.recordingLayout.accelerometerTextView.setText(String.valueOf(list.get(list.size() - 1).getAcceleration()));
            }
        }
        if (trip.getGyroscopeList() != null) {
            List<Gyroscope> list = trip.getGyroscopeList();
            if (list.size() > 0) {
                binding.recordingLayout.gyroscopeTextView.setText(String.valueOf(list.get(list.size() - 1).getAcceleration()));
            }
        }
        if (trip.getMagneticFieldList() != null) {
            List<Gyroscope> list = trip.getGyroscopeList();
            if (list.size() > 0) {
                binding.recordingLayout.magnetometerTextView.setText(String.valueOf(list.get(list.size() - 1).getAcceleration()));
            }
        }
        if (trip.getGpsList() != null) {
            List<Gps> list = trip.getGpsList();
            if (list.size() > 0) {
                binding.recordingLayout.actualSpeedTextView.setText(String.format("%s km/h", String.valueOf(list.get(list.size() - 1).getSpeed() * UnitUtils.MS_TO_KMH)));
            }
        }
    }

    @Override
    public void onRecordingFinished() {
        if (binding.recordingLinearLayout.isShown()) {
            showStartLayout();
        }
        if (!GpsUtils.isGpsActive(getBaseActivity())) {
            onError(getString(R.string.RecordingFragment_gps_disabled));
        }
        stopTimer();
    }

    @Override
    public void startTimer(final long startTime) {
        if (handler == null) {
            handler = new Handler();
            handler.post(new Runnable() {
                @Override
                public void run() {
                    long millis = System.currentTimeMillis() - startTime;
                    int seconds = (int) (millis / 1000);

                    binding.recordingLayout.timeTextView.setText(DateUtils.formatTime(seconds));
                    int mInterval = 1000;
                    if (handler != null) {
                        handler.postDelayed(this, mInterval);
                    }
                }
            });
        }
    }

    @Override
    public void stopTimer() {
        if (handler != null) {
            handler = null;
        }
    }

    public interface RecordingFragmentCallbacks {
        void startService();
        void stopService();
    }
}
