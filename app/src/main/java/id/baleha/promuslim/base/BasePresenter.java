package id.baleha.promuslim.base;

import android.util.Log;

import id.baleha.promuslim.service.NetworkClient;
import id.baleha.promuslim.service.NetworkStores;
import rx.Observable;
import rx.Scheduler;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

public class BasePresenter<V> {

    public V mvpView;
    protected NetworkStores apiStores;
    private CompositeSubscription compositeSubscription;
    private Subscriber subscriber;

    public void attachView(V MVPView) {
        this.mvpView = MVPView;
        apiStores = NetworkClient.getRetrofitPrayTime().create(NetworkStores.class);
    }

    public void dettachView() {
        this.mvpView = null;
        onUnsubscribe();
    }

    public void onUnsubscribe() {
        if (compositeSubscription != null && compositeSubscription.hasSubscriptions()) {
            compositeSubscription.unsubscribe();
            Log.w("RLOG", "onUnsubsribe");
        }
    }

    protected void addSubscribe(Observable observable, Subscriber subscriber) {
        this.subscriber = subscriber;
        if (compositeSubscription == null) {
            compositeSubscription = new CompositeSubscription();
        }

        compositeSubscription.add(observable
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber)
        );
    }

    public  void stop(){
        if(subscriber != null){
            subscriber.unsubscribe();
        }
    }

}
