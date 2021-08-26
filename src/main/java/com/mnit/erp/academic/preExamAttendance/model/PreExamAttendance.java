package com.mnit.erp.academic.preExamAttendance.model;

import com.mnit.erp.academic.course.model.Course;
import com.mnit.erp.academic.exam.model.Exam;
import com.mnit.erp.academic.section.model.Section;
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
 * Contains Exam model and its dependencies
 *
 * @author: Tejpal Singh
 * @return: Object of pre exam attendance model
 * @date: 10 June, 2021
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(uniqueConstraints={
        @UniqueConstraint(columnNames={"exam_id", "course_id"})
})
@TypeDef(name = "json", typeClass = JsonStringType.class)
public class PreExamAttendance {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    Date addedOn;
    Date updatedOn;
    @Type(type = "json")
    @Column(columnDefinition = "json")
    List<Student> students;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="exam_id", nullable = false, referencedColumnName = "id", insertable =  true, updatable = true)
    Exam exam;

    @Transient
    Long examId;
    public Long getExamId() {
        return Objects.nonNull(this.exam) ? this.exam.getId() : null;
    }
    public void setExamId(Long examId) {
        this.exam = Objects.nonNull(examId) ? new Exam(examId) : null;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="course_id", nullable = false, referencedColumnName = "id", insertable =  true, updatable = true)
    Course course;

    @Transient
    Long courseId;

    public Long getCourseId() {
        return Objects.nonNull(this.course)? this.course.getId() : null;
    }

    public void setCourseId(Long courseId) {
        this.course = Objects.nonNull(courseId)? new Course(courseId) : null;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="section_id", nullable = false, referencedColumnName = "id", insertable =  true, updatable = true)
    Section section;

    @Transient
    Long sectionId;

    public Long getSectionId() {
        return Objects.nonNull(this.section)? this.section.getId() : null;
    }

    public void setSectionId(Long sectionId) {
        this.section = Objects.nonNull(sectionId)? new Section(sectionId) : null;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="added_by_id", nullable = false, referencedColumnName = "id", insertable =  true, updatable = true)
    User addedBy;

    @Transient
    Long addedById;
    public Long getAddedById() {
        return Objects.nonNull(this.addedBy) ? this.addedBy.getId() : null;
    }

    public void setAddedById(Long addedById) {
        this.addedBy = Objects.nonNull(addedById) ? new User(addedById) : null;
    }

}
