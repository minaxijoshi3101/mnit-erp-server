package com.mnit.erp.scholarship.model;


import java.util.Objects;


import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.mnit.erp.academic.program.model.Program;
import com.mnit.erp.util.EntityIdResolver;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id", resolver = EntityIdResolver.class, scope=ScholarshipMaster.class)
public class ScholarshipMaster {
	
	 @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	 Long id;
	 
	 
	 private String scholarshipName;
	 private String normsDoc;
	 private String period;
	 private boolean active;
	 private String state;
	 private String fundingAgency;
	
	 public ScholarshipMaster(Long scholarshipMasterId) {
	        this.id = scholarshipMasterId;
	    }
	 
	 @ManyToOne(fetch = FetchType.LAZY)
	 @JoinColumn(name = "scholarshipType_id", foreignKey = @ForeignKey(name="scholarshipType_id_fk"))
	 private ScholarshipType scholarshipType;
	 
	 @Transient
	 Long scholarshipTypeId;
	 
	 public void setScholarshipTypeId(Long scholarshipTypeId){
	        this.scholarshipType = Objects.nonNull(scholarshipTypeId) ? new ScholarshipType(scholarshipTypeId) : null;
	    }
	 
	 public Long getScholarshipTypeId(){
	        return Objects.nonNull(this.scholarshipType) ? this.scholarshipType.getId() : null;
	    }

	 @ManyToOne(fetch =FetchType.LAZY)
	 @JoinColumn(nullable = false)
	 private Program program;
	 
	 @Transient
	 Long programId;
	 
	 public void setProgramId(Long programId){
	        this.program = Objects.nonNull(programId) ? new Program(programId) : null;
	    }
	 
	 public Long getProgramId(){
	        return Objects.nonNull(this.program) ? this.program.getId() : null;
	    }
	
	    
}
