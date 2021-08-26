package com.mnit.erp.academic.student.studentCategory.model;

import com.mnit.erp.academic.student.studentSubType.model.StudentSubType;
import com.mnit.erp.academic.student.studentType.model.StudentType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Objects;

/**
 * Contains student category model and its dependencies
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
        @UniqueConstraint(columnNames={"name", "student_type_id", "student_sub_type_id"})
})
public class StudentCategory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String name;
    @Column(nullable = false)
    Boolean status;

    public StudentCategory(Long studentCategoryId) {
        this.id = studentCategoryId;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="student_type_id", nullable = false, referencedColumnName = "id", insertable =  true, updatable = true)
    private StudentType studentType;

    @Transient
    Long studentTypeId;

    public Long getStudentTypeId() {
        return Objects.nonNull(this.studentType) ? this.studentType.getId() : null;
    }

    public void setStudentTypeId(Long studentTypeId) {
        this.studentType = Objects.nonNull(studentTypeId) ? new StudentType(studentTypeId) : null;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="student_sub_type_id", nullable = false, referencedColumnName = "id", insertable =  true, updatable = true)
    private StudentSubType studentSubType;

    @Transient
    Long studentSubTypeId;

    public Long getStudentSubTypeId() {
        return Objects.nonNull(this.studentSubType) ? this.studentSubType.getId() : null;
    }

    public void setStudentSubTypeId(Long studentSubTypeId) {
        this.studentSubType = Objects.nonNull(studentSubTypeId) ? new StudentSubType(studentSubTypeId) : null;
    }
}
