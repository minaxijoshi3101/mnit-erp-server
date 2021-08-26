package com.mnit.erp.academic.student.studentSubType.model;

import com.mnit.erp.academic.student.studentType.model.StudentType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Objects;

/**
 * Contains student sub type model and its dependencies
 *
 * @author: Tejpal Singh
 * @return: Object of student type model
 * @date: 10 July, 2021
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(uniqueConstraints={
        @UniqueConstraint(columnNames={"name", "student_type_id"})
})
public class StudentSubType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String name;
    @Column(nullable = false)
    Boolean status;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="student_type_id", nullable = false, referencedColumnName = "id", insertable =  true, updatable = true)
    private StudentType studentType;

    @Transient
    Long studentTypeId;

    public StudentSubType(Long studentTypeId) {
        this.id = studentTypeId;
    }

    public StudentSubType(Long id, String name, Boolean status, Long studentTypeId) {
        this.setId(id);
        this.setName(name);
        this.setStatus(status);
        this.setStudentTypeId(studentTypeId);
    }

    public Long getStudentTypeId() {
        return Objects.nonNull(this.studentType) ? this.studentType.getId() : null;
    }

    public void setStudentTypeId(Long studentTypeId) {
        this.studentType = Objects.nonNull(studentTypeId) ? new StudentType(studentTypeId) : null;
    }
}
