package com.example.attendenceapp.MainActivity;

import androidx.lifecycle.ViewModel;

import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

public class MainViewModel extends ViewModel {

    public Task<QuerySnapshot> getBatch(){
        return FirebaseFirestore.getInstance().collection("BatchList").whereEqualTo("userID",
                FirebaseAuth.getInstance().getUid()).get();
    }
}
