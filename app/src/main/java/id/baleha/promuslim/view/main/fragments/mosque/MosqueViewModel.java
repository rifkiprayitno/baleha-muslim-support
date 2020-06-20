package id.baleha.promuslim.view.main.fragments.mosque;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class MosqueViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public MosqueViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is home fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}