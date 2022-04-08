package com.example.attendenceapp.FirebaseDB;


import androidx.lifecycle.MutableLiveData;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Query;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class FirebaseCommonDB <T> {
    private final Class<T> model;
    private final CollectionReference documentReference;
    DatabaseReference databaseReference;


    public Task<Void> add(String key,T model, MutableLiveData<Boolean> completeLiveData){
        return documentReference.document(key).set(model).addOnCompleteListener(task -> {
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
