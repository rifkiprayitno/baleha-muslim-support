package id.baleha.promuslim.view.main.fragments.asma;

import android.content.Intent;

import id.baleha.promuslim.base.BaseView;
import id.baleha.promuslim.model.asma.Asma;

public interface AsmaView extends BaseView {

    void getDataSuccess(Asma asma);

    void moveToDetail(Intent intent);
}
