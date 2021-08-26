package com.mnit.erp.academic.student.studentTypeChangeRequest.model;

import com.mnit.erp.academic.student.st.model.Student;
import com.mnit.erp.academic.student.studentCategory.model.StudentCategory;
import com.mnit.erp.academic.student.studentSubType.model.StudentSubType;
import com.mnit.erp.academic.student.studentType.model.StudentType;
import com.mnit.erp.user.model.User;
import com.mnit.erp.audit.UserDateAudit;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Objects;

/**
 * Contains student type change request model and its dependencies
 *
 * @author: Tejpal Singh
 * @return: Object of student type change request model
 * @date: 22 July, 2021
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class StudentTypeChangeRequest extends UserDateAudit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    RequestApproveStatus status;
    String reasonText;

    public StudentTypeChangeRequest(Long studentTypeChangeRequestId) {
        this.id = studentTypeChangeRequestId;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="student_id", nullable = false, referencedColumnName = "id", insertable =  true, updatable = true)
    private Student student;

    @Transient
    Long studentId;

    public Long getStudentId() {
        return Objects.nonNull(this.student) ? this.student.getId() : null;
    }
    public void setStudentId(Long studentId) {
        this.student = Objects.nonNull(studentId) ? new Student(studentId) : null;
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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="student_category_id", nullable = false, referencedColumnName = "id", insertable =  true, updatable = true)
    private StudentCategory studentCategory;

    @Transient
    Long studentCategoryId;

    public Long getStudentCategoryId() {
        return Objects.nonNull(this.studentCategory) ? this.studentCategory.getId() : null;
    }
    public void setStudentCategoryId(Long studentCategoryId) {
        this.studentCategory = Objects.nonNull(studentCategoryId) ? new StudentCategory(studentCategoryId) : null;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="old_student_type_id", nullable = false, referencedColumnName = "id", insertable =  true, updatable = true)
    private StudentType oldStudentType;

    @Transient
    Long oldStudentTypeId;

    public Long getOldStudentTypeId() {
        return Objects.nonNull(this.oldStudentType) ? this.oldStudentType.getId() : null;
    }
    public void setOldStudentTypeId(Long oldStudentTypeId) {
        this.oldStudentType = Objects.nonNull(oldStudentTypeId) ? new StudentType(oldStudentTypeId) : null;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="old_student_sub_type_id", nullable = false, referencedColumnName = "id", insertable =  true, updatable = true)
    private StudentSubType oldStudentSubType;

    @Transient
    Long oldStudentSubTypeId;

    public Long getOldStudentSubTypeId() {
        return Objects.nonNull(this.oldStudentSubType) ? this.oldStudentSubType.getId() : null;
    }
    public void setOldStudentSubTypeId(Long oldStudentSubTypeId) {
        this.oldStudentSubType = Objects.nonNull(oldStudentSubTypeId) ? new StudentSubType(oldStudentSubTypeId) : null;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="old_student_category_id", nullable = false, referencedColumnName = "id", insertable =  true, updatable = true)
    private StudentCategory oldStudentCategory;

    @Transient
    Long oldStudentCategoryId;

    public Long getOldStudentCategoryId() {
        return Objects.nonNull(this.oldStudentCategory) ? this.oldStudentCategory.getId() : null;
    }
    public void setOldStudentCategoryId(Long oldStudentCategoryId) {
        this.oldStudentCategory = Objects.nonNull(oldStudentCategoryId) ? new StudentCategory(oldStudentCategoryId) : null;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="approved_by_id", referencedColumnName = "id", insertable =  true, updatable = true)
    private User approvedBy;

    @Transient
    Long approvedById;

    public Long getApprovedById() {
        return Objects.nonNull(this.approvedBy) ? this.approvedBy.getId() : null;
    }
    public void setApprovedById(Long approvedById) {
        this.approvedById = approvedById;
        this.approvedBy = Objects.nonNull(approvedById) ? new User(approvedById) : null;
    }

}
