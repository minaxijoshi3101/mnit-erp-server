package com.mnit.erp.academic.degree.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.mnit.erp.academic.program.model.Program;
import com.mnit.erp.department.model.Department;
import com.mnit.erp.util.EntityIdResolver;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id", resolver = EntityIdResolver.class, scope=Degree.class)
public class Degree {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    private String abbreviation;
    private String name;

    @ManyToOne
    @JoinColumn(name = "program_id", foreignKey = @ForeignKey(name="program_id_fk"))
    private Program program;

	@ManyToMany
    private List<Department> departments;
    
    public Degree(Long degreeId) {
        this.id = degreeId;
    }

    @Transient
    Long programId;
    public void setProgramId(Long programId){
        this.program = Objects.nonNull(programId) ? new Program(programId) : null;
    }

    public Long getProgramId(){
        return Objects.nonNull(this.program) ? this.program.getId() : null;
    }

}
