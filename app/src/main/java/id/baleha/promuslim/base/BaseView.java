package id.baleha.promuslim.base;

public interface BaseView {

    void showLoading();

    void hideLoading();

    void getDataSuccess(Object model);

    void getDatFail(String message);

    void refreshdata();
}
