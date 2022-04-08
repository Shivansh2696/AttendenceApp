package com.example.attendenceapp.Activities.AddStudent;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.attendenceapp.FirebaseDB.FirebaseDB;
import com.example.attendenceapp.Utils;
import com.example.attendenceapp.model.StudentModel;
import com.google.firebase.auth.FirebaseAuth;

import java.util.Date;

public class AddStudentViewModel extends ViewModel {
    MutableLiveData<Boolean> completeLiveData;
    private StudentModel student;
    public AddStudentViewModel() {
     completeLiveData = new MutableLiveData<>();
    }

    public void setStudent(StudentModel student,String batchID) {
        long currentTime = new Date().getTime();
        String key = Utils.uuid(student.getStudentName() + currentTime);
        student.setDate(currentTime);
        student.setBatchID(batchID);
        student.setStudentId(key);
        student.setTeacherID(FirebaseAuth.getInstance().getUid());
        FirebaseDB.getInstance().getStudentDB().add(key,student,completeLiveData);
    }

    public MutableLiveData<Boolean> getCompleteLiveData() {
        return completeLiveData;
    }
}
