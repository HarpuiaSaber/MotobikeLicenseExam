package com.group0201.motobikelicenseexam.ui.personel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class PersonelViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public PersonelViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is personel fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}