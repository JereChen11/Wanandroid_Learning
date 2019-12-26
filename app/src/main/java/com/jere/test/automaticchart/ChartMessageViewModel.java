package com.jere.test.automaticchart;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

public class ChartMessageViewModel extends ViewModel {
    private MutableLiveData<String> messageLd;

    public ChartMessageViewModel() {
        messageLd = new MutableLiveData<>();
    }

    public MutableLiveData<String> getMessageLd() {
        return messageLd;
    }

    public void setMessageLd(String messageContent) {
        this.messageLd.postValue(messageContent);
    }


}
