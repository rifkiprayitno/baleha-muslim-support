package id.baleha.promuslim.view.sample;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import id.baleha.promuslim.R;
import id.baleha.promuslim.view.place.PlaceFragment;

public class SampleFragmentToActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sample_fragment_to_activity);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.container, PlaceFragment.newInstance("halal"))
                    .commitNow();
        }
    }
}