package com.mnit.erp.hr.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;      

import java.io.Serializable;
import java.util.Date;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.mnit.erp.category.model.Category;
import com.mnit.erp.department.model.Department;
import com.mnit.erp.designation.model.Designation;
import com.mnit.erp.address.model.Address;
import com.mnit.erp.common.MaritalStatus;
import com.mnit.erp.common.Tier;
import com.mnit.erp.common.Gender;
import com.mnit.erp.common.Pwd;
import com.mnit.erp.religion.model.Religion;
import com.mnit.erp.state.model.State;
import com.mnit.erp.district.model.District;

import java.util.List;
import java.util.Objects;

/**
 *
 * @author Prahalad
 */
@Data
@NoArgsConstructor
@AllArgsConstructor

@Entity
public class Employee implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    @Column(unique=true, nullable = false)
    private String employeeCode;
    
    /*********** Personal Details **************************/
    @Column(nullable=false)
    private String name;
    
    @Column(nullable=false)
    private String fatherName;
    
    @Column(nullable=false)
    @JsonFormat(pattern="yyyy-MM-dd")
    private Date dob;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = false, referencedColumnName = "id", insertable =  true, updatable = true)
    @OnDelete(action = OnDeleteAction.NO_ACTION)
    private Category category;     
    @Transient Long categoryId;
    
    @Column(nullable=false)
    private Gender gender;
    
    @Column(nullable=false)
    private Pwd pwd;
    
    @Column(nullable=true)
    private String bloodGroup;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = true, referencedColumnName = "id", insertable =  true, updatable = true)
    @OnDelete(action = OnDeleteAction.NO_ACTION)
    private Religion religion;     
    @Transient Long religionId;
    
    @Column(nullable=false)
    private MaritalStatus maritalStatus;
    
    @Column(nullable=true)
    private String spouseName;
    
    @Column(nullable=true)
    private String placeOfBirth;
    /*********** Personal Details Ends *********************/
    
    /*********** Employment Details ************************/
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = false, referencedColumnName = "id", insertable =  true, updatable = true)
    @OnDelete(action = OnDeleteAction.NO_ACTION)
    private PayBand payBand;    
    @Transient Long payBandId;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = false, referencedColumnName = "id", insertable =  true, updatable = true)
    @OnDelete(action = OnDeleteAction.NO_ACTION)
    private GradePay gradePay;    
    @Transient Long gradePayId;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = false, referencedColumnName = "id", insertable =  true, updatable = true)
    @OnDelete(action = OnDeleteAction.NO_ACTION)
    private PayLevel payLevel;    
    @Transient Long payLevelId;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = false, referencedColumnName = "id", insertable =  true, updatable = true)
    @OnDelete(action = OnDeleteAction.NO_ACTION)
    private AppointmentNature natureOfAppointment;    
    @Transient Long natureOfAppointmentId;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = false, referencedColumnName = "id", insertable =  true, updatable = true)
    @OnDelete(action = OnDeleteAction.NO_ACTION)
    private Department department;     
    @Transient Long departmentId;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = true, referencedColumnName = "id", insertable =  true, updatable = true)
    @OnDelete(action = OnDeleteAction.NO_ACTION)
    private Department adjunctDepartment;     
    @Transient Long adjunctDepartmentId;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = false, referencedColumnName = "id", insertable =  true, updatable = true)
    @OnDelete(action = OnDeleteAction.NO_ACTION)
    private Designation designation;     
    @Transient Long designationId;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = false, referencedColumnName = "id", insertable =  true, updatable = true)
    @OnDelete(action = OnDeleteAction.NO_ACTION)
    private Cadre cadre;    
    @Transient Long cadreId;
    
    @Column(nullable=true)
    private Tier tier; 
    
    @Column(nullable=false)
    @JsonFormat(pattern="yyyy-MM-dd")
    private Date dateOfJoining;
    
    @Column(nullable=false)
    @JsonFormat(pattern="yyyy-MM-dd")
    private Date dojPresentPost;
    
    @Column(nullable=false)
    @JsonFormat(pattern="yyyy-MM-dd")
    private Date dateOfRetirement;
    
    @Column(nullable=false)
    private int retirementAge;
    
    @Column(nullable=true)
    private Boolean ltcTag; //Yes/No
    
    @Column(nullable=true)
    private Boolean daArrearTag; //Yes/No
    
    @Column(nullable=true)
    private double lastIncrementAmt;
    
    @Column(nullable=true)
    @JsonFormat(pattern="yyyy-MM-dd")
    private Date dateOfLastIncrement;
    
    @Column(nullable=true)
    @JsonFormat(pattern="yyyy-MM-dd")
    private Date dateOfNextIncrement;
    /*********** Employment Details Ends ***********************/
    
    /*********** Salary Details ********************************/
    @Column(nullable=true)
    private Boolean processSalary;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = true, referencedColumnName = "id", insertable =  true, updatable = true)
    @OnDelete(action = OnDeleteAction.NO_ACTION)
    private PayAccountType payAccountType;   
    @Transient Long payAccountTypeId;
    
    @Column(nullable=true)
    private String salaryType; //Pension/PF
    
    @Column(nullable=true)
    private String calculationType; //Regular and more
    /*********** Salary Details Ends ********************************/
    
    /************************* Pension Details ***********************/
    @Column(nullable=true)
    private String ppono;
    
    @Column(nullable=true)
    private String pensionNominee;
    
    @Column(nullable=true)
    @JsonFormat(pattern="yyyy-MM-dd")
    private Date pensionNomineeDob;
    
    @Column(nullable=true)
    private String mnitSiteProfileId;
    
    @Column(nullable=true)
    private String businessUnit;
    /***************** Pension Details Ends ***********************/
    
    /****************** Contact Details **************************/
    @OneToMany
    List<Address> addresses;
    
    @Column(nullable=true)
    private String cugNum;
    
    @Column(nullable=false)
    private String emergencyContactNum; 
    
    @Column(nullable=false)
    private String primaryEmail;
    
    @Column(nullable=true)
    private String instituteEmail;
    /****************** Contact Details Ends **************************/
    
    /****************** Misc. Details *********************************/
    @Column(nullable=true)
    private Boolean trainingNeeds;
    
    @Column(nullable=true)
    private String traningType;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = true, referencedColumnName = "id", insertable =  true, updatable = true)
    @OnDelete(action = OnDeleteAction.NO_ACTION)
    private MessCouncil messCouncilMember;  
    @Transient Long messCouncilMemberId;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = true, referencedColumnName = "id", insertable =  true, updatable = true)
    @OnDelete(action = OnDeleteAction.NO_ACTION)
    private Hostel hostel; 
    @Transient Long hostelId;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = true, referencedColumnName = "id", insertable =  true, updatable = true)
    @OnDelete(action = OnDeleteAction.NO_ACTION)
    private CostCenter costCenter;
    @Transient Long costCenterId;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = true, referencedColumnName = "id", insertable =  true, updatable = true)
    @OnDelete(action = OnDeleteAction.NO_ACTION)
    private WageType wageType;
    @Transient Long wageTypeId;
    
    @Column(nullable=true)
    private String appraisalGrade;  
    /****************** Misc. Details *********************************/
    
    /****************** Identification Details ************************/
    @Column(nullable=true)
    private String panNum; 
    
    @Column(nullable=true)
    private String pfNum;
    
    @Column(nullable=true)
    private String prnNum;
    
    @Column(nullable=true)
    private String bankName;
    
    @Column(nullable=true)
    private String bankAccNum;
    
    @Column(nullable=true)
    private String gsliNum;
    
    @Column(nullable=true)
    private String aadhaarNum;
    /****************** Identification Details Ends ************************/
    
    /************************* EPIC Details *****************/
    @Column(nullable=true)
    private String epicNum;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = true, referencedColumnName = "id", insertable =  true, updatable = true)
    @OnDelete(action = OnDeleteAction.NO_ACTION)
    private State epicState;
    @Transient Long epicStateId;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = true, referencedColumnName = "id", insertable =  true, updatable = true)
    @OnDelete(action = OnDeleteAction.NO_ACTION)
    private District epicDistrict;
    @Transient Long epicDistrictId;
    
    @Column(nullable=true)
    private String epicConstituency;
    
    @Column(nullable=true)
    private String epicWardNum;
    /************************* EPIC Details Ends*****************/
    
    /************************ Employee Status *************************/
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = false, referencedColumnName = "id", insertable =  true, updatable = true)
    @OnDelete(action = OnDeleteAction.NO_ACTION)
    private EmployeeStatus employeeStatus;
    @Transient Long employeeStatusId;
    /************************ Employee Status Ends *************************/
    
    /************************* Create/Update Details *********************/
    
    public Employee(Long id) {
        this.id = id;
    }

    public Long getCategoryId() {
        return Objects.nonNull(this.category) ? this.category.getId() : null;
    }
    public void setCategoryId(Long categoryId) {
        if(Objects.isNull(this.category) && Objects.nonNull(categoryId)){
            this.categoryId = categoryId;
            this.category = new Category(categoryId);
        }
    } 
    
    public Long getReligionId(){
        return Objects.nonNull(this.religion) ? this.religion.getId() : null;
    }
    public void setReligionId(Long religionId) {
        if(Objects.isNull(this.religion) && Objects.nonNull(religionId)){
            this.religionId = religionId;
            this.religion = new Religion(religionId);
        }
    } 
    
    public Long getPayBandId(){
        return Objects.nonNull(this.payBand) ? this.payBand.getId() : null;
    }
    public void setPayBandId(Long payBandId) {
        if(Objects.isNull(this.payBand) && Objects.nonNull(payBandId)){
            this.payBandId = payBandId;
            this.payBand = new PayBand(payBandId);
        }
    }
    
    public Long getGradePayId(){
        return Objects.nonNull(this.gradePay) ? this.gradePay.getId() : null;
    }
    public void setGradePayId(Long gradePayId) {
        if(Objects.isNull(this.gradePay) && Objects.nonNull(gradePayId)){
            this.gradePayId = gradePayId;
            this.gradePay = new GradePay(gradePayId);
        }
    }
    
    public Long getPayLevelId(){
        return Objects.nonNull(this.payLevel) ? this.payLevel.getId() : null;
    }
    public void setPayLevelId(Long payLevelId) {
        if(Objects.isNull(this.payLevel) && Objects.nonNull(payLevelId)){
            this.payLevelId = payLevelId;
            this.payLevel = new PayLevel(payLevelId);
        }
    }
    
    public Long getNatureOfAppointmentId(){
        return Objects.nonNull(this.natureOfAppointment) ? this.natureOfAppointment.getId() : null;
    }
    public void setNatureOfAppointmentId(Long natureOfAppointmentId) {
        if(Objects.isNull(this.natureOfAppointment) && Objects.nonNull(natureOfAppointmentId)){
            this.natureOfAppointmentId = natureOfAppointmentId;
            this.natureOfAppointment = new AppointmentNature(natureOfAppointmentId);
        }
    }

    public Long getDepartmentId(){
        return Objects.nonNull(this.department) ? this.department.getId() : null;
    }
    public void setDepartmentId(Long departmentId) {
        if(Objects.isNull(this.department) && Objects.nonNull(departmentId)){
            this.departmentId = departmentId;
            this.department = new Department(departmentId);
        }
    }
    
    public Long getAdjunctDepartmentId(){
        return Objects.nonNull(this.adjunctDepartment) ? this.adjunctDepartment.getId() : null;
    }
    public void setAdjunctDepartmentId(Long adjunctDepartmentId) {
        if(Objects.isNull(this.adjunctDepartment) && Objects.nonNull(adjunctDepartmentId)){
            this.adjunctDepartmentId = adjunctDepartmentId;
            this.adjunctDepartment = new Department(adjunctDepartmentId);
        }
    }
    
    public Long getDesignationId(){
        return Objects.nonNull(this.designation) ? this.designation.getId() : null;
    }
    public void setDesignationId(Long designationId) {
        if(Objects.isNull(this.designation) && Objects.nonNull(designationId)){
            this.designationId = designationId;
            this.designation = new Designation(designationId);
        }
    }
    
    public Long getCadreId(){
        return Objects.nonNull(this.cadre) ? this.cadre.getId() : null;
    }
    public void setCadreId(Long cadreId) {
        if(Objects.isNull(this.cadre) && Objects.nonNull(cadreId)){
            this.cadreId = cadreId;
            this.cadre = new Cadre(cadreId);
        }
    }
    
    public Long getPayAccountTypeId(){
        return Objects.nonNull(this.payAccountType) ? this.payAccountType.getId() : null;
    }
    public void setPayAccountTypeId(Long payAccountTypeId) {
        if(Objects.isNull(this.payAccountType) && Objects.nonNull(payAccountTypeId)){
            this.payAccountTypeId = payAccountTypeId;
            this.payAccountType = new PayAccountType(payAccountTypeId);
        }
    }
    
    public Long getMessCouncilMemberId(){
        return Objects.nonNull(this.messCouncilMember) ? this.messCouncilMember.getId() : null;
    }
    public void setMessCouncilMemberId(Long messCouncilMemberId) {
        if(Objects.isNull(this.messCouncilMember) && Objects.nonNull(messCouncilMemberId)){
            this.messCouncilMemberId = messCouncilMemberId;
            this.messCouncilMember = new MessCouncil(messCouncilMemberId);
        }
    }
    
    public Long getHostelId(){
        return Objects.nonNull(this.hostel) ? this.hostel.getId() : null;
    }
    public void setHostelId(Long hostelId) {
        if(Objects.isNull(this.hostel) && Objects.nonNull(hostelId)){
            this.hostelId = hostelId;
            this.hostel = new Hostel(hostelId);
        }
    }
    
    public Long getCostCenterId(){
        return Objects.nonNull(this.costCenter) ? this.costCenter.getId() : null;
    }
    public void setCostCenterId(Long costCenterId) {
        if(Objects.isNull(this.costCenter) && Objects.nonNull(costCenterId)){
            this.costCenterId = costCenterId;
            this.costCenter = new CostCenter(costCenterId);
        }
    }
    
    public Long getWageTypeId(){
        return Objects.nonNull(this.wageType) ? this.wageType.getId() : null;
    }
    public void setWageTypeId(Long wageTypeId) {
        if(Objects.isNull(this.wageType) && Objects.nonNull(wageTypeId)){
            this.wageTypeId = wageTypeId;
            this.wageType = new WageType(wageTypeId);
        }
    }
    
    public Long getEpicStateId(){
        return Objects.nonNull(this.epicState) ? this.epicState.getId() : null;
    }
    public void setEpicStateId(Long epicStateId) {
        if(Objects.isNull(this.epicState) && Objects.nonNull(epicStateId)){
            this.epicStateId = epicStateId;
            this.epicState = new State(epicStateId);
        }
    }
    
    public Long getEpicDistrictId(){
        return Objects.nonNull(this.epicDistrict) ? this.epicDistrict.getId() : null;
    }
    public void setEpicDistrictId(Long epicDistrictId) {
        if(Objects.isNull(this.epicDistrict) && Objects.nonNull(epicDistrictId)){
            this.epicDistrictId = epicDistrictId;
            this.epicDistrict = new District(epicDistrictId);
        }
    }
    
    public Long getEmployeeStatusId(){
        return Objects.nonNull(this.employeeStatus) ? this.employeeStatus.getId() : null;
    }
    public void setEmployeeStatusId(Long employeeStatusId) {
        if(Objects.isNull(this.employeeStatus) && Objects.nonNull(employeeStatusId)){
            this.employeeStatusId = employeeStatusId;
            this.employeeStatus = new EmployeeStatus(employeeStatusId);
        }
    }
}
