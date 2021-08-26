package com.mnit.erp.util;

import com.mnit.erp.academic.admissionType.model.AdmissionType;
import com.mnit.erp.academic.admissionType.service.AdmissionTypeService;
import com.mnit.erp.academic.branch.model.Branch;
import com.mnit.erp.academic.branch.service.BranchService;
import com.mnit.erp.academic.degree.model.Degree;
import com.mnit.erp.academic.degree.service.DegreeService;
import com.mnit.erp.academic.exam.grade.model.Grade;
import com.mnit.erp.academic.exam.grade.service.GradeService;
import com.mnit.erp.academic.program.model.Program;
import com.mnit.erp.academic.program.service.ProgramService;
import com.mnit.erp.academic.section.model.Section;
import com.mnit.erp.academic.section.service.SectionService;
import com.mnit.erp.academic.selectionboard.model.SelectionBoard;
import com.mnit.erp.academic.selectionboard.service.SelectionBoardService;
import com.mnit.erp.academic.selectionboard.type.model.SelectionBoardType;
import com.mnit.erp.academic.selectionboard.type.service.SelectionBoardTypeService;
import com.mnit.erp.academic.semester.model.Semester;
import com.mnit.erp.academic.semester.service.SemesterService;
import com.mnit.erp.academic.specialization.model.Specialization;
import com.mnit.erp.academic.specialization.service.SpecializationSerivce;
import com.mnit.erp.academic.student.incomeCategory.model.FamilyIncomeCategory;
import com.mnit.erp.academic.student.incomeCategory.service.FamilyIncomeCategoryService;
import com.mnit.erp.academic.student.status.model.StudentStatus;
import com.mnit.erp.academic.student.status.service.StudentStatusService;
import com.mnit.erp.academic.student.studentSubType.model.StudentSubType;
import com.mnit.erp.academic.student.studentSubType.service.StudentSubTypeService;
import com.mnit.erp.academic.student.studentType.model.StudentType;
import com.mnit.erp.academic.student.studentType.service.StudentTypeService;
import com.mnit.erp.category.model.Category;
import com.mnit.erp.category.service.CategoryService;
import com.mnit.erp.country.model.Country;
import com.mnit.erp.country.service.CountryService;
import com.mnit.erp.department.model.Department;
import com.mnit.erp.department.service.DepartmentService;
import com.mnit.erp.district.model.District;
import com.mnit.erp.district.service.DistrictService;
import com.mnit.erp.exceptions.ServiceException;
import com.mnit.erp.notification.model.NotificationCategory;
import com.mnit.erp.notification.repository.NotificationCategoryRepository;
import com.mnit.erp.notification.service.NotificationCategoryService;
import com.mnit.erp.state.model.State;
import com.mnit.erp.state.service.StateService;
import com.mnit.erp.user.model.User;
import com.mnit.erp.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.UUID;
/**
 *
 * @author: Tejpal Singh
 */
@Component
public class MastersInitializer implements CommandLineRunner {

    @Autowired
    StudentSubTypeService studentSubTypeService;

    @Autowired
    StudentTypeService studentTypeService;

    @Autowired
    GradeService gradeService;

    @Autowired
    AdmissionTypeService admissionTypeService;

    @Autowired
    ProgramService programService;

    @Autowired
    DegreeService degreeService;

    @Autowired
    DepartmentService departmentService;

    @Autowired
    CategoryService categoryService;

    @Autowired
    CountryService countryService;

    @Autowired
    StateService stateService;

    @Autowired
    BranchService branchService;

    @Autowired
    SpecializationSerivce specializationSerivce;

    @Autowired
    StudentStatusService studentStatusService;

    @Autowired
    FamilyIncomeCategoryService familyIncomeCategoryService;

    @Autowired
    SemesterService semesterService;

    @Autowired
    SectionService sectionService;

    @Autowired
    SelectionBoardService selectionBoardService;

    @Autowired
    SelectionBoardTypeService selectionBoardTypeService;

    @Autowired
    UserService userService;

    @Autowired
    DistrictService districtService;

    @Autowired
    NotificationCategoryRepository notificationCategoryRepository;

    @Override
    public void run(String... args) throws Exception {

        // COMMON MASTERS
        this.initializeDepartmentMaster();
        this.initializeCategoryMaster();
        this.initializeCountryMaster();
        this.initializeStateMaster();
        this.initializeDistrictsMaster();
        this.initializeNotificationCategories();

        // ACADEMIC MASTERS
        this.initializeAdmissionTypeMaster();
        this.initializeProgramMaster();
        this.initializeDegreeMaster();
        this.initializeBranchMaster();
        this.initializeSpecializationMaster();
        this.initializeStudentStatusMaster();
        this.initializeFamilyIncomeCategoryMaster();
        this.initializeSemesterMaster();
        this.initializeSectionService();
        this.initializeSelectionBoardMaster();
        this.initializeGrade();
        this.initializeStudentType();
        this.initializeStudentSubType();

        // INITIALIZE USER
        this.initializeUser();
    }
    private void initializeStudentSubType() {
        //Student types List
        List<StudentSubType> studentSubTypeList = this.studentSubTypeService.findAll();
        if(!studentSubTypeList.isEmpty()){
            return;
        }
        List<StudentSubType> studentSubTypes = Arrays.asList(
                new StudentSubType(1l, "Research Student With Assistantship", true, 1L),
                new StudentSubType(2l, "Research Student Without Assistantship", true, 1L),
                new StudentSubType(3l, "Research Student Without Assistantship", true, 2L),
                new StudentSubType(4l, "Research Student Without Assistantship", true, 3L)
        );
        studentSubTypes.forEach(studentSubType -> {
            try {
                this.studentSubTypeService.add(studentSubType);

            }catch(ServiceException se){
                System.out.println(se.getMessage());
            }
        });
    }

    private void initializeNotificationCategories() {
        List<NotificationCategory> notificationCategories = Arrays.asList(
                new NotificationCategory("LATEST_NEWS","Latest News","Notifications related to the latest news, office orders or circulars.", true,1L),
                new NotificationCategory("UPCOMING_EVENTS","Upcoming Events","Upcoming Events list.", true,2L),
                new NotificationCategory("STUDENT_CORNER","Student's Corner","List of news related to students only", true,3L),
                new NotificationCategory("VACANCIES","Vacancies","Latest news from recruitment cell", true,4L),
                new NotificationCategory("TENDER","Tenders","List of news, notices or orders related to tenders.", true,5L)
        );

        notificationCategories.forEach(notificationCategory -> {
            try{
                NotificationCategory byName = this.notificationCategoryRepository.findByName(notificationCategory.getName());
                if(Objects.nonNull(byName)){
                    this.notificationCategoryRepository.save(byName);
                }else{
                    System.out.println(notificationCategory.getName() + " already exists!");
                }
            }catch(ServiceException se){
                System.out.println(se.getMessage());
            }
        });
    }

    private void initializeStudentType() {
        //Student types List
        List<StudentType> studentTypeList = this.studentTypeService.findAll();
        if(!studentTypeList.isEmpty()){
            return;
        }
        List<StudentType> studentTypes = Arrays.asList(
                new StudentType(1L, "Full Time", true),
                new StudentType(2L, "Part Time", true),
                new StudentType(3L, "Off Campus", true)
        );

        studentTypes.forEach(studentType -> {
            try {
                this.studentTypeService.add(studentType);
            }catch(ServiceException se){
                System.out.println(se.getMessage());
            }
        });
    }

    private void initializeGrade() {
        //Grades List
        List<Grade> gradeList = this.gradeService.findAll();
        if(Objects.nonNull(gradeList)){
            return;
        }
        List<Grade> grades = Arrays.asList(
                new Grade(1L, "AA", 10L,true,"Outstanding"),
                new Grade(2L, "AB", 9L,true,"Excellent"),
                new Grade(3L, "BB", 8L,true,"Very Good"),
                new Grade(4L, "BC", 7L,true,"Good"),
                new Grade(5L, "CC", 6L,true,"Average"),
                new Grade(6L, "CD", 5L,true,"Below Average"),
                new Grade(7L, "D", 4L,true,"Marginal"),
                new Grade(8L, "DD", 4L,true,"Marginal"),
                new Grade(9L, "E", 0L,false,"Poor"),
                new Grade(10L, "F", 0L,true,"Fail"),
                new Grade(11L, "FA", 0L,true,"Fail due to Attendance Shortage"),
                new Grade(12L, "FP", 0L,true,"Fail due to Poor Performance"),
                new Grade(13L, "I", 0L,true,"Incomplete"),
                new Grade(14L, "IW", 0L,true,"Work In Progress"),
                new Grade(15L, "OPTED", 0L,false,"Opted"),
                new Grade(16L, "S", 0L,true,"Satisfactory"),
                new Grade(17L, "W", 0L,true,"Withdrawal"),
                new Grade(18L, "WR", 0L,true,"Waiver"),
                new Grade(19L, "X", 0L,true,"Unsatisfactory")
        );

        grades.forEach(grade -> {
            try {
                this.gradeService.add(grade);
            }catch(ServiceException se){
                System.out.println(se.getMessage());
            }
        });
    }

    private void initializeUser() {
        //Users List
        List<User> users = Arrays.asList(
            new User(1L, "harigyan", "harigyan", "hari.cse@mnit.ac.in", "9549650136", true, true, new Date(), UUID.randomUUID().toString()),
            new User(2L, "prahalad", "prahalad", "prahalad.ccc@mnit.ac.in", "9549650732", true, true, new Date(), UUID.randomUUID().toString()),
            new User(3L, "vikas", "vikas", "vikas.ccc@mnit.ac.in", "9785575456", true, true, new Date(), UUID.randomUUID().toString()),
            new User(4L, "tejpal", "tejpal", "tejpal.ccc@mnit.ac.in", "7976545492", true, true, new Date(), UUID.randomUUID().toString()),
            new User(5L, "rajesh", "rajesh", "rajeshmeerwal.ccc@mnit.ac.in", "9549659046", true, true, new Date(), UUID.randomUUID().toString()),
            new User(6L, "nknama", "nknama", "nandkishornama@gmail.com", "9001798489", true, true, new Date(), UUID.randomUUID().toString()),
            new User(7L, "surendra", "surendra", "surendra1407@gmail.com", "9799497218", true, true, new Date(), UUID.randomUUID().toString()),
            new User(8L, "deepak", "deepak", "ddakesto@gmail.com", "9001787668", true, true, new Date(), UUID.randomUUID().toString())
        );
        
        users.forEach(user -> {
            try {
                this.userService.createUser(user);
            }catch(ServiceException se){
                System.out.println(se.getMessage());
            }
        });
    }

    private void initializeSelectionBoardMaster() {

        //SELECTION BOARD TYPE
        List<SelectionBoardType> selectionBoardTypes = Arrays.asList(
                new SelectionBoardType(1L, "CIWG"     , "Children of Indian Workers in Gulf Countries",true),
                new SelectionBoardType(2L, "NONSAARC", "NONSAARC",true),
                new SelectionBoardType(3L, "SAARC"    , "SAARC",true),
                new SelectionBoardType(4L, "EDUCATION", "EDUCATION",true),
                new SelectionBoardType(5L, "WELFARE"  , "WELFARE ",true)
        );

        selectionBoardTypes.forEach(selectionBoardType -> {
            try{
                this.selectionBoardTypeService.add(selectionBoardType);
            }catch(ServiceException se){
                System.out.println(se.getMessage());
            }
        });
        List<SelectionBoard> selectionBoardList = this.selectionBoardService.findAll();
        if(!selectionBoardList.isEmpty()){
            return;
        }
        // SELECTION BOARD
        List<SelectionBoard> selectionBoards = Arrays.asList(
                new SelectionBoard(1L, "CCB", "Central Counselling Board", false, this.selectionBoardTypeService.findByIdIn(Arrays.asList()), null),
                new SelectionBoard(2L, "CCMN", "Centralised Counselling for M.Sc.", true, this.selectionBoardTypeService.findByIdIn(Arrays.asList()), null),
                new SelectionBoard(3L, "CCMT", "Centralized Counselling for MTech / MArch/ MPlan", true, this.selectionBoardTypeService.findByIdIn(Arrays.asList()), null),
                new SelectionBoard(4L, "CSAB", "Central Seat Allocation Board", true, this.selectionBoardTypeService.findByIdIn(Arrays.asList()), null),
                new SelectionBoard(5L, "DASA  ", "Direct Admission of Students Abroad", true, this.selectionBoardTypeService.findByIdIn(Arrays.asList(1L, 2L, 3L)), null),
                new SelectionBoard(6L, "DEE", "DEPARTMENTAL ENTRANCE EXAM", true, this.selectionBoardTypeService.findByIdIn(Arrays.asList()), null),
                new SelectionBoard(7L, "GATE SCORE", "GATE SCORE", true, this.selectionBoardTypeService.findByIdIn(Arrays.asList()), null),
                new SelectionBoard(8L, "ICCR", "Indian Council for Cultural Relations", true, this.selectionBoardTypeService.findByIdIn(Arrays.asList(2L, 3L)), null),
                new SelectionBoard(9L, "JRF", "Junior Research Fellowship", true, this.selectionBoardTypeService.findByIdIn(Arrays.asList()), null),
                new SelectionBoard(10L, "MEA", "Ministry of External Affairs", true, this.selectionBoardTypeService.findByIdIn(Arrays.asList(3L, 4L, 5L)), null),
                new SelectionBoard(11L, "QIP", "Qualified Institutional Placement", true, this.selectionBoardTypeService.findByIdIn(Arrays.asList()), null),
                new SelectionBoard(12L, "QIP(POLY)", "Qualified Institutional Placement (Poly)", true, this.selectionBoardTypeService.findByIdIn(Arrays.asList()), null),
                new SelectionBoard(13L, "SPONSORED", "SPONSORED", true, this.selectionBoardTypeService.findByIdIn(Arrays.asList()), null),
                new SelectionBoard(14L, "TEQIP-III", "Technical Education Quality Improvement Programme", true, this.selectionBoardTypeService.findByIdIn(Arrays.asList()), null)
        );

        selectionBoards.forEach(selectionBoard -> {
            try{
                this.selectionBoardService.add(selectionBoard);
            }catch(ServiceException se){
                System.out.println(se.getMessage());
            }
        });

    }

