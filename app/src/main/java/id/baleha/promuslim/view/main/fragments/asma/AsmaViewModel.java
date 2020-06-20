package id.baleha.promuslim.view.main.fragments.asma;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class AsmaViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public AsmaViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is notifications fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}