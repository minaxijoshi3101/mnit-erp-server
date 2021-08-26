package com.mnit.erp.academic.exam.gradeMark.model;

import com.mnit.erp.academic.course.model.Course;
import com.mnit.erp.academic.exam.model.Exam;
import com.mnit.erp.academic.student.st.model.Student;
import com.mnit.erp.user.model.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;

/**
 * Contains Grade Mark model and its dependencies
 *
 * @author: Tejpal Singh
 * @return: Object of grade mark model
 * @date: 29 June, 2021
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(uniqueConstraints={
        @UniqueConstraint(columnNames={"exam_id", "course_id","student_id"})
})
public class GradeMark {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    @Column(nullable = false)
    String grade;
    @Column(nullable = false)
    Long gradePoints;
    GradeMarkStatus status;
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
    @JoinColumn(name="course_id", nullable = false, referencedColumnName = "id", insertable =  true, updatable = true)
    private Course course;

    @Transient
    Long courseId;
    public void setCourseId(Long courseId){
        this.course = Objects.nonNull(courseId) ? new Course(courseId) : null;
    }
    public Long getCourseId(){
        return Objects.nonNull(this.course) ? this.course.getId() : null;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="marked_by_id", nullable = false, referencedColumnName = "id", insertable =  true, updatable = true)
    private User markedBy;

    @Transient
    Long markedById;
    public void setMarkedById(Long markedById){
        this.markedBy = Objects.nonNull(markedById) ? new User(markedById) : null;
    }
    public Long setMarkedById(){
        return Objects.nonNull(this.markedBy) ? this.markedBy.getId() : null;
    }
}
