package id.baleha.promuslim.view.main.fragments.salat;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import butterknife.BindView;
import butterknife.ButterKnife;
import id.baleha.promuslim.R;
import id.baleha.promuslim.base.MvpFragment;
import id.baleha.promuslim.model.praytime.PrayTimeResponse;


public class SalatFragment extends MvpFragment<SalatPresenter> implements SalatView, View.OnClickListener {

//    private SalatViewModel salatViewModel;

    @BindView(R.id.progress)
    ProgressBar progressBar;

    @BindView(R.id.content_layout)
    LinearLayout contentLayout;

    @BindView(R.id.tv_date)TextView tvDate;
    @BindView(R.id.tv_date_hijr)TextView tvDateHijr;
    @BindView(R.id.tv_praytime_time_imsak)TextView tvTimeImsak;
    @BindView(R.id.tv_praytime_time_fajr)TextView tvTimeFajr;
    @BindView(R.id.tv_praytime_time_dhuhr)TextView tvTimeDhuhr;
    @BindView(R.id.tv_praytime_time_ashr)TextView tvTimeAshr;
    @BindView(R.id.tv_praytime_time_maghrib)TextView tvTimeMaghrib;
    @BindView(R.id.tv_praytime_time_isha)TextView tvTimeIsha;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
//        View root = inflater.inflate(R.layout.fragment_salat, container, false);
        /*
        salatViewModel = ViewModelProviders.of(this).get(SalatViewModel.class);
        final TextView textView = root.findViewById(R.id.text_salat);
        salatViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
        */

        View root = inflater.inflate(R.layout.fragment_praytime, container, false);
        ButterKnife.bind(this, root);
        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Long timeStamp = System.currentTimeMillis()/1000;
        presenter.loadData(timeStamp.toString());
    }

    @Override
    public void onClick(View view) {

    }

    @Override
    protected SalatPresenter createPresenter() {
        return new SalatPresenter(this);
    }

    @Override
    public void getDataSuccess(PrayTimeResponse model) {
        tvDate.setText(model.getData().getDate().getReadable());
        tvDateHijr.setText(model.getData().getDate().getHijri().getDate());

        tvTimeImsak.setText(model.getData().getTimings().getImsak());
        tvTimeFajr.setText(model.getData().getTimings().getFajr());
        tvTimeDhuhr.setText(model.getData().getTimings().getDhuhr());
        tvTimeAshr.setText(model.getData().getTimings().getAsr());
        tvTimeMaghrib.setText(model.getData().getTimings().getMaghrib());
        tvTimeIsha.setText(model.getData().getTimings().getIsha());
    }

    @Override
    public void refreshData() {

    }

    @Override
    public void showLoading() {
        progressBar.setVisibility(View.VISIBLE);
        contentLayout.setVisibility(View.GONE);
    }

    @Override
    public void hideLoading() {
        progressBar.setVisibility(View.GONE);
        contentLayout.setVisibility(View.VISIBLE);
    }

    @Override
    public void getDataSuccess(Object model) {
        System.out.println(model.toString());
        Toast.makeText(getActivity(), "Data succes: ",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void getDataFail(String message) {
        Toast.makeText(getActivity(), "Please load again: "+message,Toast.LENGTH_SHORT).show();
    }

    @Override
    public void refreshdata() {

    }
}