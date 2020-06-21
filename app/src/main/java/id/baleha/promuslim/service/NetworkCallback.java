package id.baleha.promuslim.service;

import android.util.Log;

import retrofit2.HttpException;
import rx.Subscriber;

public abstract class NetworkCallback<M> extends Subscriber<M> {

    private static final String TAG = NetworkCallback.class.getName();
    public abstract  void onSuccess(M model);
    public abstract  void onFailure(String Message);
    public abstract  void onFinish();

    @Override
    public void onError(Throwable e) {
        e.printStackTrace();
        if(e instanceof HttpException){
            HttpException httpException = (HttpException) e;
            int code = httpException.code();
            String message = httpException.getMessage();
            Log.i(TAG, "code: "+code);
            onFailure(message);
        } else {
            onFailure(e.getMessage());
        }

        onFinish();
    }

    @Override
    public void onNext(M m) {
        onSuccess(m);
    }

    @Override
    public void onCompleted() {
        onFinish();
    }
}
