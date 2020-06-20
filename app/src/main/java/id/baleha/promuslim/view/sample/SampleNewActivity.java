package id.baleha.promuslim.view.sample;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import id.baleha.promuslim.R;
import id.baleha.promuslim.view.place.NewPlaceFragment;
import id.baleha.promuslim.view.sample.ui.main.SampleNewFragment;

public class SampleNewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sample_new_activity);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.container, NewPlaceFragment.newInstance("pesantren"))
                    .commitNow();
        }
    }
}