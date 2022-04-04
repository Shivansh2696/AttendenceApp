package com.example.attendenceapp.Fragments.AddStudentDetail;

import androidx.lifecycle.ViewModel;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class StudentDetailViewModel extends ViewModel {
    public DatabaseReference getStudentList(){
        return FirebaseDatabase.getInstance().getReference().child("batch").child("Student");
    }
}
