package com.example.attendenceapp.model;

import java.io.Serializable;

import lombok.Data;

@Data
public class AttendenceModel implements Serializable {
    private String studentId,teacherId;
    private boolean present;
    private long attendanceDate;
}