    private void initializeSectionService() {
        List<Section> sectionList = this.sectionService.findAll();
        if(!sectionList.isEmpty()){
            return;
        }
        List<Section> sections = Arrays.asList(
                new Section(1L, "A", true),
                new Section(2L, "B", true),
                new Section(3L, "C", true),
                new Section(4L, "D", true),
                new Section(5L, "E", true),
                new Section(6L, "F", true),
                new Section(7L, "G", true),
                new Section(8L, "H", true),
                new Section(9L, "I", true),
                new Section(10L, "J", true)
        );
        sections.forEach(section -> {
            try{
                this.sectionService.add(section);
            }catch(ServiceException se){
                System.out.println(se.getMessage());
            }
        });
    }

    private void initializeSemesterMaster() {
        List<Semester> semesterList = this.semesterService.findAll();
        if(!semesterList.isEmpty()){
            return;
        }
        List<Semester> semesters = Arrays.asList(
                new Semester(1L,1L, "First", "ODD", true),
                new Semester(2L,2L, "Second ", "EVEN", true),
                new Semester(3L,3L, "Third", "ODD", true),
                new Semester(4L,4L, "Fourth", "EVEN", true),
                new Semester(5L,5L, "Fifth", "ODD", true),
                new Semester(6L,6L, "Sixth", "EVEN", true),
                new Semester(7L,7L, "Seventh", "ODD", true),
                new Semester(8L,8L, "Eighth", "EVEN", true),
                new Semester(9L,9L, "Ninth", "ODD", true),
                new Semester(10L,10L, "Tenth", "EVEN", true),
                new Semester(11L,11L, "Eleventh", "ODD", true),
                new Semester(12L,12L, "Twelfth", "EVEN", true),
                new Semester(13L,13L, "Thirteenth", "ODD", true),
                new Semester(14L,14L, "Fourteenth", "EVEN", true)
        );
        semesters.forEach(semester -> {
            try{
                this.semesterService.add(semester);
            }catch(ServiceException se){
                System.out.println(se.getMessage());
            }
        });
    }

    private void initializeFamilyIncomeCategoryMaster() {
        List<FamilyIncomeCategory> familyIncomeCategoryList = this.familyIncomeCategoryService.findAll();
        if(!familyIncomeCategoryList.isEmpty()){
            return;
        }
        List<FamilyIncomeCategory> familyIncomeCategories = Arrays.asList(
                new FamilyIncomeCategory(1L, "Below 1 Lac", "", 0.0, 100000.0, true),
                new FamilyIncomeCategory(2L, "1 Lac to 5 Lac", "", 100001.0, 500000.0, true),
                new FamilyIncomeCategory(3L, "Above 5 Lac", "", 500001.0, 99999999.0, true)
        );
        familyIncomeCategories.forEach(familyIncomeCategory -> {
            try {
                this.familyIncomeCategoryService.add(familyIncomeCategory);
            } catch (ServiceException se) {
                System.out.println(se.getMessage());
            }
        });

    }

    private void initializeStudentStatusMaster() {
        List<StudentStatus> studentStatusList = this.studentStatusService.findAll();
        if(!studentStatusList.isEmpty()){
            return;
        }
        List<StudentStatus> studentStatuses = Arrays.asList(
                new StudentStatus(1L, "A", "Active", "Student is active", true, true),
                new StudentStatus(2L, "C", "Cancelled", "Admission Cancelled before first semester", true, false),
                new StudentStatus(3L, "D", "Demised", "Student is no more", true, true),
                new StudentStatus(4L, "I", "Inactive", "Student will have limited access ", true, true),
                new StudentStatus(5L, "P", "Passed Out", "Completed the course", true, true),
                new StudentStatus(6L, "T", "Terminated", "Student terminated by institute", true, true),
                new StudentStatus(7L, "W", "Withdrawn", "Student withdrawn his/her admission", true, true),
                new StudentStatus(8L, "M", "Migrated", "Student migrated to other institute before first semester", true, false)
        );
        studentStatuses.forEach(studentStatus -> {
            try {
                this.studentStatusService.add(studentStatus);
            } catch (ServiceException se) {
                System.out.println(se.getMessage());
            }
        });
    }

    private void initializeSpecializationMaster() {
        List<Specialization> specializationList = this.specializationSerivce.findAll();
        if(!specializationList.isEmpty()){
            return;
        }
        List<Specialization> specializations = Arrays.asList(
                new Specialization(1L, null, "CHEMICAL ENGINEERING", true, "CH", this.branchService.find(3L)),
                new Specialization(2L, null, "CHEMISTRY ", true, "CY", this.branchService.find(4L)),
                new Specialization(3L, null, "CIVIL ENGINEERING (DISASTER ASSESSMENT AND MITIGATION", true, "CD", this.branchService.find(5L)),
                new Specialization(4L, null, "COMPUTER ENGINEERING", true, "CP", this.branchService.find(6L)),
                new Specialization(5L, null, "COMPUTER ENGINEERING AND INFORMATION SECURITY", true, "IS", this.branchService.find(6L)),
                new Specialization(6L, null, "DESIGN ENGINEERING", true, "DE", this.branchService.find(14L)),
                new Specialization(7L, null, "EARTHQUAKE ENGINEERING", true, "EQ", this.branchService.find(16L)),
                new Specialization(8L, null, "ELECTRONICS & COMMUNICATION ENGINE", true, "EC", this.branchService.find(8L)),
                new Specialization(9L, null, "EMBEDDED SYSTEMS  ", true, "EB", this.branchService.find(8L)),
                new Specialization(10L, null, "ENVIRONMENTAL ENGINEERING ", true, "CE", this.branchService.find(5L)),
                new Specialization(11L, null, "INDUSTRIAL ENGINEERING", true, "IE", this.branchService.find(14L)),
                new Specialization(12L, null, "MANAGEMENT STUDIES", true, "BM", this.branchService.find(11L)),
                new Specialization(13L, null, "MATERIALS SCIENCE AND ENGINEERING ", true, "MS", this.branchService.find(12L)),
                new Specialization(14L, null, "MATHEMATIC", true, "MA", this.branchService.find(13L)),
                new Specialization(15L, null, "METALLURGICAL & MATERIALS ENGINEER", true, "MT", this.branchService.find(15L)),
                new Specialization(16L, null, "PHYSICS", true, "PH", this.branchService.find(17L)),
                new Specialization(17L, null, "POWER ELECTRONICS AND DRIVE", true, "PD", this.branchService.find(7L)),
                new Specialization(18L, null, "POWER SYSTEMS", true, "ES", this.branchService.find(7L)),
                new Specialization(19L, null, "POWER SYSTEMS MANAGEMENT  ", true, "SM", this.branchService.find(7L)),
                new Specialization(20L, null, "PRODUCTION ENGINEERING", true, "PE", this.branchService.find(14L)),
                new Specialization(21L, null, "RENEWABLE ENERGY  ", true, "CV", this.branchService.find(2L)),
                new Specialization(22L, null, "STRUCTURAL ENGINEERING", true, "CS", this.branchService.find(5L)),
                new Specialization(23L, null, "THERMAL ENGINEERING", true, "TE", this.branchService.find(14L)),
                new Specialization(24L, null, "TRANSPORTATION ENGINEERING", true, "CT", this.branchService.find(5L)),
                new Specialization(25L, null, "URBAN PLAN", true, "AR", this.branchService.find(1L)),
                new Specialization(26L, null, "VLSI DESIGN", true, "EV", this.branchService.find(8L)),
                new Specialization(27L, null, "WATER RESOURCES ENGINEERING", true, "CW", this.branchService.find(5L)),
                new Specialization(28L, null, "WIRELESS AND OPTICAL COMMUNICATION", true, "WC", this.branchService.find(8L))
        );
        specializations.forEach(specialization -> {
            try {
                this.specializationSerivce.add(specialization);
            } catch (ServiceException se) {
                System.out.println(se);
                this.specializationSerivce.update(specialization);
            }
        });
    }

    private void initializeBranchMaster() {
        List<Branch> branchList = this.branchService.findAll();
        if(!branchList.isEmpty()){
            return;
        }
        List<Branch> branches = Arrays.asList(
                new Branch(1L, null, "ARCHITECTURE AND PLANNING", "A", true, "AR", this.departmentService.find(1L), this.degreeService.findByDegreeIdIn(Arrays.asList(1L, 3L, 7L)), null, null),
                new Branch(2L, null, "CENTRE FOR ENERGY AND ENVIRONMENT", "A", true, "EN", this.departmentService.find(2L), this.degreeService.findByDegreeIdIn(Arrays.asList(5L, 7L)), null, null),
                new Branch(3L, null, "CHEMICAL ENGINEERING", "A", true, "CH", this.departmentService.find(3L), this.degreeService.findByDegreeIdIn(Arrays.asList(2L, 5L, 7L)), null, null),
                new Branch(4L, null, "CHEMISTRY", "A", true, "CY", this.departmentService.find(4L), this.degreeService.findByDegreeIdIn(Arrays.asList(4L, 7L)), null, null),
                new Branch(5L, null, "CIVIL ENGINEERING", "A", true, "CE", this.departmentService.find(5L), this.degreeService.findByDegreeIdIn(Arrays.asList(2L, 5L, 7L)), null, null),
                new Branch(6L, null, "COMPUTER SCIENCE AND ENGINEERING", "A", true, "CP", this.departmentService.find(6L), this.degreeService.findByDegreeIdIn(Arrays.asList(2L, 5L, 7L)), null, null),
                new Branch(7L, null, "ELECTRICAL ENGINEERING", "A", true, "EE", this.departmentService.find(7L), this.degreeService.findByDegreeIdIn(Arrays.asList(2L, 5L, 7L)), null, null),
                new Branch(8L, null, "ELECTRONICS AND COMMUNICATION ENGINEERING", "A", true, "EC", this.departmentService.find(8L), this.degreeService.findByDegreeIdIn(Arrays.asList(2L, 5L, 7L)), null, null),
                new Branch(9L, null, "HUMANITIES AND SOCIAL SCIENCE", "A", true, "HS", this.departmentService.find(9L), this.degreeService.findByDegreeIdIn(Arrays.asList(7L)), null, null),
                new Branch(10L, null, "INFORMATION TECHNOLOGY", "A", true, "IT", this.departmentService.find(10L), this.degreeService.findByDegreeIdIn(Arrays.asList(2L)), null, null),
                new Branch(11L, null, "MANAGEMENT STUDIES", "A", true, "BM", this.departmentService.find(11L), this.degreeService.findByDegreeIdIn(Arrays.asList(6L, 7L)), null, null),
                new Branch(12L, null, "MATERIAL RESEARCH CENTER", "A", true, "", this.departmentService.find(12L), this.degreeService.findByDegreeIdIn(Arrays.asList(5L, 7L)), null, null),
                new Branch(13L, null, "MATHEMATICS", "A", true, "MA", this.departmentService.find(13L), this.degreeService.findByDegreeIdIn(Arrays.asList(4L, 7L)), null, null),
                new Branch(14L, null, "MECHANICAL ENGINEERING", "A", true, "ME", this.departmentService.find(14L), this.degreeService.findByDegreeIdIn(Arrays.asList(2L, 5L, 7L)), null, null),
                new Branch(15L, null, "METALLURGICAL AND MATERIALS ENGINEERING", "A", true, "MT", this.departmentService.find(15L), this.degreeService.findByDegreeIdIn(Arrays.asList(2L, 5L, 7L)), null, null),
                new Branch(16L, null, "NATIONAL CENTRE FOR DISASTER MITIGATION AND MANAGEMENT", "A", true, "", this.departmentService.find(16L), this.degreeService.findByDegreeIdIn(Arrays.asList(5L, 7L)), null, null),
                new Branch(17L, null, "PHYSICS", "A", true, "PY", this.departmentService.find(17L), this.degreeService.findByDegreeIdIn(Arrays.asList(7L)), null, null)
        );
        branches.forEach(branch -> {
            try {
                this.branchService.add(branch);
            } catch (ServiceException se) {
                System.out.println(se);
                this.branchService.update(branch);
            }
        });
    }

