package id.baleha.promuslim.view.main.fragments.asma;

import android.content.Intent;
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
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import id.baleha.promuslim.R;
import id.baleha.promuslim.base.BaseFragment;
import id.baleha.promuslim.base.MvpFragment;
import id.baleha.promuslim.model.asma.Asma;
import id.baleha.promuslim.model.asma.DataItem;


public class AsmaFragment extends MvpFragment<AsmaPresenter> implements AsmaView, View.OnClickListener {

//    private AsmaViewModel asmaViewModel;

    @BindView(R.id.progress)
    ProgressBar progressBar;

    @BindView(R.id.recyclerView)
    RecyclerView contentLayout;

    private List<DataItem> list;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        /*
        asmaViewModel = ViewModelProviders.of(this).get(AsmaViewModel.class);
        View root = inflater.inflate(R.layout.fragment_asma, container, false);
        final TextView textView = root.findViewById(R.id.text_asma);
        asmaViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
        */

        View root = inflater.inflate(R.layout.fragment_asma, container, false);
        ButterKnife.bind(this, root);
        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        presenter.loadData();
        contentLayout.setLayoutManager(new LinearLayoutManager(getActivity()));
    }

    @Override
    public void onClick(View view) {

    }

    @Override
    protected AsmaPresenter createPresenter() {
        return new AsmaPresenter(this);
    }

    @Override
    public void getDataSuccess(Asma asma) {
        this.list = asma.getData();
        contentLayout.setAdapter(new RecyclerAsmaAdapter(list, R.layout.item_asma, getActivity()));
    }

    @Override
    public void moveToDetail(Intent intent) {

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