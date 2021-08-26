package com.mnit.erp.academic.exam.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.mnit.erp.academic.branch.model.Branch;
import com.mnit.erp.academic.degree.model.Degree;
import com.mnit.erp.academic.program.model.Program;
import com.mnit.erp.academic.semester.model.Semester;
import com.mnit.erp.department.model.Department;
import com.mnit.erp.util.Month;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;

/**
 * Contains Exam model and its dependencies
 *
 * @author: Tejpal Singh
 * @return: Object of exam model
 * @date: 09 June, 2021
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Exam {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    @Column(nullable = false, unique = true)
    String name;
    Month month;
    @Column(nullable = false)
    String academicYear;
    @Column(nullable = false)
    ExamType examType;
    @Column(nullable = false)
    Boolean status;
    Date addedOn;
    Date updatedOn;
    @Column(nullable = false)
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd")
    Date formStartDate;
    @Column(nullable = false)
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd")
    Date formLastDate;
    @Column(nullable = false)
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd")
    Date lateFeeFormLastDate;
    public Exam(Long examId) {
        this.id = examId;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="program_id", nullable = false, referencedColumnName = "id", insertable =  true, updatable = true)    
    private Program program;

    @Transient
    Long programId;
    public void setProgramId(Long programId){
        this.program = Objects.nonNull(programId) ? new Program(programId) : null;
    }
    public Long getProgramId(){
        return Objects.nonNull(this.program) ? this.program.getId() : null;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="degree_id", nullable = false, referencedColumnName = "id", insertable =  true, updatable = true)
    private Degree degree;

    @Transient
    Long degreeId;

    public Long getDegreeId() {
        return Objects.nonNull(this.degree) ? this.degree.getId() : null;
    }

    public void setDegreeId(Long degreeId) {
        this.degree = Objects.nonNull(degreeId) ? new Degree(degreeId) : null;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="department_id", nullable = false, referencedColumnName = "id", insertable =  true, updatable = true)
    private Department department;

    @Transient
    Long departmentId;

    public Long getDepartmentId() {
        return Objects.nonNull(this.department) ? this.department.getId() : null;
    }

    public void setDepartmentId(Long departmentId) {
        this.department = Objects.nonNull(departmentId) ? new Department(departmentId) : null;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="branch_id", nullable = false, referencedColumnName = "id", insertable =  true, updatable = true)
    private Branch branch;

    @Transient
    Long branchId;

    public Long getBranchId() {
        return Objects.nonNull(this.branch) ? this.branch.getId() : null;
    }

    public void setBranchId(Long branchId) {
        this.branch = Objects.nonNull(branchId) ? new Branch(branchId) : null;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="semester_id", nullable = false, referencedColumnName = "id", insertable =  true, updatable = true)
    private Semester semester;

    @Transient
    Long semesterId;

    public Long getSemesterId() {
        return Objects.nonNull(this.semester) ? this.semester.getId() : null;
    }

    public void setSemesterId(Long semesterId) {
        this.semester = Objects.nonNull(semesterId) ? new Semester(semesterId) : null;
    }

}
