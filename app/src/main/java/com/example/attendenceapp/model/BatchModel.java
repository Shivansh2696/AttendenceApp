package com.example.attendenceapp.model;

import java.io.Serializable;

import lombok.Data;

@Data
public class BatchModel implements Serializable {

    private String teacherName,classTime,batchName,Image,key,userID;
    private long dateOn;
}
