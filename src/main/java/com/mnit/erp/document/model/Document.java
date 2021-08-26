package com.mnit.erp.document.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.mnit.erp.academic.program.model.Program;
import com.mnit.erp.util.EntityIdResolver;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id", resolver = EntityIdResolver.class, scope=Document.class)
public class Document {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    String name;
    String description;
    Boolean active;
    
    @ManyToMany
    List<Program> programs;

    @Transient
    List<Long> programIds;

    public Document(Long documentTypeId) {
        this.id = documentTypeId;
    }

    public List<Long> getProgramIds() {
        return Objects.isNull(this.programIds) ? null : this.programs.stream().map(Program::getId).collect(Collectors.toList());
    }

    public void setProgramIds(List<Long> programIds) {
        this.programs = programIds.stream().map(Program::new).collect(Collectors.toList());
    }

}
