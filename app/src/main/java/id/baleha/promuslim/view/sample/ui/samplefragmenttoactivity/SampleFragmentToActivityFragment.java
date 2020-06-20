package id.baleha.promuslim.view.sample.ui.samplefragmenttoactivity;

import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import id.baleha.promuslim.R;


public class SampleFragmentToActivityFragment extends Fragment {

    private SampleFragmentToActivityViewModel mViewModel;

    public static SampleFragmentToActivityFragment newInstance() {
        return new SampleFragmentToActivityFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.sample_fragment_to_activity_fragment, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(SampleFragmentToActivityViewModel.class);
        // TODO: Use the ViewModel
    }

}