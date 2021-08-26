package com.mnit.erp.academic.student.st.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.mnit.erp.academic.branch.model.Branch;
import com.mnit.erp.academic.branch.service.BranchService;
import com.mnit.erp.academic.preadmission.preadmissiondata.model.PreAdmissionData;
import com.mnit.erp.academic.preadmission.preadmissiondata.repository.PreAdmissionDataRepository;
import com.mnit.erp.academic.program.model.Program;
import com.mnit.erp.academic.program.service.ProgramService;
import com.mnit.erp.academic.semester.model.Semester;
import com.mnit.erp.academic.specialization.model.Specialization;
import com.mnit.erp.academic.specialization.service.SpecializationSerivce;
import com.mnit.erp.academic.student.st.filter.StudentFilter.StudentFilter;
import com.mnit.erp.academic.student.st.model.Student;
import com.mnit.erp.academic.student.st.model.StudentDocument;
import com.mnit.erp.academic.student.st.model.StudentDocumentStatus;
import com.mnit.erp.academic.student.st.model.StudentIdSequence;
import com.mnit.erp.academic.student.st.model.StudentSemester;
import com.mnit.erp.academic.student.st.repository.StudentDocumentRepository;
import com.mnit.erp.academic.student.st.repository.StudentDocumentStatusRepository;
import com.mnit.erp.academic.student.st.repository.StudentRepository;
import com.mnit.erp.academic.student.st.repository.StudentSemesterRepository;
import com.mnit.erp.address.model.Address;
import com.mnit.erp.address.repository.AddressRepository;
import com.mnit.erp.common.model.BankDetail;
import com.mnit.erp.common.repository.BankDetailRepository;
import com.mnit.erp.entranceExam.repository.EntranceExamRepository;
import com.mnit.erp.exceptions.ServiceException;
import com.mnit.erp.family.repository.FamilyMemberRepository;
import com.mnit.erp.identificationDocument.repository.IdentificationDocumentRepository;
import com.mnit.erp.response.CustomResponseMessage;
import com.mnit.erp.security.WebSecurityConfig;
import com.mnit.erp.user.model.RoleAssignmentType;
import com.mnit.erp.user.model.User;
import com.mnit.erp.user.model.UserRole;
import com.mnit.erp.user.repository.UserRoleRepository;
import com.mnit.erp.user.service.UserService;
import com.mnit.erp.util.CommonUtils;
import com.mnit.erp.util.EmailSender;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class StudentService {

    @Autowired
    StudentRepository studentRepository;

    @Autowired
    EntranceExamRepository entranceExamRepository;

    @Autowired
    AddressRepository addressRepository;

    @Autowired
    FamilyMemberRepository familyMemberRepository;

    @Autowired
    IdentificationDocumentRepository identificationDocumentRepository;

    @Autowired
    ProgramService programService;

    @Autowired
    BranchService branchService;

    @Autowired
    UserService userService;

    @Autowired
    SpecializationSerivce specializationSerivce;

    @Autowired
    StudentSemesterRepository studentSemesterRepository;
    @Autowired
    StudentDocumentRepository studentDocumentRepository;
    @Autowired
    StudentDocumentStatusRepository studentDocumentStatusRepository;
    @Autowired
    PreAdmissionDataRepository preAdmissionDataRepository;
    @Autowired
    BankDetailRepository bankDetailRepository;
    
    @Autowired
    EmailSender emailSender;
    
    @Autowired
    UserRoleRepository userRoleRepository;
    public Student add(Student student){
        if(this.validate(student)){

            this.saveStudentMappingData(student);
            Semester semester = new Semester();
            semester.setId(1L);
            student.setSemester(semester);
            // SAVE STUDENT
            BankDetail bankDetail = new BankDetail();
            if(student.getBankDetail() != null) {
            	student.getBankDetail().setAccountFor("Student");
            	bankDetail = bankDetailRepository.save(student.getBankDetail());
            	student.setBankDetail(bankDetail);
            }
            //Entry in semester table
            for(StudentSemester ss : student.getStudentSemester())
            	ss.setStudent(student);
            if(student.getStudentSemester().size() == 0) {
                StudentSemester studentSemester = new StudentSemester();
                studentSemester.setStudent(student);
                studentSemester.setSemester(semester);
                studentSemester.setStatus("Active");
                student.getStudentSemester().add(studentSemester);
            }
            student.setAdmissionDate(new Date());
            student.setAdmissionYear(Long.valueOf(new Date().getYear()));
            Student savedStudent = this.studentRepository.save(student);
            bankDetail.setAccountForId(savedStudent.getId());
            bankDetailRepository.save(bankDetail);
            if(student.getPreAdmissionData() != null) {
            	PreAdmissionData preAdmissionData = preAdmissionDataRepository.findById(student.getPreAdmissionData().getId()).orElse(null);  
            	if(preAdmissionData != null && !"Submitted".equals(preAdmissionData.getStudentStatus())) {
            		preAdmissionData.setStudentStatus("Submitted");
            		preAdmissionDataRepository.save(preAdmissionData);
            	}
            }
            return savedStudent;
        }
        return null;
    }

    private void saveStudentMappingData(Student student){
        // SAVE ADDRESSES INFORMATION
        if(Objects.nonNull(student.getAddresses()) && !student.getAddresses().isEmpty()){
            this.addressRepository.saveAll(student.getAddresses());
        }

        // SAVE ENTRANCE EXAM INFORMATION
        if(Objects.nonNull(student.getEntranceExam())){
            this.entranceExamRepository.save(student.getEntranceExam());
        }

        // SAVE FAMILY MEMBERS INFORMATION
        if(Objects.nonNull(student.getFamilyMembers()) && !student.getFamilyMembers().isEmpty()){
            this.familyMemberRepository.saveAll(student.getFamilyMembers());
        }

        // SAVE IDENTIFICATION DOCUMENTS
        if(Objects.nonNull(student.getIdentificationDocuments()) && !student.getIdentificationDocuments().isEmpty()){
            this.identificationDocumentRepository.saveAll(student.getIdentificationDocuments());
        }
    }

    private boolean validate(Student student){
        return true;
    }

    public Student find(Long id) {
        return this.studentRepository.findById(id).orElse(null);
    }

    private String generateStudentId(Student student){
        if(Objects.nonNull(student.getStudentId())){
            throw new ServiceException("Student ID already generated!");
        }

        Program studentProgram = this.programService.find(student.getProgramId());
        // FOR UG AND PHD students branchCode
        Branch studentBranch = this.branchService.find(student.getBranchId());

        student.setAdmissionYear((long) Calendar.getInstance().get(Calendar.YEAR));
        String branchCode = studentBranch.getCode();

        if("P".equals(studentProgram.getCode())){
            // PG STUDENTS : SPECIALIZATION CODE
            Specialization specialization = this.specializationSerivce.find(student.getSpecializationId());
            branchCode = specialization.getCode();
        }

        Long maxSequence = this.studentRepository.maxSequenceByAdmissionYearAndProgram(student.getAdmissionYear(), studentProgram.getId());
        if(Objects.isNull(maxSequence) || maxSequence == 0 ){
            if("U".equals(studentProgram.getCode())){
                maxSequence = StudentIdSequence.UG;
            }else if("P".equals(studentProgram.getCode())){
                maxSequence = StudentIdSequence.PG;
            }else{
                maxSequence = StudentIdSequence.PHD_EVEN;
            }
        }
        if(maxSequence > 0) {
            student.setSequence(maxSequence + 1);
            student.setStudentId(student.getAdmissionYear() + studentProgram.getCode() + branchCode + student.getSequence());
            User user = createStudentUser(student);
            if(user == null) student.setStudentId(null);
        }
        return student.getStudentId();
    }

    public Student findByStudentId(String studentId) {
        Student student = this.studentRepository.findByStudentId(studentId);
        if(Objects.isNull(student)){
            throw new ServiceException("Student with ID : " + studentId + " not found!");
        }
        return student;
    }

    private User createStudentUser(Student student){
        User user = new User();
        user.setUsername(student.getStudentId());
        user.setPassword(student.getStudentId());
        user.setAddedOn(new Date());
        user.setEmail(student.getEmailPrimary());
        user.setMobile(student.getMobile());
        user.setEnabled(true);
        user.setActivated(true);
        try {
            user = userService.createUser(user);
        }catch(Exception ex) {
        	user = null;
        }
        
        if(user != null) {
        	UserRole userRole = new UserRole();
        	userRole.setAssignedBy(student.getAuthOfficer().getId());
        	userRole.setAssignedOn(new Date());
        	userRole.setRemarks("StudentUser Creation");
        	userRole.setUserId(user.getId());
        	userRole.setRoleId(1L);
        	userRole.setRoleAssignmentType(RoleAssignmentType.PERMANENT_ROLE);
        	userRoleRepository.save(userRole);
            //send mail to student for the user
            String emailBody = "Dear " + student.getName() + ",<br><br>"
            		+ "Please find following login user at student portal of MNIT<br>"
            		+ "User Name : " + student.getStudentId() + " <br><br>"
            		+ "Password will be send on seprate mail<br><br>"
            		+ "Thanks & Regards,<br>"
            		+ "Team Server (MNIT)";
            emailSender.sentEmailToReceipent(student.getEmailPrimary(), "", "Your login created", emailBody, "");
            emailBody = "Dear " + student.getName() + ",<br><br>"
            		+ "Please find following login password at student portal of MNIT<br>"
            		+ "Password : " + student.getStudentId() + " <br><br>"
            		+ "Password will be send on seprate mail<br><br>"
            		+ "Thanks & Regards,<br>"
            		+ "Team Server (MNIT)";
            emailSender.sentEmailToReceipent(student.getEmailPrimary(), "", "Your login credential detail", emailBody, "");
        }
        return user;        
    }

    public Student update(Student updateStudent) {
        Student savedStudent = this.studentRepository.findById(updateStudent.getId()).orElse(null);
        if(Objects.isNull(savedStudent)){
            throw new ServiceException("Invalid student ID. Can't update record!");
        }
        this.handleStudentUpdate(savedStudent, updateStudent);
        this.saveStudentMappingData(savedStudent);
        if(savedStudent.getStudentId() == null) {
        	savedStudent.setStudentId(this.generateStudentId(savedStudent));
        	savedStudent.setAdmissionDate(new Date());
        	savedStudent.setAdmissionYear(Long.valueOf(new Date().getYear()));
        }
        savedStudent = this.studentRepository.save(savedStudent);
        return savedStudent;
    }

    private void handleStudentUpdate(Student savedStudent, Student updateStudent){
        // Don't Allow update of mapping data lists
        updateStudent.setFamilyMembers(null);
        updateStudent.setEntranceExam(null);
        updateStudent.setAddresses(null);
        updateStudent.setIdentificationDocuments(null);
        //HANDLE field level access rights here
        CommonUtils.copyNonNullProperties(updateStudent, savedStudent);
    }

    public Page<Student> findByFilter(StudentFilter studentFilter, Pageable pageable) {
        return this.studentRepository.findAllByOrderByIdDesc(pageable);
    }
    
    public List<Student> findByStudentIdNull() {
        return this.studentRepository.findByStudentIdNull();
    }

	public List<Student> findByStudentIdNotNull() {
		return this.studentRepository.findByStudentIdNotNull();
	}

	public List<Object> findByStudentIdNullView() {
		List<Object> objList = new ArrayList<Object>();
		for(Student student : this.studentRepository.findByStudentIdNull()) {
			HashMap<String, Object> jsonObj = new HashMap<String, Object>();
			jsonObj.put("stId", student.getId() != null ?  student.getId() : -1);
			jsonObj.put("preRegId", student.getPreAdmissionData() != null ?  student.getPreAdmissionData().getId() : -1);
			jsonObj.put("name", student.getName());
			jsonObj.put("mobile", student.getMobile());
			jsonObj.put("gender", Objects.nonNull(student.getGender()) ? student.getGender().name() :"");
			jsonObj.put("dob", student.getDob());
			jsonObj.put("emailSecondary", student.getEmailSecondary());
			jsonObj.put("emailPrimary", student.getEmailPrimary());
			jsonObj.put("category", Objects.nonNull(student.getCategory()) ? student.getCategory().getName() : "");
			jsonObj.put("admittedCategory", Objects.nonNull(student.getAdmittedCategory()) ? student.getAdmittedCategory().getName() :"");
			jsonObj.put("pwd", student.getPwd());
			jsonObj.put("admittedPwd", student.getAdmittedPwd());
			jsonObj.put("bloodGroup", student.getBloodGroup());
			jsonObj.put("familyIncomeCategory", Objects.nonNull(student.getFamilyIncomeCategory()) ? student.getFamilyIncomeCategory().getName() :"");
			jsonObj.put("citizenShip", Objects.nonNull(student.getCitizenShip()) ? student.getCitizenShip().name() : "");
			jsonObj.put("program", Objects.nonNull(student.getProgram()) ? student.getProgram().getName() : "");
			jsonObj.put("degree", Objects.nonNull(student.getDegree()) ? student.getDegree().getName() : "");
			jsonObj.put("department", Objects.nonNull(student.getDepartment()) ? student.getDepartment().getName() : "");
			jsonObj.put("branch", Objects.nonNull(student.getBranch()) ? student.getBranch().getName() : "");
			jsonObj.put("specialization", Objects.nonNull(student.getSpecialization()) ? student.getSpecialization().getName():"");
			jsonObj.put("Semester", Objects.nonNull(student.getSemester()) ? student.getSemester().getName() : "");
			jsonObj.put("selectionBoard", Objects.nonNull(student.getSelectionBoard()) ? student.getSelectionBoard().getName() : "");
			jsonObj.put("selectionBoardType", Objects.nonNull(student.getSelectionBoardType()) ? student.getSelectionBoardType().getName():"");
			jsonObj.put("sponsoredBy", student.getSponsoredBy());
			jsonObj.put("admissionYear", student.getAdmissionYear());
			jsonObj.put("admissionType", Objects.nonNull(student.getAdmissionType()) ? student.getAdmissionType().getName() :"");
			//Address Array
			List<Object> objAddress = new ArrayList<Object>();
			for(Address adr : student.getAddresses()) {
				HashMap<String, Object> jsonAddr = new HashMap<String, Object>();
				jsonAddr.put("addressLine1",adr.getAddressLine1());
				jsonAddr.put("addressLine2",adr.getAddressLine2());
				jsonAddr.put("landMark",adr.getLandMark());
				jsonAddr.put("city",adr.getCity());
				jsonAddr.put("district",Objects.nonNull(adr.getDistrict()) ? adr.getDistrict().getName() :"");
				jsonAddr.put("state",Objects.nonNull(adr.getDistrict()) && Objects.nonNull(adr.getDistrict().getState())? adr.getDistrict().getState().getName() : "");
				jsonAddr.put("country",Objects.nonNull(adr.getDistrict()) && Objects.nonNull(adr.getDistrict().getState()) && Objects.nonNull(adr.getDistrict().getState().getCountry()) ? adr.getDistrict().getState().getCountry().getName() :"");
				jsonAddr.put("pin",adr.getPin());
				objAddress.add(jsonAddr);
			}
			jsonObj.put("addresses", objAddress);
			jsonObj.put("identificationDocuments", student.getIdentificationDocuments());
			jsonObj.put("familyMembers", student.getFamilyMembers());
			jsonObj.put("examType", Objects.nonNull(student.getEntranceExam()) ? student.getEntranceExam().getExamType() :"");
			jsonObj.put("examYear", Objects.nonNull(student.getEntranceExam()) ? student.getEntranceExam().getExamYear() : "");
			jsonObj.put("rollNo", Objects.nonNull(student.getEntranceExam()) ? student.getEntranceExam().getRollNo() :"");
			jsonObj.put("score", Objects.nonNull(student.getEntranceExam()) ? student.getEntranceExam().getScore() : "");
			jsonObj.put("examRank", Objects.nonNull(student.getEntranceExam()) ? student.getEntranceExam().getExamRank() :"");
			
			jsonObj.put("bankName", Objects.nonNull(student.getBankDetail()) ? student.getBankDetail().getBankName() :"");
			jsonObj.put("branchName", Objects.nonNull(student.getBankDetail()) ? student.getBankDetail().getBranchName() :"");
			jsonObj.put("accountNo", Objects.nonNull(student.getBankDetail()) ? student.getBankDetail().getAccountNo() : "");
			jsonObj.put("accountName", Objects.nonNull(student.getBankDetail()) ? student.getBankDetail().getAccountName() :"");
			jsonObj.put("ifsc", Objects.nonNull(student.getBankDetail()) ? student.getBankDetail().getIFSC() : "");

			objList.add(jsonObj);
		}
		return objList;
	}

	public List<Student> findAsParam(Student  student) {
    	List<Student> students = null;
    	try{
    		students = this.studentRepository.findAsParam(student);
    	}catch(Exception ex){
    		log.error("",ex);
    	}
        return students;
    }

    public CustomResponseMessage addUpdate(StudentDocument  studentDocument) {
    	String sActionRes = "save";
    	try{
        	if(studentDocument.getId() != null && studentDocument.getId() > 0){
        		sActionRes = "update";
        		StudentDocument studentDocumentEx = this.studentDocumentRepository.findById(studentDocument.getId()).orElse(null);
        		if(studentDocumentEx != null) {
        			CommonUtils.copyNonNullProperties(studentDocument, studentDocumentEx);
        			studentDocument = studentDocumentEx;
        		}
        	}
        	//if syllabus and reference file has values then save the file
			if (studentDocument.getFile() != null && studentDocument.getFile().getBytes().length > 0) {
				studentDocument.setFilePath(CommonUtils.saveFile("student/documents", studentDocument.getFile()));
			}
			studentDocumentRepository.save(studentDocument);

			if(studentDocument.getAuthOfficer() != null) {
    			StudentDocumentStatus studentDocumentStatus = studentDocumentStatusRepository.findTopByStudentDocumentOrderByIdDesc(studentDocument);
    			if(studentDocumentStatus == null || !studentDocumentStatus.getDocStatus().equals(studentDocument.getDocStatus()) ) 
    				studentDocumentStatus = new StudentDocumentStatus();
    			
				studentDocumentStatus.setStudentDocument(studentDocument);
				studentDocumentStatus.setAuthOfficer(studentDocument.getAuthOfficer());
				studentDocumentStatus.setDocStatus(studentDocument.getDocStatus());
				studentDocumentStatus.setRemarks(studentDocument.getRemarks());
				studentDocumentStatusRepository.save(studentDocumentStatus);
    		}
			
    		sActionRes = "Student document " + sActionRes + " successfuly";
    	}catch(Exception ex){
    		log.error("",ex);
    		studentDocument = null;
    		sActionRes = "Unable to "+ sActionRes +" student document";
    	}
    	return CommonUtils.buildResponse(studentDocument, sActionRes);
    }
    
    public CustomResponseMessage studentDocumentFindAll(){
    	List<StudentDocument> studentDocuments = studentDocumentRepository.findAll();
    	for(StudentDocument studentDocument : studentDocuments) {
        	if(studentDocument.getFilePath() != null) {
        		try {
					studentDocument.setBase64File( CommonUtils.readFileAsBase64(studentDocument.getFilePath()));
				} catch (IOException e) {
					e.printStackTrace();
				}
        	}
    	}
    	return CommonUtils.buildResponse(studentDocuments, "Find student documents");
    }
    
    public CustomResponseMessage studentDocumentFindByStudentId(Long studentId, String type){
    	List<StudentDocument> studentDocuments = null;
    	if("Pre".equalsIgnoreCase(type))
    		studentDocuments = studentDocumentRepository.findAllByPreAdmissionData_Id(studentId);
    	else
    		studentDocuments = studentDocumentRepository.findAllByStudent_Id(studentId);
    	for(StudentDocument studentDocument : studentDocuments) {
        	if(studentDocument.getFilePath() != null) {
        		try {
					studentDocument.setBase64File( CommonUtils.readFileAsBase64(studentDocument.getFilePath()));
				} catch (IOException e) {
					e.printStackTrace();
				}
        	}
    	}
    	return CommonUtils.buildResponse(studentDocuments, "Find student documents");
    }
    
    public CustomResponseMessage uploadFile(Long studentDocumentId,MultipartFile file){
    	String sActionRes = "File uploaded failed";
    	StudentDocument studentDocumentEx = null;
		try{
        	if(studentDocumentId > 0 && file.getBytes().length > 0){
        		studentDocumentEx = studentDocumentRepository.findById(studentDocumentId).orElse(null);
        		if(studentDocumentEx != null) {
        			if (file != null && file.getBytes().length > 0) {
        				studentDocumentEx.setFilePath(CommonUtils.saveFile("student/documents", file));
        			}
        			studentDocumentRepository.save(studentDocumentEx);
        			studentDocumentEx.setBase64File( CommonUtils.readFileAsBase64(studentDocumentEx.getFilePath()));
    	    		sActionRes = "File uploaded successfuly";
        		}
        	}
    	}catch(Exception ex){
    		log.error("",ex);
    		studentDocumentEx = null;
    		sActionRes = "Unable to upload file";
    	}
    	return CommonUtils.buildResponse(studentDocumentEx, sActionRes);
    }

    @SuppressWarnings("deprecation")
	public CustomResponseMessage updateApprovalStatus(Map<String, Object> reqParam) {
    	String sActionRes = "";
    	Student studentEx = null;
		try{
			Long id = 0l;
			if(reqParam.containsKey("id")) {
				id = Long.valueOf(reqParam.get("id").toString());
	    		studentEx = studentRepository.findById(id).orElse(null);
	    		if(studentEx != null) {
	    			if(reqParam.containsKey("authOfficerId")) {
	    				User user = new User();
		    			user.setId(Long.valueOf(reqParam.get("authOfficerId").toString()));
		    			studentEx.setAuthOfficer(user);
		    		}
	    			studentEx.setRemark(reqParam.containsKey("remark") ? reqParam.get("remark").toString() : "");
	    			studentEx.setStatus(reqParam.containsKey("status") ? reqParam.get("status").toString() : "");
	    			if(studentEx.getAdmissionYear() == null) {
		    			studentEx.setAdmissionDate(new Date());
		                studentEx.setAdmissionYear(Long.valueOf(new Date().getYear()));
	    			}
	    			studentEx.setStudentId(generateStudentId(studentEx));
	    			if(studentEx.getStudentId() == null) {
		        		sActionRes = "Something wrong in studentId or User creation";
	    			}else {
	    				studentRepository.save(studentEx);
		        		sActionRes = "Approval status updated successful";
	    			}
	    		}else {
	        		sActionRes = "Student not found";
	    		}
			}else {
        		sActionRes = "id not found in request";
			}
    	}catch(Exception ex){
    		log.error("",ex);
    		studentEx = null;
    		sActionRes = "Unable to update status";
    	}
    	return CommonUtils.buildResponse(studentEx, sActionRes);
    }
    
    public CustomResponseMessage updateBankDetail(BankDetail bankDetail) {
    	String sActionRes = "";
    	Student studentEx = null;
		try{
    		studentEx = studentRepository.findById(bankDetail.getAccountForId()).orElse(null);
    		if(studentEx != null) {
    			bankDetail.setAccountFor("Student");
    			bankDetail = bankDetailRepository.save(bankDetail);
    			studentEx.setBankDetail(bankDetail);
    			studentRepository.save(studentEx);
        		sActionRes = "Bank detail updated successful";
    		}else {
        		sActionRes = "Student not found";
    		}
    	}catch(Exception ex){
    		log.error("",ex);
    		studentEx = null;
    		sActionRes = "Unable to update bank detail";
    	}
    	return CommonUtils.buildResponse(studentEx, sActionRes);
    }
    
    public CustomResponseMessage uploadPhoto(Long preAdmissionId,MultipartFile photo, String photoType){
    	String sActionRes = "photo uploaded failed";
    	Student studentEx = null;
		try{
        	if(preAdmissionId > 0 && photo.getBytes().length > 0){
        		studentEx = studentRepository.findByPreAdmissionData_Id(preAdmissionId);
        		if(studentEx != null) {
        			if (photo != null && photo.getBytes().length > 0) {
        				studentEx.setPhotoPath(CommonUtils.saveFile("student/photo", photo));
        				studentEx.setPhotoType(photoType);
        			}
        			studentRepository.save(studentEx);
        			studentEx.setBase64Photo(CommonUtils.readFileAsBase64(studentEx.getPhotoPath()));
    	    		sActionRes = "File uploaded successfuly";
        		}
        	}
    	}catch(Exception ex){
    		log.error("",ex);
    		studentEx = null;
    		sActionRes = "Unable to upload photo";
    	}
    	return CommonUtils.buildResponse(studentEx, sActionRes);
    }
    
}
