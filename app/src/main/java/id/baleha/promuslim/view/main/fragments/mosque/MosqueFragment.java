package id.baleha.promuslim.view.main.fragments.mosque;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import id.baleha.promuslim.R;
 

public class MosqueFragment extends Fragment {

    private MosqueViewModel mosqueViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        mosqueViewModel =
                ViewModelProviders.of(this).get(MosqueViewModel.class);
        View root = inflater.inflate(R.layout.fragment_mosque, container, false);
        final TextView textView = root.findViewById(R.id.text_mosque);
        mosqueViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
        return root;
    }
}