package id.baleha.promuslim.view.main.fragments.salat;

import id.baleha.promuslim.base.BaseView;
import id.baleha.promuslim.model.praytime.PrayTimeResponse;

public interface SalatView extends BaseView {


    void getDataSuccess(PrayTimeResponse model);

    void refreshData();
}
