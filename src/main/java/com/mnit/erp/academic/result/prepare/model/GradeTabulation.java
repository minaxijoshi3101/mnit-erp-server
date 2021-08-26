package com.mnit.erp.academic.result.prepare.model;

import com.mnit.erp.academic.exam.model.Exam;
import com.mnit.erp.academic.semester.model.Semester;
import com.mnit.erp.academic.student.st.model.Student;
import com.vladmihalcea.hibernate.type.json.JsonStringType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;

import javax.persistence.*;
import java.util.Date;
import java.util.List;
import java.util.Objects;

/**
 * Contains Grade tabulation model and its dependencies
 *
 * @author: Tejpal Singh
 * @return: Object of grade tabulation model
 * @date: 06 July, 2021
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(uniqueConstraints={
        @UniqueConstraint(columnNames={"exam_id", "semester_id","student_id"})
})
@TypeDef(name = "json", typeClass = JsonStringType.class)
public class GradeTabulation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    
    @Type(type = "json")
    //@Column(columnDefinition = "json")
    @Transient
    List<CourseObject> courseObjectList;
    
    Long preparedById;
    Long semCreditsRegistered;
    Long semCreditsEarned;
    Long semGradePoints;
    Long prevCreditsRegistered;
    Long prevCreditsEarned;
    Long prevGradePoints;
    Long totalCreditsRegistered;
    Long totalCreditsEarned;
    Long totalGradePoints;
    Double sgpa;
    Double cgpa;
    String remarks;
    Date addedOn;
    Date updatedOn;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="student_id", nullable = false, referencedColumnName = "id", insertable =  true, updatable = true)
    private Student student;

    @Transient
    Long studentId;
    public void setStudentId(Long studentId){
        this.student = Objects.nonNull(studentId) ? new Student(studentId) : null;
    }
    public Long getStudentId(){
        return Objects.nonNull(this.student) ? this.student.getId() : null;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="exam_id", nullable = false, referencedColumnName = "id", insertable =  true, updatable = true)
    private Exam exam;

    @Transient
    Long examId;
    public void setExamId(Long examId){
        this.exam = Objects.nonNull(examId) ? new Exam(examId) : null;
    }
    public Long getExamId(){
        return Objects.nonNull(this.exam) ? this.exam.getId() : null;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="semester_id", nullable = false, referencedColumnName = "id", insertable =  true, updatable = true)
    private Semester semester;

    @Transient
    Long semesterId;
    public void setSemesterId(Long semesterId){
        this.semester = Objects.nonNull(semesterId) ? new Semester(semesterId) : null;
    }
    public Long getSemesterId(){
        return Objects.nonNull(this.semester) ? this.semester.getId() : null;
    }

}
