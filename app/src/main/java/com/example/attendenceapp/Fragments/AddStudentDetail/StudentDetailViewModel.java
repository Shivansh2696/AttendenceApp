package com.example.attendenceapp.Fragments.AddStudentDetail;

import androidx.lifecycle.ViewModel;

import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;

public class StudentDetailViewModel extends ViewModel {
    public Query getStudentList(String batchID){
        return FirebaseFirestore.getInstance().collection("StudentList").whereEqualTo("batchID",batchID);
    }
}