    private void initializeStateMaster() {
        List<State> stateList = this.stateService.findAll();
        if(!stateList.isEmpty()){
            return;
        }
        List<State> states = Arrays.asList(
                new State(	1L, 	"Andaman Nicobar", true, this.countryService.find(103L), 	"Union Territory", null),
                new State(	2L, 	"Andhra Pradesh", true, this.countryService.find(103L), 	"State", null),
                new State(	3L, 	"Arunachal Pradesh", true, this.countryService.find(103L), 	"State", null),
                new State(	4L, 	"Assam", true, this.countryService.find(103L), 	"State", null),
                new State(	5L, 	"Bihar", true, this.countryService.find(103L), 	"State", null),
                new State(	6L, 	"Chandigarh", true, this.countryService.find(103L), 	"Union Territory", null),
                new State(	7L, 	"Chhattisgarh", true, this.countryService.find(103L), 	"State", null),
                new State(	8L, 	"Dadra Nagar Haveli", true, this.countryService.find(103L), 	"Union Territory", null),
                new State(	9L, 	"Daman Diu", true, this.countryService.find(103L), 	"Union Territory", null),
                new State(	10L, 	"Delhi", true, this.countryService.find(103L), 	"Union Territory", null),
                new State(	11L, 	"Goa", true, this.countryService.find(103L), 	"State", null),
                new State(	12L, 	"Gujarat", true, this.countryService.find(103L), 	"State", null),
                new State(	13L, 	"Haryana", true, this.countryService.find(103L), 	"State", null),
                new State(	14L, 	"Himachal Pradesh", true, this.countryService.find(103L), 	"State", null),
                new State(	15L, 	"Jammu Kashmir", true, this.countryService.find(103L), 	"Union Territory", null),
                new State(	16L, 	"Jharkhand", true, this.countryService.find(103L), 	"State", null),
                new State(	17L, 	"Karnataka", true, this.countryService.find(103L), 	"State", null),
                new State(	18L, 	"Kerala", true, this.countryService.find(103L), 	"State", null),
                new State(	19L, 	"Ladakh", true, this.countryService.find(103L), 	"Union Territory", null),
                new State(	20L, 	"Lakshadweep", true, this.countryService.find(103L), 	"Union Territory", null),
                new State(	21L, 	"Madhya Pradesh", true, this.countryService.find(103L), 	"State", null),
                new State(	22L, 	"Maharashtra", true, this.countryService.find(103L), 	"State", null),
                new State(	23L, 	"Manipur", true, this.countryService.find(103L), 	"State", null),
                new State(	24L, 	"Meghalaya", true, this.countryService.find(103L), 	"State", null),
                new State(	25L, 	"Mizoram", true, this.countryService.find(103L), 	"State", null),
                new State(	26L, 	"Nagaland", true, this.countryService.find(103L), 	"State", null),
                new State(	27L, 	"Odisha", true, this.countryService.find(103L), 	"State", null),
                new State(	28L, 	"Puducherry", true, this.countryService.find(103L), 	"Union Territory", null),
                new State(	29L, 	"Punjab", true, this.countryService.find(103L), 	"State", null),
                new State(	30L, 	"Rajasthan", true, this.countryService.find(103L), 	"State", null),
                new State(	31L, 	"Sikkim", true, this.countryService.find(103L), 	"State", null),
                new State(	32L, 	"Tamil Nadu", true, this.countryService.find(103L), 	"State", null),
                new State(	33L, 	"Telangana", true, this.countryService.find(103L), 	"State", null),
                new State(	34L, 	"Tripura", true, this.countryService.find(103L), 	"State", null),
                new State(	35L, 	"Uttar Pradesh", true, this.countryService.find(103L), 	"State", null),
                new State(	36L, 	"Uttarakhand", true, this.countryService.find(103L), 	"State", null),
                new State(	37L, 	"West Bengal", true, this.countryService.find(103L), 	"State", null)
        );
        states.forEach(state -> {
            try {
                this.stateService.add(state);
            } catch (ServiceException se) {
                System.out.println(se.getMessage());
            }
        });
    }

    private void initializeProgramMaster() {
        List<Program> programList = this.programService.findAll();
        if(!programList.isEmpty()){
            return;
        }
        List<Program> programs = Arrays.asList(
                new Program(1L, "UG", "Under Graduate", "U"),
                new Program(2L, "PG", "Post Graduate", "P"),
                new Program(3L, "PhD", "Doctor of Philosophy", "R")
        );
        programs.forEach(program -> {
            try {
                this.programService.add(program);
            } catch (ServiceException se) {
                System.out.println(se.getMessage());
                this.programService.update(program);
            }
        });
    }

    private void initializeAdmissionTypeMaster() {
        List<AdmissionType> admissionTypeList = this.admissionTypeService.findAll();
        if(!admissionTypeList.isEmpty()){
            return;
        }
        List<AdmissionType> admissionTypes = Arrays.asList(
                new AdmissionType(1L, "FT", "FULL TIME", true),
                new AdmissionType(2L, "OFC", "OFF CAMPUS", true),
                new AdmissionType(3L, "PT", "PART TIME", true),
                new AdmissionType(4L, "STF", "STAFF", true)
        );
        admissionTypes.forEach(admissionType -> {
            try {
                admissionTypeService.add(admissionType);
            } catch (ServiceException se) {
                System.out.println(se.getMessage());
            }
        });
    }

    private void initializeDegreeMaster() {
        List<Degree> degreeList = this.degreeService.findAll();
        if(!degreeList.isEmpty()){
            return;
        }
        List<Degree> degrees = Arrays.asList(
                new Degree(1L, "B.Arch", "Bachelor of Architecture", this.programService.find(1L), null,null),
                new Degree(2L, "B.Tech", "Bachelor of Technology", this.programService.find(1L), null,null),
                new Degree(3L, "M.Plan", "Master of Planning", this.programService.find(2L), null,null),
                new Degree(4L, "M.Sc", "Master of Science", this.programService.find(2L), null,null),
                new Degree(5L, "M.Tech", "Master of Technology", this.programService.find(2L), null,null),
                new Degree(6L, "MBA", "Master of Business Administration", this.programService.find(2L), null,null),
                new Degree(7L, "Ph.D", "Doctor of Philosophy", this.programService.find(3L), null,null)
        );
        degrees.forEach(degree -> {
            try {
                this.degreeService.add(degree);
            } catch (ServiceException se) {
                System.out.println(se.getMessage());
            }
        });
    }

    private void initializeDepartmentMaster() {
        List<Department> departmentList = this.departmentService.findAll();
        if(!departmentList.isEmpty()){
            return;
        }
        List<Department> departments = Arrays.asList(
                new Department(1L, "ARCHITECTURE AND PLANNING", "A", true),
                new Department(2L, "CENTRE FOR ENERGY AND ENVIRONMENT", "A", true),
                new Department(3L, "CHEMICAL ENGINEERING", "A", true),
                new Department(4L, "CHEMISTRY", "A", true),
                new Department(5L, "CIVIL ENGINEERING", "A", true),
                new Department(6L, "COMPUTER SCIENCE AND ENGINEERING", "A", true),
                new Department(7L, "ELECTRICAL ENGINEERING", "A", true),
                new Department(8L, "ELECTRONICS AND COMMUNICATION ENGINEERING", "A", true),
                new Department(9L, "HUMANITIES AND SOCIAL SCIENCE", "A", true),
                new Department(10L, "INFORMATION TECHNOLOGY", "A", true),
                new Department(11L, "MANAGEMENT STUDIES", "A", true),
                new Department(12L, "MATERIAL RESEARCH CENTER", "A", true),
                new Department(13L, "MATHEMATICS", "A", true),
                new Department(14L, "MECHANICAL ENGINEERING", "A", true),
                new Department(15L, "METALLURGICAL AND MATERIALS ENGINEERING", "A", true),
                new Department(16L, "NATIONAL CENTRE FOR DISASTER MITIGATION AND MANAGEMENT", "A", true),
                new Department(17L, "PHYSICS", "A", true),
                new Department(18L, "ACADEMIC SECTION", "O", true),
                new Department(19L, "ESTABLISHMENT", "O", true),
                new Department(20L, "ACCOUNTS SECTION", "O", true),
                new Department(21L, "RESEARCH AND CONSULTANCY", "O", true),
                new Department(22L, "STUDENT WELFARE", "O", true),
                new Department(23L, "TRAINING AND PLACEMENT", "O", true),
                new Department(24L, "ESTATE", "O", true),
                new Department(25L, "STORE AND PURCHASE", "O", true)
        );

        departments.forEach(department -> {
            try {
                this.departmentService.add(department);
            } catch (ServiceException se) {
                System.out.println(se.getMessage());
            }
        });
    }

    private void initializeCategoryMaster() {
        List<Category> categoryList = this.categoryService.findAll();
        if(!categoryList.isEmpty()){
            return;
        }
        List<Category> categories = Arrays.asList(
                new Category(1L, "EWS", "Economically Weaker Section", true),
                new Category(2L, "GEN", "General", true),
                new Category(3L, "OBC", "Other Backward Class", true),
                new Category(4L, "SC", "Scheduled Castes", true),
                new Category(5L, "ST", "Scheduled Tribes", true)
        );
        categories.forEach(category -> {
            try {
                this.categoryService.add(category);
            } catch (ServiceException se) {
                System.out.println(se.getMessage());
            }
        });
    }

