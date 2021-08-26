package com.mnit.erp.academic.branch.model;

import com.mnit.erp.academic.degree.model.Degree;
import com.mnit.erp.department.model.Department;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Branch {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    String abbreviation;
    String name;
    String description;
    Boolean active;
    String code; // CODE TO BE USED IN STUDENT-ID GENERATION

    @OneToOne
    Department department;

    @ManyToMany
    List<Degree> degrees;

    @Transient
    Long departmentId;

    public Branch(Long branchId) {
        this.id = branchId;
    }

    public void setDepartmentId(Long departmentId){
        this.department = Objects.nonNull(departmentId) ? new Department(departmentId) : null;
    }

    public Long getDepartmentId(){ return Objects.nonNull(this.department) ? this.department.getId() : null; }

    @Transient
    List<Long> degreeIds;

    public List<Long> getDegreeIds() {
        return Objects.isNull(this.degrees) ? null : this.degrees.stream().map(Degree::getId).collect(Collectors.toList());
    }

    public void setDegreeIds(List<Long> degreeIds) {
        this.degrees = Objects.nonNull(degreeIds) ? degreeIds.stream().map(Degree::new).collect(Collectors.toList()) : null;
    }

}
