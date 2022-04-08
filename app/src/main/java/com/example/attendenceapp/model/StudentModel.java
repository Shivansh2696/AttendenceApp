package com.example.attendenceapp.model;

import java.io.Serializable;

import lombok.Data;

@Data
public class StudentModel implements Serializable {
    private String studentName,studentPhone,studentAddress,studentId,studentImage,teacherID;
    private Long date;
}
