package com.example.attendenceapp.FirebaseDB;

import com.example.attendenceapp.pojo.BatchPOJO;
import com.example.attendenceapp.pojo.StudentPOJO;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class FirebaseDB{

    private DatabaseReference databaseReference;
    private FirebaseCommonDB<BatchPOJO> batchDB;
    private FirebaseCommonDB<StudentPOJO> studentDB;
    private static FirebaseDB instance;

    public static FirebaseDB getInstance() {
        if(instance == null)
            instance = new FirebaseDB();
        return instance;
    }

    private FirebaseDB(){
        databaseReference = FirebaseDatabase.getInstance().getReference().child("batch");
        batchDB = new FirebaseCommonDB(databaseReference, BatchPOJO.class);

        databaseReference = FirebaseDatabase.getInstance().getReference().child("batch").child("Student");
        studentDB = new FirebaseCommonDB(databaseReference, StudentPOJO.class);
    }

    public FirebaseCommonDB<BatchPOJO> getBatchDB() {
        return batchDB;
    }

    public FirebaseCommonDB<StudentPOJO> getStudentDB() {
        return studentDB;
    }
}
