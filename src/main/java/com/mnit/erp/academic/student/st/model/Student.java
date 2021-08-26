package com.mnit.erp.academic.student.st.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Transient;
import javax.validation.constraints.*;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.mnit.erp.academic.admissionType.model.AdmissionType;
import com.mnit.erp.academic.branch.model.Branch;
import com.mnit.erp.academic.degree.model.Degree;
import com.mnit.erp.academic.preadmission.preadmissiondata.model.PreAdmissionData;
import com.mnit.erp.academic.program.model.Program;
import com.mnit.erp.academic.selectionboard.model.SelectionBoard;
import com.mnit.erp.academic.selectionboard.type.model.SelectionBoardType;
import com.mnit.erp.academic.semester.model.Semester;
import com.mnit.erp.academic.specialization.model.Specialization;
import com.mnit.erp.academic.student.incomeCategory.model.FamilyIncomeCategory;
import com.mnit.erp.academic.student.studentCategory.model.StudentCategory;
import com.mnit.erp.academic.student.studentSubType.model.StudentSubType;
import com.mnit.erp.academic.student.studentType.model.StudentType;
import com.mnit.erp.address.model.Address;
import com.mnit.erp.category.model.Category;
import com.mnit.erp.common.model.BankDetail;
import com.mnit.erp.department.model.Department;
import com.mnit.erp.entranceExam.model.EntranceExam;
import com.mnit.erp.family.model.FamilyMember;
import com.mnit.erp.identificationDocument.model.IdentificationDocument;
import com.mnit.erp.user.model.User;
import com.mnit.erp.util.EntityIdResolver;

