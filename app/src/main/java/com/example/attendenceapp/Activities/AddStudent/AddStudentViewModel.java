package com.example.attendenceapp.Activities.AddStudent;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.attendenceapp.FirebaseDB.FirebaseDB;
import com.example.attendenceapp.pojo.StudentPOJO;

public class AddStudentViewModel extends ViewModel {
    MutableLiveData<Boolean> completeLiveData;
    private StudentPOJO student;
    public AddStudentViewModel() {
     completeLiveData = new MutableLiveData<>();
    }

    public void setStudent(StudentPOJO student) {
        FirebaseDB.getInstance().getStudentDB().add(student,completeLiveData);
    }

    public MutableLiveData<Boolean> getCompleteLiveData() {
        return completeLiveData;
    }
}
