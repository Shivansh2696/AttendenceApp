package com.example.attendenceapp.MainActivity;

import androidx.lifecycle.ViewModel;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainViewModel extends ViewModel {

    public DatabaseReference getBatch(){
        return FirebaseDatabase.getInstance().getReference().child("batch").getRef();
    }
}