import com.mnit.erp.validation.constraints.CurrentYearConstraint;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id", resolver = EntityIdResolver.class, scope=Student.class)
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    //@NotNull(message = "{year.invalid}")
    //@CurrentYearConstraint
    Long admissionYear;

    @Digits(integer = 4, fraction = 0, message = "{sequence.invalid}")
    Long sequence;

    String batch;
    String studentId;

    //PERSONAL DETAILS
    @NotBlank(message = "{name.blank}")
    @Pattern(regexp = "^([A-Za-z]{1,}[\\.]{0,1}\\s{0,1}[A-Za-z]{0,})+$", message = "{name.invalid}")
    String name;

    @NotBlank(message = "{email.invalid}")
    @Email(message = "{email.invalid}")
    String emailPrimary;

    @Email(message = "{email.invalid}")
    String emailSecondary;

    @NotEmpty(message = "{mobile.invalid}")
    @Digits(fraction = 0, integer = 10, message = "{mobile.invalid}")
    String mobile;

    Gender gender;

    @Length(min = 2, max = 3, message = "bloodgroup.invalid")
    String bloodGroup;
    Date dob;
    
    @ManyToOne
	@JsonIdentityReference(alwaysAsId = true)
	private User authOfficer;
    String status;
    String remark;
    String photoPath;
    String photoType;
    
    @ManyToOne
	@JsonIdentityReference(alwaysAsId = true)
    private PreAdmissionData preAdmissionData;

    @ManyToOne
    Category category;
    @Transient Long categoryId;
    Boolean pwd;

    Citizenship citizenShip;

    @OneToMany
    List<Address> addresses;

    @ManyToMany
    List<IdentificationDocument> identificationDocuments;

    @ManyToOne
    FamilyIncomeCategory familyIncomeCategory;
    @Transient Long familyIncomeCategoryId;

    // ADMISSION DETAILS

    @ManyToOne
    Category admittedCategory;
    @Transient Long admittedCategoryId;
    Boolean admittedPwd;

    @ManyToOne
    Program program;
    @Transient Long programId;

    @ManyToOne
    Department department;
    @Transient Long departmentId;

    @ManyToOne
    Degree degree;
    @Transient Long degreeId;

    @ManyToOne
    Branch branch;
    @Transient Long branchId;

    @ManyToOne
    Specialization specialization;
    @Transient Long specializationId;

    @ManyToOne
    AdmissionType admissionType;
    @Transient Long admissionTypeId;

    @ManyToOne
    SelectionBoard selectionBoard;
    @Transient Long selectionBoardId;

    @ManyToOne
    SelectionBoardType selectionBoardType;
    @Transient Long selectionBoardTypeId;

    @ManyToOne
    StudentType studentType;
    @Transient Long studentTypeId;

    @ManyToOne
    StudentSubType studentSubType;
    @Transient Long studentSubTypeId;

    @ManyToOne
    StudentCategory studentCategory;
    @Transient Long studentCategoryId;

    String sponsoredBy;

    @OneToOne
    EntranceExam entranceExam;

    Date admissionDate;
    Date updatedOn;

    // FAMILY DETAILS and Nominee details
    @ManyToMany
    List<FamilyMember> familyMembers;
    
    // Default First Semester should set
    @ManyToOne
    @JsonIdentityReference(alwaysAsId = true)
    Semester semester;

	@OneToMany(cascade = CascadeType.ALL,fetch=FetchType.LAZY ,mappedBy="student")
	private List<StudentSemester> studentSemester = new ArrayList<StudentSemester>(0);

    @ManyToOne
    BankDetail bankDetail;
    @Transient Long bankDetailId;

    @Transient 
    String base64Photo;
    
    @Transient
	private String authOfficerName;
	public String getAuthOfficerName() {
		return authOfficer != null ? authOfficer.getUsername() : null;
	}
	
	public Student(Long id) {
        this.id = id;
    }

    public Long getCategoryId() {
        return Objects.nonNull(this.category) ? this.category.getId() : null;
    }

    public void setCategoryId(Long categoryId) {
        if(Objects.isNull(this.category)){
            this.categoryId = categoryId;
            this.category = new Category(categoryId);
        }
    }

    public Long getAdmittedCategoryId() {
        return Objects.nonNull(this.admittedCategory) ? this.admittedCategory.getId() : null;
    }

    public void setAdmittedCategoryId(Long admittedCategoryId) {
        if(Objects.isNull(this.admittedCategory)){
            this.admittedCategoryId = admittedCategoryId;
            this.admittedCategory = new Category(admittedCategoryId);
        }
    }

    public Long getProgramId() {
        return Objects.nonNull(this.program) ? this.program.getId() : null;
    }

    public void setProgramId(Long programId) {
        if(Objects.isNull(this.program)){
            this.programId = programId;
            this.program = new Program(programId);
        }
    }

    public Long getDepartmentId() {
        return Objects.nonNull(this.department) ? this.department.getId() : departmentId;
    }

    public void setDepartmentId(Long departmentId) {
        if(Objects.isNull(this.department)){
            this.departmentId = departmentId;
            this.department = new Department(departmentId);
        }
    }

    public Long getDegreeId() {
        return Objects.nonNull(this.degree) ? this.degree.getId() : degreeId;
    }

    public void setDegreeId(Long degreeId) {
        if(Objects.isNull(this.degree)){
            this.degreeId = degreeId;
            this.degree = new Degree(degreeId);
        }
    }

    public Long getBranchId() {
        return Objects.nonNull(this.branch) ? this.branch.getId() : branchId;
    }

    public void setBranchId(Long branchId) {
        if(Objects.isNull(this.branch)){
            this.branchId = branchId;
            this.branch = new Branch(branchId);
        }
    }

    public Long getSpecializationId() {
        return Objects.nonNull(this.specialization) ? specialization.getId() : specializationId;
    }

    public void setSpecializationId(Long specializationId) {
        if(Objects.isNull(this.specialization)){
            this.specializationId = specializationId;
            this.specialization = new Specialization(specializationId);
        }
    }

    public Long getAdmissionTypeId() {
        return Objects.nonNull(this.admissionType) ? this.admissionType.getId() : admissionTypeId;
    }

    public void setAdmissionTypeId(Long admissionTypeId) {
        if(Objects.isNull(this.admissionType)){
            this.admissionTypeId = admissionTypeId;
            this.admissionType = new AdmissionType(admissionTypeId);
        }
    }

    public Long getSelectionBoardId() {
        return Objects.nonNull(this.selectionBoard) ? this.selectionBoard.getId() : selectionBoardId;
    }

    public void setSelectionBoardId(Long selectionBoardId) {
        if(Objects.isNull(this.selectionBoard)){
            this.selectionBoardId = selectionBoardId;
            this.selectionBoard = new SelectionBoard(selectionBoardId);
        }
    }

    public Long getSelectionBoardTypeId() {
        return Objects.nonNull(this.selectionBoardType) ? this.selectionBoardType.getId() : selectionBoardTypeId;
    }

    public void setSelectionBoardTypeId(Long selectionBoardTypeId) {
        if(Objects.isNull(this.selectionBoardType) && Objects.nonNull(selectionBoardTypeId)){
            this.selectionBoardTypeId = selectionBoardTypeId;
            this.selectionBoardType = new SelectionBoardType(selectionBoardTypeId);
        }
    }

    public Long getStudentTypeId() {
        return Objects.nonNull(this.studentType) ? this.studentType.getId() : studentTypeId;
    }

    public void setStudentTypeId(Long studentTypeId) {
        if(Objects.isNull(this.studentType) && Objects.nonNull(studentTypeId)){
            this.studentTypeId = studentTypeId;
            this.studentType = new StudentType(studentTypeId);
        }
    }

    public Long getStudentSubTypeId() {
        return Objects.nonNull(this.studentSubType) ? this.studentSubType.getId() : studentSubTypeId;
    }

    public void setStudentSubTypeId(Long studentSubTypeId) {
        if(Objects.isNull(this.studentSubType) && Objects.nonNull(studentSubTypeId)){
            this.studentSubTypeId = studentSubTypeId;
            this.studentSubType = new StudentSubType(studentSubTypeId);
        }
    }

    public Long getStudentCategoryId() {
        return Objects.nonNull(this.studentCategory) ? this.studentCategory.getId() : studentCategoryId;
    }

    public void setStudentCategoryId(Long studentCategoryId) {
        if(Objects.isNull(this.studentCategory) && Objects.nonNull(studentCategoryId)){
            this.studentCategoryId = studentCategoryId;
            this.studentCategory = new StudentCategory(studentCategoryId);
        }
    }

    public Long getFamilyIncomeCategoryId() {
        return Objects.nonNull(this.familyIncomeCategory) ? this.familyIncomeCategory.getId() : familyIncomeCategoryId;
    }

    public void setFamilyIncomeCategoryId(Long familyIncomeCategoryId) {
        if(Objects.isNull(this.familyIncomeCategory)){
            this.familyIncomeCategoryId = familyIncomeCategoryId;
            this.familyIncomeCategory = new FamilyIncomeCategory(familyIncomeCategoryId);
        }
    }

    public Long getBankDetailId() {
        return Objects.nonNull(this.bankDetail) ? this.bankDetail.getId() : null;
    }

    public void setBankDetailId(Long bankDetailId) {
        if(Objects.isNull(this.bankDetail)){
            this.bankDetailId = bankDetailId;
            this.bankDetail = new BankDetail();
            this.bankDetail.setId(bankDetailId);
        }
    }

}
