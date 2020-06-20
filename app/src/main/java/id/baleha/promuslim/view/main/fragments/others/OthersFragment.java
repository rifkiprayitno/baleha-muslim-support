package id.baleha.promuslim.view.main.fragments.others;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.sql.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import id.baleha.promuslim.R;
import id.baleha.promuslim.view.compass.CompassActivity;
import id.baleha.promuslim.view.main.fragments.mosque.MosqueViewModel;
import id.baleha.promuslim.view.place.NewPlaceFragment;
import id.baleha.promuslim.view.sample.SampleFragmentToActivity;
import id.baleha.promuslim.view.sample.SampleNewActivity;
import id.baleha.promuslim.view.tasbeeh.TasbeehActivity;


public class OthersFragment extends Fragment {

    Context context;
    private OthersViewModel othersViewModel;
    List<String> titleMenudata ;
    ArrayList iconMenudata = new ArrayList<>(Arrays.asList(R.drawable.ic_baseline_flight_24,
            R.drawable.ic_dashboard_black_24dp,
            R.drawable.ic_nav__bottom_contacts_24,
            R.drawable.ic_nav_bottom_releases_24));
    ArrayList detenationMenudata = new ArrayList<>(Arrays.asList(CompassActivity.class,
            SampleNewActivity.class,
            TasbeehActivity.class,
            SampleFragmentToActivity.class));

    RecyclerView recyclerView;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        othersViewModel = ViewModelProviders.of(this).get(OthersViewModel.class);
        View root = inflater.inflate(R.layout.fragment_others, container, false);
        final TextView textView = root.findViewById(R.id.text_others);
        othersViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });

        context = getContext();
        titleMenudata = Arrays.asList(getContext().getResources().getStringArray(R.array.other_item_title));

        recyclerView = (RecyclerView) root.findViewById(R.id.rv_other_menus);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity().getApplicationContext(), 2);
        recyclerView.setLayoutManager(gridLayoutManager);
        OthersRecyclerAdapter adapter = new OthersRecyclerAdapter(titleMenudata, iconMenudata, detenationMenudata, context);
        recyclerView.setAdapter(adapter);

        return root;
    }
}