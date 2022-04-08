package com.example.attendenceapp.Activities.AddBatch;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.attendenceapp.FirebaseDB.FirebaseDB;
import com.example.attendenceapp.Utils;
import com.example.attendenceapp.model.BatchModel;
import com.google.firebase.auth.FirebaseAuth;

import java.util.Date;
import java.util.UUID;

public class AddBatchViewModel extends ViewModel {

    MutableLiveData<Boolean> completeLiveData;
    public AddBatchViewModel(){
            completeLiveData = new MutableLiveData<>();
    }

    public void setBatch(BatchModel batch) {
        batch.setUserID(FirebaseAuth.getInstance().getUid());
        batch.setDateOn(new Date().getTime());
        String string = batch.getBatchName() + batch.getUserID();
        batch.setKey(Utils.uuid(string));

        FirebaseDB.getInstance().getBatchDB().add(batch.getKey(),batch,completeLiveData);
    }
    public LiveData<Boolean> getCompleteLiveData() {
        return completeLiveData;
    }
}
