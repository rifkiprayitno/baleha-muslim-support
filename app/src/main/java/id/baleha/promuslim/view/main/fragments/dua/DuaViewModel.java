package id.baleha.promuslim.view.main.fragments.dua;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class DuaViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public DuaViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is notifications fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}