package com.example.attendenceapp.Activities.AddBatch;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.attendenceapp.FirebaseDB.FirebaseDB;
import com.example.attendenceapp.pojo.BatchPOJO;

public class AddBatchViewModel extends ViewModel {

    MutableLiveData<Boolean> completeLiveData;
    private BatchPOJO batch;
    public AddBatchViewModel(){
            completeLiveData = new MutableLiveData<>();
    }

    public void setBatch(BatchPOJO batch) {
        FirebaseDB.getInstance().getBatchDB().add(batch,completeLiveData);
    }

    public LiveData<Boolean> getCompleteLiveData() {
        return completeLiveData;
    }
}
