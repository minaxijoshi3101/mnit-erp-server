package com.mnit.erp.academic.student.st.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;

import org.hibernate.annotations.CreationTimestamp;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.mnit.erp.academic.preadmission.preadmissiondata.model.PreAdmissionData;
import com.mnit.erp.document.model.Document;
import com.mnit.erp.user.model.User;
import com.mnit.erp.util.EntityIdResolver;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id", resolver = EntityIdResolver.class, scope=StudentDocument.class)
public class StudentDocument {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
	@JsonIdentityReference(alwaysAsId = true)
    private Student student;

    @ManyToOne
	@JsonIdentityReference(alwaysAsId = true)
    private PreAdmissionData preAdmissionData;
    
    @ManyToOne
	@JsonIdentityReference(alwaysAsId = true)
    private Document document;
    
    @CreationTimestamp
    private Date uploadedOn;

    private String filePath;
    private String fileType;
    private Boolean agreement;
    private Boolean submitLater; 
    private Date submissionDate;
    
	@ManyToOne
	@JsonIdentityReference(alwaysAsId = true)
	private User authOfficer;
    private String docStatus;
    private String remarks;

    @Transient
    private MultipartFile file;
    
	@Transient
    private String base64File;
	
    @Transient
	private String studentName;
	public String getStudentName() {
		studentName = student != null ? student.getName() : null;
		if(studentName == null) studentName = preAdmissionData != null ? preAdmissionData.getName() : null;
		return studentName;
	}
    
    @Transient
	private String documentName;
	public String getDocumentName() {
		return document != null ? document.getName() : null;
	}
	
    @Transient
	private String authOfficerName;
	public String getAuthOfficerName() {
		return authOfficer != null ? authOfficer.getUsername() : null;
	}


}
