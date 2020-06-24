package id.baleha.promuslim.view.main.fragments.asma;

import android.app.Activity;
import android.content.Intent;

import id.baleha.promuslim.base.BasePresenter;
import id.baleha.promuslim.model.asma.Asma;
import id.baleha.promuslim.model.asma.DataItem;
import id.baleha.promuslim.service.NetworkCallback;

public class AsmaPresenter extends BasePresenter<AsmaView> {

    AsmaPresenter(AsmaView view) {
        super.attachView(view);
    }

    void loadData() {
        mvpView.showLoading();
        addSubscribe(apiStores.getAsmaAlHusna(), new NetworkCallback<Asma>() {
            @Override
            public void onSuccess(Asma model) {
                mvpView.getDataSuccess(model);
            }

            @Override
            public void onFailure(String Message) {
                mvpView.getDataFail(Message);
            }

            @Override
            public void onFinish() {
                mvpView.hideLoading();
            }
        });
    }

    void getItemAsma(DataItem item, Activity activity){
//        Intent intent = new Intent(activity, DetailActivity.class);
//        intent.putExtra("number", item.getNumber());
//        mvpView.moveToDetail(intent);
    }
}
