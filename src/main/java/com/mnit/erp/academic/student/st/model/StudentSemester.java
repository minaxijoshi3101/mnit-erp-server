package com.mnit.erp.academic.student.st.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.CreationTimestamp;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.mnit.erp.academic.semester.model.Semester;
import com.mnit.erp.util.EntityIdResolver;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id", resolver = EntityIdResolver.class, scope=StudentSemester.class)
public class StudentSemester implements Serializable{
	private static final long serialVersionUID = 1L;

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

	@ManyToOne(targetEntity=Student.class)
	@JoinColumn(name="studentId",referencedColumnName="id",nullable=false,updatable=true)
	private Student student = null;

	@CreationTimestamp
	private Date createdOn;

    @ManyToOne
    @JsonIdentityReference(alwaysAsId = true)
    private Semester semester;
    
    private String status;
}
