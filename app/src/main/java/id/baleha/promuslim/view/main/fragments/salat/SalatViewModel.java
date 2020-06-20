package id.baleha.promuslim.view.main.fragments.salat;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class SalatViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public SalatViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is dashboard fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}