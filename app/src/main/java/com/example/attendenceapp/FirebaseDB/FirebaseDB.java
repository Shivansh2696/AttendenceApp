package com.example.attendenceapp.FirebaseDB;

import com.example.attendenceapp.model.BatchModel;
import com.example.attendenceapp.model.StudentModel;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;

public class FirebaseDB{

    private CollectionReference databaseReference;
    private FirebaseCommonDB<BatchModel> batchDB;
    private FirebaseCommonDB<StudentModel> studentDB;
     String batchName;
    private static FirebaseDB instance;

    public static FirebaseDB getInstance() {
        if(instance == null)
            instance = new FirebaseDB();
        return instance;
    }

    private FirebaseDB(){
        databaseReference = FirebaseFirestore.getInstance().collection("BatchList");
        batchDB = new FirebaseCommonDB(BatchModel.class,databaseReference);

//        databaseReference = FirebaseDatabase.getInstance().getReference().child("BatchList").push().child("Student");
//        studentDB = new FirebaseCommonDB(databaseReference, StudentPOJO.class);
    }

    public FirebaseCommonDB<BatchModel> getBatchDB() {
        return batchDB;
    }

    public FirebaseCommonDB<StudentModel> getStudentDB() {
        return studentDB;
    }


}
