package com.mnit.erp.academic.exam.attendance.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.mnit.erp.academic.course.model.Course;
import com.mnit.erp.academic.exam.model.Exam;
import com.mnit.erp.room.model.Room;
import com.mnit.erp.user.model.User;
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
 * Contains Exam attendance model and its dependencies
 *
 * @author: Tejpal Singh
 * @return: Object of exam model
 * @date: 24 June, 2021
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(uniqueConstraints={
        @UniqueConstraint(columnNames={"exam_id", "course_id","room_id"})
})
@TypeDef(name = "json", typeClass = JsonStringType.class)
public class ExamAttendance {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    Date addedOn;
    Date updatedOn;
    @Type(type = "json")
    @Column(columnDefinition = "json")
    List<Student> students;

    @Column(nullable = false)
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd")
    Date markingDate;

    public ExamAttendance(Long examAttendanceId) {
        this.id = examAttendanceId;
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
    @JoinColumn(name="room_id", nullable = false, referencedColumnName = "id", insertable =  true, updatable = true)
    private Room room;

    @Transient
    Long roomId;

    public Long getRoomId() {
        return Objects.nonNull(this.room) ? this.room.getId() : null;
    }

    public void setRoomId(Long roomId) {
        this.room = Objects.nonNull(roomId) ? new Room(roomId) : null;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="marked_by_id", nullable = false, referencedColumnName = "id", insertable =  true, updatable = true)
    private User markedBy;

    @Transient
    Long markedById;

    public Long getMarkedById() {
        return Objects.nonNull(this.markedBy) ? this.markedBy.getId() : null;
    }

    public void setMarkedById(Long markedById) {
        this.markedById = markedById;
        this.markedBy = Objects.nonNull(markedById) ? new User(markedById) : null;
    }
}
