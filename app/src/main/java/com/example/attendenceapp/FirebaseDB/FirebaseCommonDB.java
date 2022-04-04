package com.example.attendenceapp.FirebaseDB;

import androidx.lifecycle.MutableLiveData;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Query;

public class FirebaseCommonDB <T> {
    private T model;
    private final DatabaseReference databaseReference;

    public FirebaseCommonDB(DatabaseReference databaseReference, Class<T> tClass) {
        this.databaseReference = databaseReference;
        this.model = (T) tClass;
    }

    public Task<Void> add(T model,MutableLiveData<Boolean> completeLiveData){
        return databaseReference.push().setValue(model).addOnCompleteListener(task -> {
            if(completeLiveData != null){
                completeLiveData.setValue(true);
            }
        });
    }

    // getting of data
    public Query get(){
        return databaseReference;
    }

    // Delete

    public Task<Void> remove(String key){
        return databaseReference.child(key).removeValue();
    }

//    //Update
//    public Task<Void> update(String key, Map<String, Object> data){
//        return databaseReference.child(key).updateChildren(data);
//    }

    public Task<Void> update(String key, T model){
        return databaseReference.child(key).setValue(model);
    }
}
