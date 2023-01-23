package com.ualr.recyclerviewassignment.model;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

public class SharedDataModel extends ViewModel
{
    private final MutableLiveData<List<Inbox>> inbox = new MutableLiveData<>();
    private final MutableLiveData<Integer> index = new MutableLiveData<>(-1);

    public LiveData<List<Inbox>> getInbox() {
        return inbox;
    }

    public LiveData<Integer> getIndex() {
        return index;
    }

    public void setInbox(List<Inbox> inbox) { this.inbox.setValue(inbox); }

    public void setIndex(int index) {
        this.index.setValue(index);
    }
}
