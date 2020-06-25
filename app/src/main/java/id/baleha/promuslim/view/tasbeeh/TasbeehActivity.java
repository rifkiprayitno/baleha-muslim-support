package id.baleha.promuslim.view.tasbeeh;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import id.baleha.promuslim.R;

public class TasbeehActivity extends AppCompatActivity {

    Integer counter, countTarget, total;

    @BindView(R.id.tv_counter)
    TextView tvCounter;
    @BindView(R.id.tv_count_target)
    TextView tvCounterTarget;
    @BindView(R.id.tv_total)
    TextView tvTotal;
    @BindView(R.id.fab_increase)
    FloatingActionButton fabIncrease;
    @BindView(R.id.fab_decrease)
    FloatingActionButton fabDecrease;

    String strTarget;
    String strTotal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tasbeeh);
        ButterKnife.bind(this);
        initCounter();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.tasbeeh_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_reset_counter:
                break;
            case R.id.action_switch_sound:
                break;
            case R.id.action_switch_counter_target:
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    @OnClick(R.id.fab_increase)
    void increaseCounter(View view) {
        ++counter;
        if(counter >= countTarget)counter=0;
        tvCounter.setText(String.valueOf(counter));
        updateStringValue(++total);
    }

    @OnClick(R.id.fab_decrease)
    void decreaseCounter(View view) {
        --counter;
        if(counter <= 0)counter=0;
        tvCounter.setText(String.valueOf(counter));
        updateStringValue(--total);
    }

    private void initCounter() {
        if (total == null || counter == null || strTarget == null) {
            counter = 0;
            total = 0;
            countTarget = 33;
            strTarget = getResources().getString(R.string.txt_counter_target);
            strTotal = getResources().getString(R.string.txt_counter_total);
            strTarget = String.format(strTarget, countTarget);
            strTotal = String.format(strTotal, 0);
        }

        tvCounter.setText(String.valueOf(counter));
        tvCounterTarget.setText(strTarget);
        tvTotal.setText(strTotal);
    }

    void updateStringValue(int total){
        strTotal = getResources().getString(R.string.txt_counter_total);
        strTotal = String.format(strTotal, total);
        tvTotal.setText(strTotal);
    }
}