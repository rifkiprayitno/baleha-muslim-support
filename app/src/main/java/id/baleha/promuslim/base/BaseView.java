package id.baleha.promuslim.base;

public interface BaseView {

    void showLoading();

    void hideLoading();

    void getDataSuccess(Object model);

    void getDataFail(String message);

    void refreshdata();
}
