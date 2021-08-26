package com.mnit.erp.academic.result.prepare.model;

import com.mnit.erp.academic.exam.model.ExamType;
import lombok.Data;

@Data
public class CourseObject {
    String courseName;
    String courseCode;
    Long credits;
    String grade;
    Long gradePoints;
    ExamType examType;
}
