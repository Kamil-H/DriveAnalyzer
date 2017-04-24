package kamilhalko.com.driveanalyzer.views.fragments.gear;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import javax.inject.Inject;

import kamilhalko.com.driveanalyzer.R;
import kamilhalko.com.driveanalyzer.databinding.FragmentGearBinding;
import kamilhalko.com.driveanalyzer.presenters.fragments.GearPresenter;
import kamilhalko.com.driveanalyzer.views.BaseFragment;

public class GearFragment extends BaseFragment implements GearView {
    @Inject GearPresenter<GearFragment> gearPresenter;
    private FragmentGearBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentGearBinding.inflate(inflater, container, false);
        getActivityComponent().inject(this);
        setUpView();
        return binding.getRoot();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        gearPresenter.onDetach();
    }

    private void setUpView() {
        gearPresenter.onAttach(this);
        setUpGearButtons();
        setUpButtons();
    }

    @Override
    public void setUpButtons() {
        binding.startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gearPresenter.onStart(getBaseActivity());
            }
        });

        binding.gearLayout.stopButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gearPresenter.onStop();
            }
        });
    }

    public void setUpGearButtons() {
        binding.gearLayout.button1.setOnClickListener(gearButtonClicked(1));
        binding.gearLayout.button2.setOnClickListener(gearButtonClicked(2));
        binding.gearLayout.button3.setOnClickListener(gearButtonClicked(3));
        binding.gearLayout.button4.setOnClickListener(gearButtonClicked(4));
        binding.gearLayout.button5.setOnClickListener(gearButtonClicked(5));
        binding.gearLayout.buttonR.setOnClickListener(gearButtonClicked(-1));
        binding.gearLayout.buttonI.setOnClickListener(gearButtonClicked(-2));
    }

    public View.OnClickListener gearButtonClicked(final int gearPosition) {
        return new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gearPresenter.onReadButtonClicked(gearPosition);
            }
        };
    }

    @Override
    public void showStartLayout() {
        binding.gearLinearLayout.setVisibility(View.GONE);
        binding.startLinearLayout.setVisibility(View.VISIBLE);
    }

    @Override
    public void showGearLayout() {
        binding.startLinearLayout.setVisibility(View.GONE);
        binding.gearLinearLayout.setVisibility(View.VISIBLE);
    }

    @Override
    public void onSaved() {
        onError(getString(R.string.GearFragment_saved), getString(R.string.GearFragment_undo), new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gearPresenter.undo();
            }
        });
    }
}
