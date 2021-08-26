package com.mnit.erp.academic.student.st.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;

import org.hibernate.annotations.CreationTimestamp;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.mnit.erp.user.model.User;
import com.mnit.erp.util.EntityIdResolver;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id", resolver = EntityIdResolver.class, scope=StudentDocumentStatus.class)
public class StudentDocumentStatus {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
	@JsonIdentityReference(alwaysAsId = true)
    private StudentDocument studentDocument;
    @CreationTimestamp
	private Date createdOn;
	@ManyToOne
	@JsonIdentityReference(alwaysAsId = true)
	private User authOfficer;
    private String docStatus;
    private String remarks;
        
    @Transient
	private String authOffierName;
	public String getAuthOffierName() {
		return authOfficer != null ? authOfficer.getUsername() : null;
	}

}