    private void initializeCountryMaster() {
        List<Country> countryList = this.countryService.findAll();
        if(!countryList.isEmpty()){
            return;
        }
        List<Country> countries = Arrays.asList(
                new Country(1L, "AF", "Afghanistan", true),
                new Country(2L, "AX", "Ã…land Islands", true),
                new Country(3L, "AL", "Albania", true),
                new Country(4L, "DZ", "Algeria", true),
                new Country(5L, "AS", "American Samoa", true),
                new Country(6L, "AD", "Andorra", true),
                new Country(7L, "AO", "Angola", true),
                new Country(8L, "AI", "Anguilla", true),
                new Country(9L, "AQ", "Antarctica", true),
                new Country(10L, "AG", "Antigua and Barbuda", true),
                new Country(11L, "AR", "Argentina", true),
                new Country(12L, "AM", "Armenia", true),
                new Country(13L, "AW", "Aruba", true),
                new Country(14L, "AU", "Australia", true),
                new Country(15L, "AT", "Austria", true),
                new Country(16L, "AZ", "Azerbaijan", true),
                new Country(17L, "BS", "Bahamas", true),
                new Country(18L, "BH", "Bahrain", true),
                new Country(19L, "BD", "Bangladesh", true),
                new Country(20L, "BB", "Barbados", true),
                new Country(21L, "BY", "Belarus", true),
                new Country(22L, "BE", "Belgium", true),
                new Country(23L, "BZ", "Belize", true),
                new Country(24L, "BJ", "Benin", true),
                new Country(25L, "BM", "Bermuda", true),
                new Country(26L, "BT", "Bhutan", true),
                new Country(27L, "BO", "Bolivia, Plurinational State of", true),
                new Country(28L, "BQ", "Bonaire, Sint Eustatius and Saba", true),
                new Country(29L, "BA", "Bosnia and Herzegovina", true),
                new Country(30L, "BW", "Botswana", true),
                new Country(31L, "BV", "Bouvet Island", true),
                new Country(32L, "BR", "Brazil", true),
                new Country(33L, "IO", "British Indian Ocean Territory", true),
                new Country(34L, "BN", "Brunei Darussalam", true),
                new Country(35L, "BG", "Bulgaria", true),
                new Country(36L, "BF", "Burkina Faso", true),
                new Country(37L, "BI", "Burundi", true),
                new Country(38L, "KH", "Cambodia", true),
                new Country(39L, "CM", "Cameroon", true),
                new Country(40L, "CA", "Canada", true),
                new Country(41L, "CV", "Cape Verde", true),
                new Country(42L, "KY", "Cayman Islands", true),
                new Country(43L, "CF", "Central African Republic", true),
                new Country(44L, "TD", "Chad", true),
                new Country(45L, "CL", "Chile", true),
                new Country(46L, "CN", "China", true),
                new Country(47L, "CX", "Christmas Island", true),
                new Country(48L, "CC", "Cocos (Keeling) Islands", true),
                new Country(49L, "CO", "Colombia", true),
                new Country(50L, "KM", "Comoros", true),
                new Country(51L, "CG", "Congo", true),
                new Country(52L, "CD", "Congo, the Democratic Republic of the", true),
                new Country(53L, "CK", "Cook Islands", true),
                new Country(54L, "CR", "Costa Rica", true),
                new Country(55L, "CI", "CÃ´te d'Ivoire", true),
                new Country(56L, "HR", "Croatia", true),
                new Country(57L, "CU", "Cuba", true),
                new Country(58L, "CW", "CuraÃ§ao", true),
                new Country(59L, "CY", "Cyprus", true),
                new Country(60L, "CZ", "Czech Republic", true),
                new Country(61L, "DK", "Denmark", true),
                new Country(62L, "DJ", "Djibouti", true),
                new Country(63L, "DM", "Dominica", true),
                new Country(64L, "DO", "Dominican Republic", true),
                new Country(65L, "EC", "Ecuador", true),
                new Country(66L, "EG", "Egypt", true),
                new Country(67L, "SV", "El Salvador", true),
                new Country(68L, "GQ", "Equatorial Guinea", true),
                new Country(69L, "ER", "Eritrea", true),
                new Country(70L, "EE", "Estonia", true),
                new Country(71L, "ET", "Ethiopia", true),
                new Country(72L, "FK", "Falkland Islands (Malvinas)", true),
                new Country(73L, "FO", "Faroe Islands", true),
                new Country(74L, "FJ", "Fiji", true),
                new Country(75L, "FI", "Finland", true),
                new Country(76L, "FR", "France", true),
                new Country(77L, "GF", "French Guiana", true),
                new Country(78L, "PF", "French Polynesia", true),
                new Country(79L, "TF", "French Southern Territories", true),
                new Country(80L, "GA", "Gabon", true),
                new Country(81L, "GM", "Gambia", true),
                new Country(82L, "GE", "Georgia", true),
                new Country(83L, "DE", "Germany", true),
                new Country(84L, "GH", "Ghana", true),
                new Country(85L, "GI", "Gibraltar", true),
                new Country(86L, "GR", "Greece", true),
                new Country(87L, "GL", "Greenland", true),
                new Country(88L, "GD", "Grenada", true),
                new Country(89L, "GP", "Guadeloupe", true),
                new Country(90L, "GU", "Guam", true),
                new Country(91L, "GT", "Guatemala", true),
                new Country(92L, "GG", "Guernsey", true),
                new Country(93L, "GN", "Guinea", true),
                new Country(94L, "GW", "Guinea-Bissau", true),
                new Country(95L, "GY", "Guyana", true),
                new Country(96L, "HT", "Haiti", true),
                new Country(97L, "HM", "Heard Island and McDonald Islands", true),
                new Country(98L, "VA", "Holy See (Vatican City State)", true),
                new Country(99L, "HN", "Honduras", true),
                new Country(100L, "HK", "Hong Kong", true),
                new Country(101L, "HU", "Hungary", true),
                new Country(102L, "IS", "Iceland", true),
                new Country(103L, "IN", "India", true),
                new Country(104L, "ID", "Indonesia", true),
                new Country(105L, "IR", "Iran, Islamic Republic of", true),
                new Country(106L, "IQ", "Iraq", true),
                new Country(107L, "IE", "Ireland", true),
                new Country(108L, "IM", "Isle of Man", true),
                new Country(109L, "IL", "Israel", true),
                new Country(110L, "IT", "Italy", true),
                new Country(111L, "JM", "Jamaica", true),
                new Country(112L, "JP", "Japan", true),
                new Country(113L, "JE", "Jersey", true),
                new Country(114L, "JO", "Jordan", true),
                new Country(115L, "KZ", "Kazakhstan", true),
                new Country(116L, "KE", "Kenya", true),
                new Country(117L, "KI", "Kiribati", true),
                new Country(118L, "KP", "Korea, Democratic People's Republic of", true),
                new Country(119L, "KR", "Korea, Republic of", true),
                new Country(120L, "KW", "Kuwait", true),
                new Country(121L, "KG", "Kyrgyzstan", true),
                new Country(122L, "LA", "Lao People's Democratic Republic", true),
                new Country(123L, "LV", "Latvia", true),
                new Country(124L, "LB", "Lebanon", true),
                new Country(125L, "LS", "Lesotho", true),
                new Country(126L, "LR", "Liberia", true),
                new Country(127L, "LY", "Libya", true),
                new Country(128L, "LI", "Liechtenstein", true),
                new Country(129L, "LT", "Lithuania", true),
                new Country(130L, "LU", "Luxembourg", true),
                new Country(131L, "MO", "Macao", true),
                new Country(132L, "MK", "Macedonia, the Former Yugoslav Republic of", true),
                new Country(133L, "MG", "Madagascar", true),
                new Country(134L, "MW", "Malawi", true),
                new Country(135L, "MY", "Malaysia", true),
                new Country(136L, "MV", "Maldives", true),
                new Country(137L, "ML", "Mali", true),
                new Country(138L, "MT", "Malta", true),
                new Country(139L, "MH", "Marshall Islands", true),
                new Country(140L, "MQ", "Martinique", true),
                new Country(141L, "MR", "Mauritania", true),
                new Country(142L, "MU", "Mauritius", true),
                new Country(143L, "YT", "Mayotte", true),
                new Country(144L, "MX", "Mexico", true),
                new Country(145L, "FM", "Micronesia, Federated States of", true),
                new Country(146L, "MD", "Moldova, Republic of", true),
                new Country(147L, "MC", "Monaco", true),
                new Country(148L, "MN", "Mongolia", true),
                new Country(149L, "ME", "Montenegro", true),
                new Country(150L, "MS", "Montserrat", true),
                new Country(151L, "MA", "Morocco", true),
                new Country(152L, "MZ", "Mozambique", true),
                new Country(153L, "MM", "Myanmar", true),
                new Country(154L, "NA", "Namibia", true),
                new Country(155L, "NR", "Nauru", true),
                new Country(156L, "NP", "Nepal", true),
                new Country(157L, "NL", "Netherlands", true),
                new Country(158L, "NC", "New Caledonia", true),
                new Country(159L, "NZ", "New Zealand", true),
                new Country(160L, "NI", "Nicaragua", true),
                new Country(161L, "NE", "Niger", true),
                new Country(162L, "NG", "Nigeria", true),
                new Country(163L, "NU", "Niue", true),
                new Country(164L, "NF", "Norfolk Island", true),
                new Country(165L, "MP", "Northern Mariana Islands", true),
                new Country(166L, "NO", "Norway", true),
                new Country(167L, "OM", "Oman", true),
                new Country(168L, "PK", "Pakistan", true),
                new Country(169L, "PW", "Palau", true),
                new Country(170L, "PS", "Palestine, State of", true),
                new Country(171L, "PA", "Panama", true),
                new Country(172L, "PG", "Papua New Guinea", true),
                new Country(173L, "PY", "Paraguay", true),
                new Country(174L, "PE", "Peru", true),
                new Country(175L, "PH", "Philippines", true),
                new Country(176L, "PN", "Pitcairn", true),
                new Country(177L, "PL", "Poland", true),
                new Country(178L, "PT", "Portugal", true),
                new Country(179L, "PR", "Puerto Rico", true),
                new Country(180L, "QA", "Qatar", true),
                new Country(181L, "RE", "RÃ©union", true),
                new Country(182L, "RO", "Romania", true),
                new Country(183L, "RU", "Russian Federation", true),
                new Country(184L, "RW", "Rwanda", true),
                new Country(185L, "BL", "Saint BarthÃ©lemy", true),
                new Country(186L, "SH", "Saint Helena, Ascension and Tristan da Cunha", true),
                new Country(187L, "KN", "Saint Kitts and Nevis", true),
                new Country(188L, "LC", "Saint Lucia", true),
                new Country(189L, "MF", "Saint Martin (French part)", true),
                new Country(190L, "PM", "Saint Pierre and Miquelon", true),
                new Country(191L, "VC", "Saint Vincent and the Grenadines", true),
                new Country(192L, "WS", "Samoa", true),
                new Country(193L, "SM", "San Marino", true),
                new Country(194L, "ST", "Sao Tome and Principe", true),
                new Country(195L, "SA", "Saudi Arabia", true),
                new Country(196L, "SN", "Senegal", true),
                new Country(197L, "RS", "Serbia", true),
                new Country(198L, "SC", "Seychelles", true),
                new Country(199L, "SL", "Sierra Leone", true),
                new Country(200L, "SG", "Singapore", true),
                new Country(201L, "SX", "Sint Maarten (Dutch part)", true),
                new Country(202L, "SK", "Slovakia", true),
                new Country(203L, "SI", "Slovenia", true),
                new Country(204L, "SB", "Solomon Islands", true),
                new Country(205L, "SO", "Somalia", true),
                new Country(206L, "ZA", "South Africa", true),
                new Country(207L, "GS", "South Georgia and the South Sandwich Islands", true),
                new Country(208L, "SS", "South Sudan", true),
                new Country(209L, "ES", "Spain", true),
                new Country(210L, "LK", "Sri Lanka", true),
                new Country(211L, "SD", "Sudan", true),
                new Country(212L, "SR", "Suriname", true),
                new Country(213L, "SJ", "Svalbard and Jan Mayen", true),
                new Country(214L, "SZ", "Swaziland", true),
                new Country(215L, "SE", "Sweden", true),
                new Country(216L, "CH", "Switzerland", true),
                new Country(217L, "SY", "Syrian Arab Republic", true),
                new Country(218L, "TW", "Taiwan, Province of China", true),
                new Country(219L, "TJ", "Tajikistan", true),
                new Country(220L, "TZ", "Tanzania, United Republic of", true),
                new Country(221L, "TH", "Thailand", true),
                new Country(222L, "TL", "Timor-Leste", true),
                new Country(223L, "TG", "Togo", true),
                new Country(224L, "TK", "Tokelau", true),
                new Country(225L, "TO", "Tonga", true),
                new Country(226L, "TT", "Trinidad and Tobago", true),
                new Country(227L, "TN", "Tunisia", true),
                new Country(228L, "TR", "Turkey", true),
                new Country(229L, "TM", "Turkmenistan", true),
                new Country(230L, "TC", "Turks and Caicos Islands", true),
                new Country(231L, "TV", "Tuvalu", true),
                new Country(232L, "UG", "Uganda", true),
                new Country(233L, "UA", "Ukraine", true),
                new Country(234L, "AE", "United Arab Emirates", true),
                new Country(235L, "GB", "United Kingdom", true),
                new Country(236L, "US", "United States", true),
                new Country(237L, "UM", "United States Minor Outlying Islands", true),
                new Country(238L, "UY", "Uruguay", true),
                new Country(239L, "UZ", "Uzbekistan", true),
                new Country(240L, "VU", "Vanuatu", true),
                new Country(241L, "VE", "Venezuela, Bolivarian Republic of", true),
                new Country(242L, "VN", "Viet Nam", true),
                new Country(243L, "VG", "Virgin Islands, British", true),
                new Country(244L, "VI", "Virgin Islands, U.S.", true),
                new Country(245L, "WF", "Wallis and Futuna", true),
                new Country(246L, "EH", "Western Sahara", true),
                new Country(247L, "YE", "Yemen", true),
                new Country(248L, "ZM", "Zambia", true),
                new Country(249L, "ZW", "Zimbabwe", true)
        );
        countries.forEach(country -> {
            try {
                this.countryService.add(country);
            } catch (ServiceException se) {
                System.out.println(se.getMessage());
            }
        });
    }

