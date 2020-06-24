package id.baleha.promuslim.view.main.fragments.salat;

import id.baleha.promuslim.base.BasePresenter;
import id.baleha.promuslim.model.praytime.PrayTimeResponse;
import id.baleha.promuslim.model.praytime.Timings;
import id.baleha.promuslim.service.NetworkCallback;

public class SalatPresenter extends BasePresenter<SalatView> {

    SalatPresenter(SalatView view) {
        super.attachView(view);
    }

    void loadData(String timestamp) {
        mvpView.showLoading();
        addSubscribe(apiStores.getPrayTime(timestamp, "-6.914744", "107.609810", 11), new NetworkCallback<PrayTimeResponse>() {
            @Override
            public void onSuccess(PrayTimeResponse model) {
                mvpView.getDataSuccess(model);
            }

            @Override
            public void onFailure(String Message) {
                mvpView.getDatFail(Message);
            }

            @Override
            public void onFinish() {
                mvpView.hideLoading();
            }
        });
    }
}
