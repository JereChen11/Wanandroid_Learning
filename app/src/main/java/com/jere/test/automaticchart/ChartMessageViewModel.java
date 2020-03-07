package com.jere.test.automaticchart;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

/**
 * @author jere
 */
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