    private void initializeDistrictsMaster(){
        List<District> districtList = this.districtService.findAll();
        if(!districtList.isEmpty()){
            return;
        }
        List<District> districts = Arrays.asList(
                new District(	1L,	"Nicobar", 	this.stateService.find(	1L), null),
                new District(	2L,	"North Middle Andaman", 	this.stateService.find(	1L), null),
                new District(	3L,	"South Andaman", 	this.stateService.find(	1L), null),
                new District(	4L,	"Anantapur", 	this.stateService.find(	2L), null),
                new District(	5L,	"Chittoor", 	this.stateService.find(	2L), null),
                new District(	6L,	"East Godavari", 	this.stateService.find(	2L), null),
                new District(	7L,	"Guntur", 	this.stateService.find(	2L), null),
                new District(	8L,	"Kadapa", 	this.stateService.find(	2L), null),
                new District(	9L,	"Krishna", 	this.stateService.find(	2L), null),
                new District(	10L,	"Kurnool", 	this.stateService.find(	2L), null),
                new District(	11L,	"Nellore", 	this.stateService.find(	2L), null),
                new District(	12L,	"Prakasam", 	this.stateService.find(	2L), null),
                new District(	13L,	"Srikakulam", 	this.stateService.find(	2L), null),
                new District(	14L,	"Visakhapatnam", 	this.stateService.find(	2L), null),
                new District(	15L,	"Vizianagaram", 	this.stateService.find(	2L), null),
                new District(	16L,	"West Godavari", 	this.stateService.find(	2L), null),
                new District(	17L,	"Anjaw", 	this.stateService.find(	3L), null),
                new District(	18L,	"Central Siang", 	this.stateService.find(	3L), null),
                new District(	19L,	"Changlang", 	this.stateService.find(	3L), null),
                new District(	20L,	"Dibang Valley", 	this.stateService.find(	3L), null),
                new District(	21L,	"East Kameng", 	this.stateService.find(	3L), null),
                new District(	22L,	"East Siang", 	this.stateService.find(	3L), null),
                new District(	23L,	"Kamle", 	this.stateService.find(	3L), null),
                new District(	24L,	"Kra Daadi", 	this.stateService.find(	3L), null),
                new District(	25L,	"Kurung Kumey", 	this.stateService.find(	3L), null),
                new District(	26L,	"Lepa Rada", 	this.stateService.find(	3L), null),
                new District(	27L,	"Lohit", 	this.stateService.find(	3L), null),
                new District(	28L,	"Longding", 	this.stateService.find(	3L), null),
                new District(	29L,	"Lower Dibang Valley", 	this.stateService.find(	3L), null),
                new District(	30L,	"Lower Siang", 	this.stateService.find(	3L), null),
                new District(	31L,	"Lower Subansiri", 	this.stateService.find(	3L), null),
                new District(	32L,	"Namsai", 	this.stateService.find(	3L), null),
                new District(	33L,	"Pakke Kessang", 	this.stateService.find(	3L), null),
                new District(	34L,	"Papum Pare", 	this.stateService.find(	3L), null),
                new District(	35L,	"Shi Yomi", 	this.stateService.find(	3L), null),
                new District(	36L,	"Tawang", 	this.stateService.find(	3L), null),
                new District(	37L,	"Tirap", 	this.stateService.find(	3L), null),
                new District(	38L,	"Upper Siang", 	this.stateService.find(	3L), null),
                new District(	39L,	"Upper Subansiri", 	this.stateService.find(	3L), null),
                new District(	40L,	"West Kameng", 	this.stateService.find(	3L), null),
                new District(	41L,	"West Siang", 	this.stateService.find(	3L), null),
                new District(	42L,	"Bajali", 	this.stateService.find(	4L), null),
                new District(	43L,	"Baksa", 	this.stateService.find(	4L), null),
                new District(	44L,	"Barpeta", 	this.stateService.find(	4L), null),
                new District(	45L,	"Biswanath", 	this.stateService.find(	4L), null),
                new District(	46L,	"Bongaigaon", 	this.stateService.find(	4L), null),
                new District(	47L,	"Cachar", 	this.stateService.find(	4L), null),
                new District(	48L,	"Charaideo", 	this.stateService.find(	4L), null),
                new District(	49L,	"Chirang", 	this.stateService.find(	4L), null),
                new District(	50L,	"Darrang", 	this.stateService.find(	4L), null),
                new District(	51L,	"Dhemaji", 	this.stateService.find(	4L), null),
                new District(	52L,	"Dhubri", 	this.stateService.find(	4L), null),
                new District(	53L,	"Dibrugarh", 	this.stateService.find(	4L), null),
                new District(	54L,	"Dima Hasao", 	this.stateService.find(	4L), null),
                new District(	55L,	"Goalpara", 	this.stateService.find(	4L), null),
                new District(	56L,	"Golaghat", 	this.stateService.find(	4L), null),
                new District(	57L,	"Hailakandi", 	this.stateService.find(	4L), null),
                new District(	58L,	"Hojai", 	this.stateService.find(	4L), null),
                new District(	59L,	"Jorhat", 	this.stateService.find(	4L), null),
                new District(	60L,	"Kamrup", 	this.stateService.find(	4L), null),
                new District(	61L,	"Kamrup Metropolitan", 	this.stateService.find(	4L), null),
                new District(	62L,	"Karbi Anglong", 	this.stateService.find(	4L), null),
                new District(	63L,	"Karimganj", 	this.stateService.find(	4L), null),
                new District(	64L,	"Kokrajhar", 	this.stateService.find(	4L), null),
                new District(	65L,	"Lakhimpur", 	this.stateService.find(	4L), null),
                new District(	66L,	"Majuli", 	this.stateService.find(	4L), null),
                new District(	67L,	"Morigaon", 	this.stateService.find(	4L), null),
                new District(	68L,	"Nagaon", 	this.stateService.find(	4L), null),
                new District(	69L,	"Nalbari", 	this.stateService.find(	4L), null),
                new District(	70L,	"Sivasagar", 	this.stateService.find(	4L), null),
                new District(	71L,	"Sonitpur", 	this.stateService.find(	4L), null),
                new District(	72L,	"South Salmara-Mankachar", 	this.stateService.find(	4L), null),
                new District(	73L,	"Tinsukia", 	this.stateService.find(	4L), null),
                new District(	74L,	"Udalguri", 	this.stateService.find(	4L), null),
                new District(	75L,	"West Karbi Anglong", 	this.stateService.find(	4L), null),
                new District(	76L,	"Araria", 	this.stateService.find(	5L), null),
                new District(	77L,	"Arwal", 	this.stateService.find(	5L), null),
                new District(	78L,	"Aurangabad", 	this.stateService.find(	5L), null),
                new District(	79L,	"Banka", 	this.stateService.find(	5L), null),
                new District(	80L,	"Begusarai", 	this.stateService.find(	5L), null),
                new District(	81L,	"Bhagalpur", 	this.stateService.find(	5L), null),
                new District(	82L,	"Bhojpur", 	this.stateService.find(	5L), null),
                new District(	83L,	"Buxar", 	this.stateService.find(	5L), null),
                new District(	84L,	"Darbhanga", 	this.stateService.find(	5L), null),
                new District(	85L,	"East Champaran", 	this.stateService.find(	5L), null),
                new District(	86L,	"Gaya", 	this.stateService.find(	5L), null),
                new District(	87L,	"Gopalganj", 	this.stateService.find(	5L), null),
                new District(	88L,	"Jamui", 	this.stateService.find(	5L), null),
                new District(	89L,	"Jehanabad", 	this.stateService.find(	5L), null),
                new District(	90L,	"Kaimur", 	this.stateService.find(	5L), null),
                new District(	91L,	"Katihar", 	this.stateService.find(	5L), null),
                new District(	92L,	"Khagaria", 	this.stateService.find(	5L), null),
                new District(	93L,	"Kishanganj", 	this.stateService.find(	5L), null),
                new District(	94L,	"Lakhisarai", 	this.stateService.find(	5L), null),
                new District(	95L,	"Madhepura", 	this.stateService.find(	5L), null),
                new District(	96L,	"Madhubani", 	this.stateService.find(	5L), null),
                new District(	97L,	"Munger", 	this.stateService.find(	5L), null),
                new District(	98L,	"Muzaffarpur", 	this.stateService.find(	5L), null),
                new District(	99L,	"Nalanda", 	this.stateService.find(	5L), null),
                new District(	100L,	"Nawada", 	this.stateService.find(	5L), null),
                new District(	101L,	"Patna", 	this.stateService.find(	5L), null),
                new District(	102L,	"Purnia", 	this.stateService.find(	5L), null),
                new District(	103L,	"Rohtas", 	this.stateService.find(	5L), null),
                new District(	104L,	"Saharsa", 	this.stateService.find(	5L), null),
                new District(	105L,	"Samastipur", 	this.stateService.find(	5L), null),
                new District(	106L,	"Saran", 	this.stateService.find(	5L), null),
                new District(	107L,	"Sheikhpura", 	this.stateService.find(	5L), null),
                new District(	108L,	"Sheohar", 	this.stateService.find(	5L), null),
                new District(	109L,	"Sitamarhi", 	this.stateService.find(	5L), null),
                new District(	110L,	"Siwan", 	this.stateService.find(	5L), null),
                new District(	111L,	"Supaul", 	this.stateService.find(	5L), null),
                new District(	112L,	"Vaishali", 	this.stateService.find(	5L), null),
                new District(	113L,	"West Champaran", 	this.stateService.find(	5L), null),
                new District(	114L,	"Chandigarh", 	this.stateService.find(	6L), null),
                new District(	115L,	"Balod", 	this.stateService.find(	7L), null),
                new District(	116L,	"Baloda Bazar", 	this.stateService.find(	7L), null),
                new District(	117L,	"Balrampur", 	this.stateService.find(	7L), null),
                new District(	118L,	"Bastar", 	this.stateService.find(	7L), null),
                new District(	119L,	"Bemetara", 	this.stateService.find(	7L), null),
                new District(	120L,	"Bijapur", 	this.stateService.find(	7L), null),
                new District(	121L,	"Bilaspur", 	this.stateService.find(	7L), null),
                new District(	122L,	"Dantewada", 	this.stateService.find(	7L), null),
                new District(	123L,	"Dhamtari", 	this.stateService.find(	7L), null),
                new District(	124L,	"Durg", 	this.stateService.find(	7L), null),
                new District(	125L,	"Gariaband", 	this.stateService.find(	7L), null),
                new District(	126L,	"Gaurela Pendra Marwahi", 	this.stateService.find(	7L), null),
                new District(	127L,	"Janjgir Champa", 	this.stateService.find(	7L), null),
                new District(	128L,	"Jashpur", 	this.stateService.find(	7L), null),
                new District(	129L,	"Kabirdham", 	this.stateService.find(	7L), null),
                new District(	130L,	"Kanker", 	this.stateService.find(	7L), null),
                new District(	131L,	"Kondagaon", 	this.stateService.find(	7L), null),
                new District(	132L,	"Korba", 	this.stateService.find(	7L), null),
                new District(	133L,	"Koriya", 	this.stateService.find(	7L), null),
                new District(	134L,	"Mahasamund", 	this.stateService.find(	7L), null),
                new District(	135L,	"Mungeli", 	this.stateService.find(	7L), null),
                new District(	136L,	"Narayanpur", 	this.stateService.find(	7L), null),
                new District(	137L,	"Raigarh", 	this.stateService.find(	7L), null),
                new District(	138L,	"Raipur", 	this.stateService.find(	7L), null),
                new District(	139L,	"Rajnandgaon", 	this.stateService.find(	7L), null),
                new District(	140L,	"Sukma", 	this.stateService.find(	7L), null),
                new District(	141L,	"Surajpur", 	this.stateService.find(	7L), null),
                new District(	142L,	"Surguja", 	this.stateService.find(	7L), null),
                new District(	143L,	"Dadra Nagar Haveli", 	this.stateService.find(	8L), null),
                new District(	144L,	"Daman", 	this.stateService.find(	9L), null),
                new District(	145L,	"Diu", 	this.stateService.find(	9L), null),
                new District(	146L,	"Central Delhi", 	this.stateService.find(	10L), null),
                new District(	147L,	"East Delhi", 	this.stateService.find(	10L), null),
                new District(	148L,	"New Delhi", 	this.stateService.find(	10L), null),
                new District(	149L,	"North Delhi", 	this.stateService.find(	10L), null),
                new District(	150L,	"North East Delhi", 	this.stateService.find(	10L), null),
                new District(	151L,	"North West Delhi", 	this.stateService.find(	10L), null),
                new District(	152L,	"Shahdara", 	this.stateService.find(	10L), null),
                new District(	153L,	"South Delhi", 	this.stateService.find(	10L), null),
                new District(	154L,	"South East Delhi", 	this.stateService.find(	10L), null),
                new District(	155L,	"South West Delhi", 	this.stateService.find(	10L), null),
                new District(	156L,	"West Delhi", 	this.stateService.find(	10L), null),
                new District(	157L,	"North Goa", 	this.stateService.find(	11L), null),
                new District(	158L,	"South Goa", 	this.stateService.find(	11L), null),
                new District(	159L,	"Ahmedabad", 	this.stateService.find(	12L), null),
                new District(	160L,	"Amreli", 	this.stateService.find(	12L), null),
                new District(	161L,	"Anand", 	this.stateService.find(	12L), null),
                new District(	162L,	"Aravalli", 	this.stateService.find(	12L), null),
                new District(	163L,	"Banaskantha", 	this.stateService.find(	12L), null),
                new District(	164L,	"Bharuch", 	this.stateService.find(	12L), null),
                new District(	165L,	"Bhavnagar", 	this.stateService.find(	12L), null),
                new District(	166L,	"Botad", 	this.stateService.find(	12L), null),
                new District(	167L,	"Chhota Udaipur", 	this.stateService.find(	12L), null),
                new District(	168L,	"Dahod", 	this.stateService.find(	12L), null),
                new District(	169L,	"Dang", 	this.stateService.find(	12L), null),
                new District(	170L,	"Devbhoomi Dwarka", 	this.stateService.find(	12L), null),
                new District(	171L,	"Gandhinagar", 	this.stateService.find(	12L), null),
                new District(	172L,	"Gir Somnath", 	this.stateService.find(	12L), null),
                new District(	173L,	"Jamnagar", 	this.stateService.find(	12L), null),
                new District(	174L,	"Junagadh", 	this.stateService.find(	12L), null),
                new District(	175L,	"Kheda", 	this.stateService.find(	12L), null),
                new District(	176L,	"Kutch", 	this.stateService.find(	12L), null),
                new District(	177L,	"Mahisagar", 	this.stateService.find(	12L), null),
                new District(	178L,	"Mehsana", 	this.stateService.find(	12L), null),
                new District(	179L,	"Morbi", 	this.stateService.find(	12L), null),
                new District(	180L,	"Narmada", 	this.stateService.find(	12L), null),
                new District(	181L,	"Navsari", 	this.stateService.find(	12L), null),
                new District(	182L,	"Panchmahal", 	this.stateService.find(	12L), null),
                new District(	183L,	"Patan", 	this.stateService.find(	12L), null),
                new District(	184L,	"Porbandar", 	this.stateService.find(	12L), null),
                new District(	185L,	"Rajkot", 	this.stateService.find(	12L), null),
                new District(	186L,	"Sabarkantha", 	this.stateService.find(	12L), null),
                new District(	187L,	"Surat", 	this.stateService.find(	12L), null),
                new District(	188L,	"Surendranagar", 	this.stateService.find(	12L), null),
                new District(	189L,	"Tapi", 	this.stateService.find(	12L), null),
                new District(	190L,	"Vadodara", 	this.stateService.find(	12L), null),
                new District(	191L,	"Valsad", 	this.stateService.find(	12L), null),
                new District(	192L,	"Ambala", 	this.stateService.find(	13L), null),
                new District(	193L,	"Bhiwani", 	this.stateService.find(	13L), null),
                new District(	194L,	"Charkhi Dadri", 	this.stateService.find(	13L), null),
                new District(	195L,	"Faridabad", 	this.stateService.find(	13L), null),
                new District(	196L,	"Fatehabad", 	this.stateService.find(	13L), null),
                new District(	197L,	"Gurugram", 	this.stateService.find(	13L), null),
                new District(	198L,	"Hisar", 	this.stateService.find(	13L), null),
                new District(	199L,	"Jhajjar", 	this.stateService.find(	13L), null),
                new District(	200L,	"Jind", 	this.stateService.find(	13L), null),
                new District(	201L,	"Kaithal", 	this.stateService.find(	13L), null),
                new District(	202L,	"Karnal", 	this.stateService.find(	13L), null),
                new District(	203L,	"Kurukshetra", 	this.stateService.find(	13L), null),
                new District(	204L,	"Mahendragarh", 	this.stateService.find(	13L), null),
                new District(	205L,	"Mewat", 	this.stateService.find(	13L), null),
                new District(	206L,	"Palwal", 	this.stateService.find(	13L), null),
                new District(	207L,	"Panchkula", 	this.stateService.find(	13L), null),
                new District(	208L,	"Panipat", 	this.stateService.find(	13L), null),
                new District(	209L,	"Rewari", 	this.stateService.find(	13L), null),
                new District(	210L,	"Rohtak", 	this.stateService.find(	13L), null),
                new District(	211L,	"Sirsa", 	this.stateService.find(	13L), null),
                new District(	212L,	"Sonipat", 	this.stateService.find(	13L), null),
                new District(	213L,	"Yamunanagar", 	this.stateService.find(	13L), null),
                new District(	214L,	"Bilaspur", 	this.stateService.find(	14L), null),
                new District(	215L,	"Chamba", 	this.stateService.find(	14L), null),
                new District(	216L,	"Hamirpur", 	this.stateService.find(	14L), null),
                new District(	217L,	"Kangra", 	this.stateService.find(	14L), null),
                new District(	218L,	"Kinnaur", 	this.stateService.find(	14L), null),
                new District(	219L,	"Kullu", 	this.stateService.find(	14L), null),
                new District(	220L,	"Lahaul Spiti", 	this.stateService.find(	14L), null),
                new District(	221L,	"Mandi", 	this.stateService.find(	14L), null),
                new District(	222L,	"Shimla", 	this.stateService.find(	14L), null),
                new District(	223L,	"Sirmaur", 	this.stateService.find(	14L), null),
                new District(	224L,	"Solan", 	this.stateService.find(	14L), null),
                new District(	225L,	"Una", 	this.stateService.find(	14L), null),
                new District(	226L,	"Anantnag", 	this.stateService.find(	15L), null),
                new District(	227L,	"Bandipora", 	this.stateService.find(	15L), null),
                new District(	228L,	"Baramulla", 	this.stateService.find(	15L), null),
                new District(	229L,	"Budgam", 	this.stateService.find(	15L), null),
                new District(	230L,	"Doda", 	this.stateService.find(	15L), null),
                new District(	231L,	"Ganderbal", 	this.stateService.find(	15L), null),
                new District(	232L,	"Jammu", 	this.stateService.find(	15L), null),
                new District(	233L,	"Kathua", 	this.stateService.find(	15L), null),
                new District(	234L,	"Kishtwar", 	this.stateService.find(	15L), null),
                new District(	235L,	"Kulgam", 	this.stateService.find(	15L), null),
                new District(	236L,	"Kupwara", 	this.stateService.find(	15L), null),
                new District(	237L,	"Poonch", 	this.stateService.find(	15L), null),
                new District(	238L,	"Pulwama", 	this.stateService.find(	15L), null),
                new District(	239L,	"Rajouri", 	this.stateService.find(	15L), null),
                new District(	240L,	"Ramban", 	this.stateService.find(	15L), null),
                new District(	241L,	"Reasi", 	this.stateService.find(	15L), null),
                new District(	242L,	"Samba", 	this.stateService.find(	15L), null),
                new District(	243L,	"Shopian", 	this.stateService.find(	15L), null),
                new District(	244L,	"Srinagar", 	this.stateService.find(	15L), null),
                new District(	245L,	"Udhampur", 	this.stateService.find(	15L), null),
                new District(	246L,	"Bokaro", 	this.stateService.find(	16L), null),
                new District(	247L,	"Chatra", 	this.stateService.find(	16L), null),
                new District(	248L,	"Deoghar", 	this.stateService.find(	16L), null),
                new District(	249L,	"Dhanbad", 	this.stateService.find(	16L), null),
                new District(	250L,	"Dumka", 	this.stateService.find(	16L), null),
                new District(	251L,	"East Singhbhum", 	this.stateService.find(	16L), null),
                new District(	252L,	"Garhwa", 	this.stateService.find(	16L), null),
                new District(	253L,	"Giridih", 	this.stateService.find(	16L), null),
                new District(	254L,	"Godda", 	this.stateService.find(	16L), null),
                new District(	255L,	"Gumla", 	this.stateService.find(	16L), null),
                new District(	256L,	"Hazaribagh", 	this.stateService.find(	16L), null),
                new District(	257L,	"Jamtara", 	this.stateService.find(	16L), null),
                new District(	258L,	"Khunti", 	this.stateService.find(	16L), null),
                new District(	259L,	"Koderma", 	this.stateService.find(	16L), null),
                new District(	260L,	"Latehar", 	this.stateService.find(	16L), null),
                new District(	261L,	"Lohardaga", 	this.stateService.find(	16L), null),
                new District(	262L,	"Pakur", 	this.stateService.find(	16L), null),
                new District(	263L,	"Palamu", 	this.stateService.find(	16L), null),
                new District(	264L,	"Ramgarh", 	this.stateService.find(	16L), null),
                new District(	265L,	"Ranchi", 	this.stateService.find(	16L), null),
                new District(	266L,	"Sahebganj", 	this.stateService.find(	16L), null),
                new District(	267L,	"Seraikela Kharsawan", 	this.stateService.find(	16L), null),
                new District(	268L,	"Simdega", 	this.stateService.find(	16L), null),
                new District(	269L,	"West Singhbhum", 	this.stateService.find(	16L), null),
                new District(	270L,	"Bagalkot", 	this.stateService.find(	17L), null),
                new District(	271L,	"Bangalore Rural", 	this.stateService.find(	17L), null),
                new District(	272L,	"Bangalore Urban", 	this.stateService.find(	17L), null),
                new District(	273L,	"Belgaum", 	this.stateService.find(	17L), null),
                new District(	274L,	"Bellary", 	this.stateService.find(	17L), null),
                new District(	275L,	"Bidar", 	this.stateService.find(	17L), null),
                new District(	276L,	"Chamarajanagar", 	this.stateService.find(	17L), null),
                new District(	277L,	"Chikkaballapur", 	this.stateService.find(	17L), null),
                new District(	278L,	"Chikkamagaluru", 	this.stateService.find(	17L), null),
                new District(	279L,	"Chitradurga", 	this.stateService.find(	17L), null),
                new District(	280L,	"Dakshina Kannada", 	this.stateService.find(	17L), null),
                new District(	281L,	"Davanagere", 	this.stateService.find(	17L), null),
                new District(	282L,	"Dharwad", 	this.stateService.find(	17L), null),
                new District(	283L,	"Gadag", 	this.stateService.find(	17L), null),
                new District(	284L,	"Gulbarga", 	this.stateService.find(	17L), null),
                new District(	285L,	"Hassan", 	this.stateService.find(	17L), null),
                new District(	286L,	"Haveri", 	this.stateService.find(	17L), null),
                new District(	287L,	"Kodagu", 	this.stateService.find(	17L), null),
                new District(	288L,	"Kolar", 	this.stateService.find(	17L), null),
                new District(	289L,	"Koppal", 	this.stateService.find(	17L), null),
                new District(	290L,	"Mandya", 	this.stateService.find(	17L), null),
                new District(	291L,	"Mysore", 	this.stateService.find(	17L), null),
                new District(	292L,	"Raichur", 	this.stateService.find(	17L), null),
                new District(	293L,	"Ramanagara", 	this.stateService.find(	17L), null),
                new District(	294L,	"Shimoga", 	this.stateService.find(	17L), null),
                new District(	295L,	"Tumkur", 	this.stateService.find(	17L), null),
                new District(	296L,	"Udupi", 	this.stateService.find(	17L), null),
                new District(	297L,	"Uttara Kannada", 	this.stateService.find(	17L), null),
                new District(	298L,	"Vijayanagara", 	this.stateService.find(	17L), null),
                new District(	299L,	"Vijayapura ", 	this.stateService.find(	17L), null),
                new District(	300L,	"Yadgir", 	this.stateService.find(	17L), null),
                new District(	301L,	"Alappuzha", 	this.stateService.find(	18L), null),
                new District(	302L,	"Ernakulam", 	this.stateService.find(	18L), null),
                new District(	303L,	"Idukki", 	this.stateService.find(	18L), null),
                new District(	304L,	"Kannur", 	this.stateService.find(	18L), null),
                new District(	305L,	"Kasaragod", 	this.stateService.find(	18L), null),
                new District(	306L,	"Kollam", 	this.stateService.find(	18L), null),
                new District(	307L,	"Kottayam", 	this.stateService.find(	18L), null),
                new District(	308L,	"Kozhikode", 	this.stateService.find(	18L), null),
                new District(	309L,	"Malappuram", 	this.stateService.find(	18L), null),
                new District(	310L,	"Palakkad", 	this.stateService.find(	18L), null),
                new District(	311L,	"Pathanamthitta", 	this.stateService.find(	18L), null),
                new District(	312L,	"Thiruvananthapuram", 	this.stateService.find(	18L), null),
                new District(	313L,	"Thrissur", 	this.stateService.find(	18L), null),
                new District(	314L,	"Wayanad", 	this.stateService.find(	18L), null),
                new District(	315L,	"Kargil", 	this.stateService.find(	19L), null),
                new District(	316L,	"Leh", 	this.stateService.find(	19L), null),
                new District(	317L,	"Lakshadweep", 	this.stateService.find(	20L), null),
                new District(	318L,	"Agar Malwa", 	this.stateService.find(	21L), null),
                new District(	319L,	"Alirajpur", 	this.stateService.find(	21L), null),
                new District(	320L,	"Anuppur", 	this.stateService.find(	21L), null),
                new District(	321L,	"Ashoknagar", 	this.stateService.find(	21L), null),
                new District(	322L,	"Balaghat", 	this.stateService.find(	21L), null),
                new District(	323L,	"Barwani", 	this.stateService.find(	21L), null),
                new District(	324L,	"Betul", 	this.stateService.find(	21L), null),
                new District(	325L,	"Bhind", 	this.stateService.find(	21L), null),
                new District(	326L,	"Bhopal", 	this.stateService.find(	21L), null),
                new District(	327L,	"Burhanpur", 	this.stateService.find(	21L), null),
                new District(	328L,	"Chachaura", 	this.stateService.find(	21L), null),
                new District(	329L,	"Chhatarpur", 	this.stateService.find(	21L), null),
                new District(	330L,	"Chhindwara", 	this.stateService.find(	21L), null),
                new District(	331L,	"Damoh", 	this.stateService.find(	21L), null),
                new District(	332L,	"Datia", 	this.stateService.find(	21L), null),
                new District(	333L,	"Dewas", 	this.stateService.find(	21L), null),
                new District(	334L,	"Dhar", 	this.stateService.find(	21L), null),
                new District(	335L,	"Dindori", 	this.stateService.find(	21L), null),
                new District(	336L,	"Guna", 	this.stateService.find(	21L), null),
                new District(	337L,	"Gwalior", 	this.stateService.find(	21L), null),
                new District(	338L,	"Harda", 	this.stateService.find(	21L), null),
                new District(	339L,	"Hoshangabad", 	this.stateService.find(	21L), null),
                new District(	340L,	"Indore", 	this.stateService.find(	21L), null),
                new District(	341L,	"Jabalpur", 	this.stateService.find(	21L), null),
                new District(	342L,	"Jhabua", 	this.stateService.find(	21L), null),
                new District(	343L,	"Katni", 	this.stateService.find(	21L), null),
                new District(	344L,	"Khandwa", 	this.stateService.find(	21L), null),
                new District(	345L,	"Khargone", 	this.stateService.find(	21L), null),
                new District(	346L,	"Maihar", 	this.stateService.find(	21L), null),
                new District(	347L,	"Mandla", 	this.stateService.find(	21L), null),
                new District(	348L,	"Mandsaur", 	this.stateService.find(	21L), null),
                new District(	349L,	"Morena", 	this.stateService.find(	21L), null),
                new District(	350L,	"Nagda", 	this.stateService.find(	21L), null),
                new District(	351L,	"Narsinghpur", 	this.stateService.find(	21L), null),
                new District(	352L,	"Neemuch", 	this.stateService.find(	21L), null),
                new District(	353L,	"Niwari", 	this.stateService.find(	21L), null),
                new District(	354L,	"Panna", 	this.stateService.find(	21L), null),
                new District(	355L,	"Raisen", 	this.stateService.find(	21L), null),
                new District(	356L,	"Rajgarh", 	this.stateService.find(	21L), null),
                new District(	357L,	"Ratlam", 	this.stateService.find(	21L), null),
                new District(	358L,	"Rewa", 	this.stateService.find(	21L), null),
                new District(	359L,	"Sagar", 	this.stateService.find(	21L), null),
                new District(	360L,	"Satna", 	this.stateService.find(	21L), null),
                new District(	361L,	"Sehore", 	this.stateService.find(	21L), null),
                new District(	362L,	"Seoni", 	this.stateService.find(	21L), null),
                new District(	363L,	"Shahdol", 	this.stateService.find(	21L), null),
                new District(	364L,	"Shajapur", 	this.stateService.find(	21L), null),
                new District(	365L,	"Sheopur", 	this.stateService.find(	21L), null),
                new District(	366L,	"Shivpuri", 	this.stateService.find(	21L), null),
                new District(	367L,	"Sidhi", 	this.stateService.find(	21L), null),
                new District(	368L,	"Singrauli", 	this.stateService.find(	21L), null),
                new District(	369L,	"Tikamgarh", 	this.stateService.find(	21L), null),
                new District(	370L,	"Ujjain", 	this.stateService.find(	21L), null),
                new District(	371L,	"Umaria", 	this.stateService.find(	21L), null),
                new District(	372L,	"Vidisha", 	this.stateService.find(	21L), null),
                new District(	373L,	"Ahmednagar", 	this.stateService.find(	22L), null),
                new District(	374L,	"Akola", 	this.stateService.find(	22L), null),
                new District(	375L,	"Amravati", 	this.stateService.find(	22L), null),
                new District(	376L,	"Aurangabad", 	this.stateService.find(	22L), null),
                new District(	377L,	"Beed", 	this.stateService.find(	22L), null),
                new District(	378L,	"Bhandara", 	this.stateService.find(	22L), null),
                new District(	379L,	"Buldhana", 	this.stateService.find(	22L), null),
                new District(	380L,	"Chandrapur", 	this.stateService.find(	22L), null),
                new District(	381L,	"Dhule", 	this.stateService.find(	22L), null),
                new District(	382L,	"Gadchiroli", 	this.stateService.find(	22L), null),
                new District(	383L,	"Gondia", 	this.stateService.find(	22L), null),
                new District(	384L,	"Hingoli", 	this.stateService.find(	22L), null),
                new District(	385L,	"Jalgaon", 	this.stateService.find(	22L), null),
                new District(	386L,	"Jalna", 	this.stateService.find(	22L), null),
                new District(	387L,	"Kolhapur", 	this.stateService.find(	22L), null),
                new District(	388L,	"Latur", 	this.stateService.find(	22L), null),
                new District(	389L,	"Mumbai City", 	this.stateService.find(	22L), null),
                new District(	390L,	"Mumbai Suburban", 	this.stateService.find(	22L), null),
                new District(	391L,	"Nagpur", 	this.stateService.find(	22L), null),
                new District(	392L,	"Nanded", 	this.stateService.find(	22L), null),
                new District(	393L,	"Nandurbar", 	this.stateService.find(	22L), null),
                new District(	394L,	"Nashik", 	this.stateService.find(	22L), null),
                new District(	395L,	"Osmanabad", 	this.stateService.find(	22L), null),
                new District(	396L,	"Palghar", 	this.stateService.find(	22L), null),
                new District(	397L,	"Parbhani", 	this.stateService.find(	22L), null),
                new District(	398L,	"Pune", 	this.stateService.find(	22L), null),
                new District(	399L,	"Raigad", 	this.stateService.find(	22L), null),
                new District(	400L,	"Ratnagiri", 	this.stateService.find(	22L), null),
                new District(	401L,	"Sangli", 	this.stateService.find(	22L), null),
                new District(	402L,	"Satara", 	this.stateService.find(	22L), null),
                new District(	403L,	"Sindhudurg", 	this.stateService.find(	22L), null),
                new District(	404L,	"Solapur", 	this.stateService.find(	22L), null),
                new District(	405L,	"Thane", 	this.stateService.find(	22L), null),
                new District(	406L,	"Wardha", 	this.stateService.find(	22L), null),
                new District(	407L,	"Washim", 	this.stateService.find(	22L), null),
                new District(	408L,	"Yavatmal", 	this.stateService.find(	22L), null),
                new District(	409L,	"Bishnupur", 	this.stateService.find(	23L), null),
                new District(	410L,	"Chandel", 	this.stateService.find(	23L), null),
                new District(	411L,	"Churachandpur", 	this.stateService.find(	23L), null),
                new District(	412L,	"Imphal East", 	this.stateService.find(	23L), null),
                new District(	413L,	"Imphal West", 	this.stateService.find(	23L), null),
                new District(	414L,	"Jiribam", 	this.stateService.find(	23L), null),
                new District(	415L,	"Kakching", 	this.stateService.find(	23L), null),
                new District(	416L,	"Kamjong", 	this.stateService.find(	23L), null),
                new District(	417L,	"Kangpokpi", 	this.stateService.find(	23L), null),
                new District(	418L,	"Noney", 	this.stateService.find(	23L), null),
                new District(	419L,	"Pherzawl", 	this.stateService.find(	23L), null),
                new District(	420L,	"Senapati", 	this.stateService.find(	23L), null),
                new District(	421L,	"Tamenglong", 	this.stateService.find(	23L), null),
                new District(	422L,	"Tengnoupal", 	this.stateService.find(	23L), null),
                new District(	423L,	"Thoubal", 	this.stateService.find(	23L), null),
                new District(	424L,	"Ukhrul", 	this.stateService.find(	23L), null),
                new District(	425L,	"East Garo Hills", 	this.stateService.find(	24L), null),
                new District(	426L,	"East Jaintia Hills", 	this.stateService.find(	24L), null),
                new District(	427L,	"East Khasi Hills", 	this.stateService.find(	24L), null),
                new District(	428L,	"North Garo Hills", 	this.stateService.find(	24L), null),
                new District(	429L,	"Ri Bhoi", 	this.stateService.find(	24L), null),
                new District(	430L,	"South Garo Hills", 	this.stateService.find(	24L), null),
                new District(	431L,	"South West Garo Hills", 	this.stateService.find(	24L), null),
                new District(	432L,	"South West Khasi Hills", 	this.stateService.find(	24L), null),
                new District(	433L,	"West Garo Hills", 	this.stateService.find(	24L), null),
                new District(	434L,	"West Jaintia Hills", 	this.stateService.find(	24L), null),
                new District(	435L,	"West Khasi Hills", 	this.stateService.find(	24L), null),
                new District(	436L,	"Aizawl", 	this.stateService.find(	25L), null),
                new District(	437L,	"Champhai", 	this.stateService.find(	25L), null),
                new District(	438L,	"Hnahthial", 	this.stateService.find(	25L), null),
                new District(	439L,	"Kolasib", 	this.stateService.find(	25L), null),
                new District(	440L,	"Khawzawl", 	this.stateService.find(	25L), null),
                new District(	441L,	"Lawngtlai", 	this.stateService.find(	25L), null),
                new District(	442L,	"Lunglei", 	this.stateService.find(	25L), null),
                new District(	443L,	"Mamit", 	this.stateService.find(	25L), null),
                new District(	444L,	"Saiha", 	this.stateService.find(	25L), null),
                new District(	445L,	"Serchhip", 	this.stateService.find(	25L), null),
                new District(	446L,	"Saitual", 	this.stateService.find(	25L), null),
                new District(	447L,	"Dimapur", 	this.stateService.find(	26L), null),
                new District(	448L,	"Kiphire", 	this.stateService.find(	26L), null),
                new District(	449L,	"Kohima", 	this.stateService.find(	26L), null),
                new District(	450L,	"Longleng", 	this.stateService.find(	26L), null),
                new District(	451L,	"Mokokchung", 	this.stateService.find(	26L), null),
                new District(	452L,	"Mon", 	this.stateService.find(	26L), null),
                new District(	453L,	"Noklak", 	this.stateService.find(	26L), null),
                new District(	454L,	"Peren", 	this.stateService.find(	26L), null),
                new District(	455L,	"Phek", 	this.stateService.find(	26L), null),
                new District(	456L,	"Tuensang", 	this.stateService.find(	26L), null),
                new District(	457L,	"Wokha", 	this.stateService.find(	26L), null),
                new District(	458L,	"Zunheboto", 	this.stateService.find(	26L), null),
                new District(	459L,	"Angul", 	this.stateService.find(	27L), null),
                new District(	460L,	"Balangir", 	this.stateService.find(	27L), null),
                new District(	461L,	"Balasore", 	this.stateService.find(	27L), null),
                new District(	462L,	"Bargarh", 	this.stateService.find(	27L), null),
                new District(	463L,	"Bhadrak", 	this.stateService.find(	27L), null),
                new District(	464L,	"Boudh", 	this.stateService.find(	27L), null),
                new District(	465L,	"Cuttack", 	this.stateService.find(	27L), null),
                new District(	466L,	"Debagarh", 	this.stateService.find(	27L), null),
                new District(	467L,	"Dhenkanal", 	this.stateService.find(	27L), null),
                new District(	468L,	"Gajapati", 	this.stateService.find(	27L), null),
                new District(	469L,	"Ganjam", 	this.stateService.find(	27L), null),
                new District(	470L,	"Jagatsinghpur", 	this.stateService.find(	27L), null),
                new District(	471L,	"Jajpur", 	this.stateService.find(	27L), null),
                new District(	472L,	"Jharsuguda", 	this.stateService.find(	27L), null),
                new District(	473L,	"Kalahandi", 	this.stateService.find(	27L), null),
                new District(	474L,	"Kandhamal", 	this.stateService.find(	27L), null),
                new District(	475L,	"Kendrapara", 	this.stateService.find(	27L), null),
                new District(	476L,	"Kendujhar", 	this.stateService.find(	27L), null),
                new District(	477L,	"Khordha", 	this.stateService.find(	27L), null),
                new District(	478L,	"Koraput", 	this.stateService.find(	27L), null),
                new District(	479L,	"Malkangiri", 	this.stateService.find(	27L), null),
                new District(	480L,	"Mayurbhanj", 	this.stateService.find(	27L), null),
                new District(	481L,	"Nabarangpur", 	this.stateService.find(	27L), null),
                new District(	482L,	"Nayagarh", 	this.stateService.find(	27L), null),
                new District(	483L,	"Nuapada", 	this.stateService.find(	27L), null),
                new District(	484L,	"Puri", 	this.stateService.find(	27L), null),
                new District(	485L,	"Rayagada", 	this.stateService.find(	27L), null),
                new District(	486L,	"Sambalpur", 	this.stateService.find(	27L), null),
                new District(	487L,	"Subarnapur", 	this.stateService.find(	27L), null),
                new District(	488L,	"Sundergarh", 	this.stateService.find(	27L), null),
                new District(	489L,	"Karaikal", 	this.stateService.find(	28L), null),
                new District(	490L,	"Mahe", 	this.stateService.find(	28L), null),
                new District(	491L,	"Puducherry", 	this.stateService.find(	28L), null),
                new District(	492L,	"Yanam", 	this.stateService.find(	28L), null),
                new District(	493L,	"Amritsar", 	this.stateService.find(	29L), null),
                new District(	494L,	"Barnala", 	this.stateService.find(	29L), null),
                new District(	495L,	"Bathinda", 	this.stateService.find(	29L), null),
                new District(	496L,	"Faridkot", 	this.stateService.find(	29L), null),
                new District(	497L,	"Fatehgarh Sahib", 	this.stateService.find(	29L), null),
                new District(	498L,	"Fazilka", 	this.stateService.find(	29L), null),
                new District(	499L,	"Firozpur", 	this.stateService.find(	29L), null),
                new District(	500L,	"Gurdaspur", 	this.stateService.find(	29L), null),
                new District(	501L,	"Hoshiarpur", 	this.stateService.find(	29L), null),
                new District(	502L,	"Jalandhar", 	this.stateService.find(	29L), null),
                new District(	503L,	"Kapurthala", 	this.stateService.find(	29L), null),
                new District(	504L,	"Ludhiana", 	this.stateService.find(	29L), null),
                new District(	505L,	"Mansa", 	this.stateService.find(	29L), null),
                new District(	506L,	"Moga", 	this.stateService.find(	29L), null),
                new District(	507L,	"Mohali", 	this.stateService.find(	29L), null),
                new District(	508L,	"Muktsar", 	this.stateService.find(	29L), null),
                new District(	509L,	"Pathankot", 	this.stateService.find(	29L), null),
                new District(	510L,	"Patiala", 	this.stateService.find(	29L), null),
                new District(	511L,	"Rupnagar", 	this.stateService.find(	29L), null),
                new District(	512L,	"Sangrur", 	this.stateService.find(	29L), null),
                new District(	513L,	"Shaheed Bhagat Singh Nagar", 	this.stateService.find(	29L), null),
                new District(	514L,	"Tarn Taran", 	this.stateService.find(	29L), null),
                new District(	515L,	"Ajmer", 	this.stateService.find(	30L), null),
                new District(	516L,	"Alwar", 	this.stateService.find(	30L), null),
                new District(	517L,	"Banswara", 	this.stateService.find(	30L), null),
                new District(	518L,	"Baran", 	this.stateService.find(	30L), null),
                new District(	519L,	"Barmer", 	this.stateService.find(	30L), null),
                new District(	520L,	"Bharatpur", 	this.stateService.find(	30L), null),
                new District(	521L,	"Bhilwara", 	this.stateService.find(	30L), null),
                new District(	522L,	"Bikaner", 	this.stateService.find(	30L), null),
                new District(	523L,	"Bundi", 	this.stateService.find(	30L), null),
                new District(	524L,	"Chittorgarh", 	this.stateService.find(	30L), null),
                new District(	525L,	"Churu", 	this.stateService.find(	30L), null),
                new District(	526L,	"Dausa", 	this.stateService.find(	30L), null),
                new District(	527L,	"Dholpur", 	this.stateService.find(	30L), null),
                new District(	528L,	"Dungarpur", 	this.stateService.find(	30L), null),
                new District(	529L,	"Hanumangarh", 	this.stateService.find(	30L), null),
                new District(	530L,	"Jaipur", 	this.stateService.find(	30L), null),
                new District(	531L,	"Jaisalmer", 	this.stateService.find(	30L), null),
                new District(	532L,	"Jalore", 	this.stateService.find(	30L), null),
                new District(	533L,	"Jhalawar", 	this.stateService.find(	30L), null),
                new District(	534L,	"Jhunjhunu", 	this.stateService.find(	30L), null),
                new District(	535L,	"Jodhpur", 	this.stateService.find(	30L), null),
                new District(	536L,	"Karauli", 	this.stateService.find(	30L), null),
                new District(	537L,	"Kota", 	this.stateService.find(	30L), null),
                new District(	538L,	"Nagaur", 	this.stateService.find(	30L), null),
                new District(	539L,	"Pali", 	this.stateService.find(	30L), null),
                new District(	540L,	"Pratapgarh", 	this.stateService.find(	30L), null),
                new District(	541L,	"Rajsamand", 	this.stateService.find(	30L), null),
                new District(	542L,	"Sawai Madhopur", 	this.stateService.find(	30L), null),
                new District(	543L,	"Sikar", 	this.stateService.find(	30L), null),
                new District(	544L,	"Sirohi", 	this.stateService.find(	30L), null),
                new District(	545L,	"Sri Ganganagar", 	this.stateService.find(	30L), null),
                new District(	546L,	"Tonk", 	this.stateService.find(	30L), null),
                new District(	547L,	"Udaipur", 	this.stateService.find(	30L), null),
                new District(	548L,	"East Sikkim", 	this.stateService.find(	31L), null),
                new District(	549L,	"North Sikkim", 	this.stateService.find(	31L), null),
                new District(	550L,	"South Sikkim", 	this.stateService.find(	31L), null),
                new District(	551L,	"West Sikkim", 	this.stateService.find(	31L), null),
                new District(	552L,	"Ariyalur", 	this.stateService.find(	32L), null),
                new District(	553L,	"Chengalpattu", 	this.stateService.find(	32L), null),
                new District(	554L,	"Chennai", 	this.stateService.find(	32L), null),
                new District(	555L,	"Coimbatore", 	this.stateService.find(	32L), null),
                new District(	556L,	"Cuddalore", 	this.stateService.find(	32L), null),
                new District(	557L,	"Dharmapuri", 	this.stateService.find(	32L), null),
                new District(	558L,	"Dindigul", 	this.stateService.find(	32L), null),
                new District(	559L,	"Erode", 	this.stateService.find(	32L), null),
                new District(	560L,	"Kallakurichi", 	this.stateService.find(	32L), null),
                new District(	561L,	"Kanchipuram", 	this.stateService.find(	32L), null),
                new District(	562L,	"Kanyakumari", 	this.stateService.find(	32L), null),
                new District(	563L,	"Karur", 	this.stateService.find(	32L), null),
                new District(	564L,	"Krishnagiri", 	this.stateService.find(	32L), null),
                new District(	565L,	"Madurai", 	this.stateService.find(	32L), null),
                new District(	566L,	"Mayiladuthurai ", 	this.stateService.find(	32L), null),
                new District(	567L,	"Nagapattinam", 	this.stateService.find(	32L), null),
                new District(	568L,	"Namakkal", 	this.stateService.find(	32L), null),
                new District(	569L,	"Nilgiris", 	this.stateService.find(	32L), null),
                new District(	570L,	"Perambalur", 	this.stateService.find(	32L), null),
                new District(	571L,	"Pudukkottai", 	this.stateService.find(	32L), null),
                new District(	572L,	"Ramanathapuram", 	this.stateService.find(	32L), null),
                new District(	573L,	"Ranipet", 	this.stateService.find(	32L), null),
                new District(	574L,	"Salem", 	this.stateService.find(	32L), null),
                new District(	575L,	"Sivaganga", 	this.stateService.find(	32L), null),
                new District(	576L,	"Tenkasi", 	this.stateService.find(	32L), null),
                new District(	577L,	"Thanjavur", 	this.stateService.find(	32L), null),
                new District(	578L,	"Theni", 	this.stateService.find(	32L), null),
                new District(	579L,	"Thoothukudi", 	this.stateService.find(	32L), null),
                new District(	580L,	"Tiruchirappalli", 	this.stateService.find(	32L), null),
                new District(	581L,	"Tirunelveli", 	this.stateService.find(	32L), null),
                new District(	582L,	"Tirupattur", 	this.stateService.find(	32L), null),
                new District(	583L,	"Tiruppur", 	this.stateService.find(	32L), null),
                new District(	584L,	"Tiruvallur", 	this.stateService.find(	32L), null),
                new District(	585L,	"Tiruvannamalai", 	this.stateService.find(	32L), null),
                new District(	586L,	"Tiruvarur", 	this.stateService.find(	32L), null),
                new District(	587L,	"Vellore", 	this.stateService.find(	32L), null),
                new District(	588L,	"Viluppuram", 	this.stateService.find(	32L), null),
                new District(	589L,	"Virudhunagar", 	this.stateService.find(	32L), null),
                new District(	590L,	"Adilabad", 	this.stateService.find(	33L), null),
                new District(	591L,	"Bhadradri Kothagudem", 	this.stateService.find(	33L), null),
                new District(	592L,	"Hyderabad", 	this.stateService.find(	33L), null),
                new District(	593L,	"Jagtial", 	this.stateService.find(	33L), null),
                new District(	594L,	"Jangaon", 	this.stateService.find(	33L), null),
                new District(	595L,	"Jayashankar", 	this.stateService.find(	33L), null),
                new District(	596L,	"Jogulamba", 	this.stateService.find(	33L), null),
                new District(	597L,	"Kamareddy", 	this.stateService.find(	33L), null),
                new District(	598L,	"Karimnagar", 	this.stateService.find(	33L), null),
                new District(	599L,	"Khammam", 	this.stateService.find(	33L), null),
                new District(	600L,	"Komaram Bheem", 	this.stateService.find(	33L), null),
                new District(	601L,	"Mahabubabad", 	this.stateService.find(	33L), null),
                new District(	602L,	"Mahbubnagar", 	this.stateService.find(	33L), null),
                new District(	603L,	"Mancherial", 	this.stateService.find(	33L), null),
                new District(	604L,	"Medak", 	this.stateService.find(	33L), null),
                new District(	605L,	"Medchal", 	this.stateService.find(	33L), null),
                new District(	606L,	"Mulugu", 	this.stateService.find(	33L), null),
                new District(	607L,	"Nagarkurnool", 	this.stateService.find(	33L), null),
                new District(	608L,	"Nalgonda", 	this.stateService.find(	33L), null),
                new District(	609L,	"Narayanpet", 	this.stateService.find(	33L), null),
                new District(	610L,	"Nirmal", 	this.stateService.find(	33L), null),
                new District(	611L,	"Nizamabad", 	this.stateService.find(	33L), null),
                new District(	612L,	"Peddapalli", 	this.stateService.find(	33L), null),
                new District(	613L,	"Rajanna Sircilla", 	this.stateService.find(	33L), null),
                new District(	614L,	"Ranga Reddy", 	this.stateService.find(	33L), null),
                new District(	615L,	"Sangareddy", 	this.stateService.find(	33L), null),
                new District(	616L,	"Siddipet", 	this.stateService.find(	33L), null),
                new District(	617L,	"Suryapet", 	this.stateService.find(	33L), null),
                new District(	618L,	"Vikarabad", 	this.stateService.find(	33L), null),
                new District(	619L,	"Wanaparthy", 	this.stateService.find(	33L), null),
                new District(	620L,	"Warangal Rural", 	this.stateService.find(	33L), null),
                new District(	621L,	"Warangal Urban", 	this.stateService.find(	33L), null),
                new District(	622L,	"Yadadri Bhuvanagiri", 	this.stateService.find(	33L), null),
                new District(	623L,	"Dhalai", 	this.stateService.find(	34L), null),
                new District(	624L,	"Gomati", 	this.stateService.find(	34L), null),
                new District(	625L,	"Khowai", 	this.stateService.find(	34L), null),
                new District(	626L,	"North Tripura", 	this.stateService.find(	34L), null),
                new District(	627L,	"Sepahijala", 	this.stateService.find(	34L), null),
                new District(	628L,	"South Tripura", 	this.stateService.find(	34L), null),
                new District(	629L,	"Unakoti", 	this.stateService.find(	34L), null),
                new District(	630L,	"West Tripura", 	this.stateService.find(	34L), null),
                new District(	631L,	"Agra", 	this.stateService.find(	35L), null),
                new District(	632L,	"Aligarh", 	this.stateService.find(	35L), null),
                new District(	633L,	"Ambedkar Nagar", 	this.stateService.find(	35L), null),
                new District(	634L,	"Amethi", 	this.stateService.find(	35L), null),
                new District(	635L,	"Amroha", 	this.stateService.find(	35L), null),
                new District(	636L,	"Auraiya", 	this.stateService.find(	35L), null),
                new District(	637L,	"Ayodhya", 	this.stateService.find(	35L), null),
                new District(	638L,	"Azamgarh", 	this.stateService.find(	35L), null),
                new District(	639L,	"Baghpat", 	this.stateService.find(	35L), null),
                new District(	640L,	"Bahraich", 	this.stateService.find(	35L), null),
                new District(	641L,	"Ballia", 	this.stateService.find(	35L), null),
                new District(	642L,	"Balrampur", 	this.stateService.find(	35L), null),
                new District(	643L,	"Banda", 	this.stateService.find(	35L), null),
                new District(	644L,	"Barabanki", 	this.stateService.find(	35L), null),
                new District(	645L,	"Bareilly", 	this.stateService.find(	35L), null),
                new District(	646L,	"Basti", 	this.stateService.find(	35L), null),
                new District(	647L,	"Bhadohi", 	this.stateService.find(	35L), null),
                new District(	648L,	"Bijnor", 	this.stateService.find(	35L), null),
                new District(	649L,	"Budaun", 	this.stateService.find(	35L), null),
                new District(	650L,	"Bulandshahr", 	this.stateService.find(	35L), null),
                new District(	651L,	"Chandauli", 	this.stateService.find(	35L), null),
                new District(	652L,	"Chitrakoot", 	this.stateService.find(	35L), null),
                new District(	653L,	"Deoria", 	this.stateService.find(	35L), null),
                new District(	654L,	"Etah", 	this.stateService.find(	35L), null),
                new District(	655L,	"Etawah", 	this.stateService.find(	35L), null),
                new District(	656L,	"Farrukhabad", 	this.stateService.find(	35L), null),
                new District(	657L,	"Fatehpur", 	this.stateService.find(	35L), null),
                new District(	658L,	"Firozabad", 	this.stateService.find(	35L), null),
                new District(	659L,	"Gautam Buddha Nagar", 	this.stateService.find(	35L), null),
                new District(	660L,	"Ghaziabad", 	this.stateService.find(	35L), null),
                new District(	661L,	"Ghazipur", 	this.stateService.find(	35L), null),
                new District(	662L,	"Gonda", 	this.stateService.find(	35L), null),
                new District(	663L,	"Gorakhpur", 	this.stateService.find(	35L), null),
                new District(	664L,	"Hamirpur", 	this.stateService.find(	35L), null),
                new District(	665L,	"Hapur", 	this.stateService.find(	35L), null),
                new District(	666L,	"Hardoi", 	this.stateService.find(	35L), null),
                new District(	667L,	"Hathras", 	this.stateService.find(	35L), null),
                new District(	668L,	"Jalaun", 	this.stateService.find(	35L), null),
                new District(	669L,	"Jaunpur", 	this.stateService.find(	35L), null),
                new District(	670L,	"Jhansi", 	this.stateService.find(	35L), null),
                new District(	671L,	"Kannauj", 	this.stateService.find(	35L), null),
                new District(	672L,	"Kanpur Dehat", 	this.stateService.find(	35L), null),
                new District(	673L,	"Kanpur Nagar", 	this.stateService.find(	35L), null),
                new District(	674L,	"Kasganj", 	this.stateService.find(	35L), null),
                new District(	675L,	"Kaushambi", 	this.stateService.find(	35L), null),
                new District(	676L,	"Kheri", 	this.stateService.find(	35L), null),
                new District(	677L,	"Kushinagar", 	this.stateService.find(	35L), null),
                new District(	678L,	"Lalitpur", 	this.stateService.find(	35L), null),
                new District(	679L,	"Lucknow", 	this.stateService.find(	35L), null),
                new District(	680L,	"Maharajganj", 	this.stateService.find(	35L), null),
                new District(	681L,	"Mahoba", 	this.stateService.find(	35L), null),
                new District(	682L,	"Mainpuri", 	this.stateService.find(	35L), null),
                new District(	683L,	"Mathura", 	this.stateService.find(	35L), null),
                new District(	684L,	"Mau", 	this.stateService.find(	35L), null),
                new District(	685L,	"Meerut", 	this.stateService.find(	35L), null),
                new District(	686L,	"Mirzapur", 	this.stateService.find(	35L), null),
                new District(	687L,	"Moradabad", 	this.stateService.find(	35L), null),
                new District(	688L,	"Muzaffarnagar", 	this.stateService.find(	35L), null),
                new District(	689L,	"Pilibhit", 	this.stateService.find(	35L), null),
                new District(	690L,	"Pratapgarh", 	this.stateService.find(	35L), null),
                new District(	691L,	"Prayagraj", 	this.stateService.find(	35L), null),
                new District(	692L,	"Raebareli", 	this.stateService.find(	35L), null),
                new District(	693L,	"Rampur", 	this.stateService.find(	35L), null),
                new District(	694L,	"Saharanpur", 	this.stateService.find(	35L), null),
                new District(	695L,	"Sambhal", 	this.stateService.find(	35L), null),
                new District(	696L,	"Sant Kabir Nagar", 	this.stateService.find(	35L), null),
                new District(	697L,	"Shahjahanpur", 	this.stateService.find(	35L), null),
                new District(	698L,	"Shamli", 	this.stateService.find(	35L), null),
                new District(	699L,	"Shravasti", 	this.stateService.find(	35L), null),
                new District(	700L,	"Siddharthnagar", 	this.stateService.find(	35L), null),
                new District(	701L,	"Sitapur", 	this.stateService.find(	35L), null),
                new District(	702L,	"Sonbhadra", 	this.stateService.find(	35L), null),
                new District(	703L,	"Sultanpur", 	this.stateService.find(	35L), null),
                new District(	704L,	"Unnao", 	this.stateService.find(	35L), null),
                new District(	705L,	"Varanasi", 	this.stateService.find(	35L), null),
                new District(	706L,	"Almora", 	this.stateService.find(	36L), null),
                new District(	707L,	"Bageshwar", 	this.stateService.find(	36L), null),
                new District(	708L,	"Chamoli", 	this.stateService.find(	36L), null),
                new District(	709L,	"Champawat", 	this.stateService.find(	36L), null),
                new District(	710L,	"Dehradun", 	this.stateService.find(	36L), null),
                new District(	711L,	"Haridwar", 	this.stateService.find(	36L), null),
                new District(	712L,	"Nainital", 	this.stateService.find(	36L), null),
                new District(	713L,	"Pauri", 	this.stateService.find(	36L), null),
                new District(	714L,	"Pithoragarh", 	this.stateService.find(	36L), null),
                new District(	715L,	"Rudraprayag", 	this.stateService.find(	36L), null),
                new District(	716L,	"Tehri", 	this.stateService.find(	36L), null),
                new District(	717L,	"Udham Singh Nagar", 	this.stateService.find(	36L), null),
                new District(	718L,	"Uttarkashi", 	this.stateService.find(	36L), null),
                new District(	719L,	"Alipurduar", 	this.stateService.find(	37L), null),
                new District(	720L,	"Bankura", 	this.stateService.find(	37L), null),
                new District(	721L,	"Birbhum", 	this.stateService.find(	37L), null),
                new District(	722L,	"Cooch Behar", 	this.stateService.find(	37L), null),
                new District(	723L,	"Dakshin Dinajpur", 	this.stateService.find(	37L), null),
                new District(	724L,	"Darjeeling", 	this.stateService.find(	37L), null),
                new District(	725L,	"Hooghly", 	this.stateService.find(	37L), null),
                new District(	726L,	"Howrah", 	this.stateService.find(	37L), null),
                new District(	727L,	"Jalpaiguri", 	this.stateService.find(	37L), null),
                new District(	728L,	"Jhargram", 	this.stateService.find(	37L), null),
                new District(	729L,	"Kalimpong", 	this.stateService.find(	37L), null),
                new District(	730L,	"Kolkata", 	this.stateService.find(	37L), null),
                new District(	731L,	"Malda", 	this.stateService.find(	37L), null),
                new District(	732L,	"Murshidabad", 	this.stateService.find(	37L), null),
                new District(	733L,	"Nadia", 	this.stateService.find(	37L), null),
                new District(	734L,	"North 24 Parganas", 	this.stateService.find(	37L), null),
                new District(	735L,	"Paschim Bardhaman", 	this.stateService.find(	37L), null),
                new District(	736L,	"Paschim Medinipur", 	this.stateService.find(	37L), null),
                new District(	737L,	"Purba Bardhaman", 	this.stateService.find(	37L), null),
                new District(	738L,	"Purba Medinipur", 	this.stateService.find(	37L), null),
                new District(	739L,	"Purulia", 	this.stateService.find(	37L), null),
                new District(	740L,	"South 24 Parganas", 	this.stateService.find(	37L), null),
                new District(	741L,	"Uttar Dinajpur", 	this.stateService.find(	37L), null)
        );

        districts.forEach(district -> {
            try{
                this.districtService.add(district);
            }catch(ServiceException se){
//                System.out.println(se.getMessage());
            }
        });
    }

}
