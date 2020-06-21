package id.baleha.promuslim.base;

import android.app.Activity;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import okhttp3.Call;
import rx.subscriptions.CompositeSubscription;

public class BaseActivity extends AppCompatActivity {

    public Activity activity;
    CompositeSubscription compositeSubscription;
    List<Call> calls;

    @Override
    public void setContentView(int layoutResID) {
        super.setContentView(layoutResID);
        activity=this;
        ButterKnife.bind(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        onCancelled();
        onUnSubscribe();
    }

    private void onCancelled(){
        if(calls != null && calls.size() > 0){
            for (Call call : calls){
                if (!call.isCanceled()){
                    call.cancel();
                }
            }
        }
    }

    private void onUnSubscribe(){
        if (compositeSubscription != null && compositeSubscription.hasSubscriptions()){
            compositeSubscription.unsubscribe();
        }
    }

    public void addCalls(Call call){
        if(call == null){
            calls = new ArrayList<>();
        }
        calls.add(call);
    }
}
