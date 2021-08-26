package com.mnit.erp.academic.exam.calendar.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.mnit.erp.academic.course.model.Course;
import com.mnit.erp.academic.exam.model.Exam;
import com.mnit.erp.academic.section.model.Section;
import com.mnit.erp.academic.student.st.model.Student;
import com.mnit.erp.room.model.Room;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * Contains Exam calendar model and its dependencies
 *
 * @author: Tejpal Singh
 * @return: Object of exam model
 * @date: 22 June, 2021
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class ExamCalendar {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(nullable = false)
    Integer numberOfStudent;
    @ManyToMany
    List<Student> students;

    public List<Long> getStudentIds() {
        return Objects.isNull(this.students) ? null : this.students.stream().map(Student::getId).collect(Collectors.toList());
    }

    public void setStudentIds(List<Long> studentIds) {
        this.students = Objects.nonNull(studentIds) ? studentIds.stream().map(Student::new).collect(Collectors.toList()) : null;
    }

    @Transient
    List<Long> studentIds;

    @Column(nullable = false)
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd")
    Date dateOfExam;

    @Column(nullable = false)
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="HH:mm")
    Date startTime;

    @Column(nullable = false)
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="HH:mm")
    Date endTime;

    public ExamCalendar(Long examId) {
        this.id = examId;
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
    @JoinColumn(name="section_id", nullable = false, referencedColumnName = "id", insertable =  true, updatable = true)
    private Section section;

    @Transient
    Long sectionId;

    public Long getSectionId() {
        return Objects.nonNull(this.section) ? this.section.getId() : null;
    }

    public void setSectionId(Long sectionId) {
        this.sectionId = sectionId;
        this.section = Objects.nonNull(sectionId) ? new Section(sectionId) : null;
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

}
