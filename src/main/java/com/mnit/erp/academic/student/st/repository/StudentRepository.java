package com.mnit.erp.academic.student.st.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.mnit.erp.academic.student.st.model.Student;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
    @Query(
            nativeQuery = true,
            value = "SELECT max(sequence) FROM Student WHERE admissionYear= ?1 AND program_id= ?2  "
    )
    Long maxSequenceByAdmissionYearAndProgram(Long admissionYear, Long programId);
	Student findByStudentId(String studentId);
	Student findByPreAdmissionData_Id(Long preAdmissionId);
	
	Page<Student> findAllByOrderByIdDesc(Pageable pageable);
	Page<Student> findAll(Pageable pageable);
	List<Student> findByStudentIdNull();
	List<Student> findByStudentIdNotNull();
	
	@Query("select c from Student c where (c.category = :#{#cp.category} or :#{#cp.category} is null) And "
			 + " (c.citizenShip = :#{#cp.citizenShip} or :#{#cp.citizenShip} is null) And "
			 + " (c.familyIncomeCategory = :#{#cp.familyIncomeCategory} or :#{#cp.familyIncomeCategory} is null) And "
			 + " (c.admittedCategory = :#{#cp.admittedCategory} or :#{#cp.admittedCategory} is null) And "
			 + " (c.program = :#{#cp.program} or :#{#cp.program} is null) And "
			 + " (c.department = :#{#cp.department} or :#{#cp.department} is null) And "
			 + " (c.specialization = :#{#cp.specialization} or :#{#cp.specialization} is null) And "
			 + " (c.admissionType = :#{#cp.admissionType} or :#{#cp.admissionType} is null) And "
			 + " (c.selectionBoard = :#{#cp.selectionBoard} or :#{#cp.selectionBoard} is null) And "
			 + " (c.selectionBoardType = :#{#cp.selectionBoardType} or :#{#cp.selectionBoardType} is null) And "
			 + " (c.entranceExam = :#{#cp.entranceExam} or :#{#cp.entranceExam} is null) And "
			 + " (c.semester = :#{#cp.semester} or :#{#cp.semester} is null) And "
			 + " (c.admissionYear = :#{#cp.admissionYear} or :#{#cp.admissionYear} is null) And "
			 + " (c.studentId = :#{#cp.studentId} or :#{#cp.studentId} is null) And "
			 + " (c.batch = :#{#cp.batch} or :#{#cp.batch} is null) And "
			 + " (c.name = :#{#cp.name} or :#{#cp.name} is null) And "
			 + " (c.emailPrimary = :#{#cp.emailPrimary} or :#{#cp.emailPrimary} is null) And "
			 + " (c.emailSecondary = :#{#cp.emailSecondary} or :#{#cp.emailSecondary} is null) And "
			 + " (c.mobile = :#{#cp.mobile} or :#{#cp.mobile} is null) And "
			 + " (c.gender = :#{#cp.gender} or :#{#cp.gender} is null) And "
			 + " (c.bloodGroup = :#{#cp.bloodGroup} or :#{#cp.bloodGroup} is null) And "
			 + " (c.dob = :#{#cp.dob} or :#{#cp.dob} is null) And "
			 + " (c.sponsoredBy = :#{#cp.sponsoredBy} or :#{#cp.sponsoredBy} is null) And "
			 + " (c.admissionDate = :#{#cp.admissionDate} or :#{#cp.admissionDate} is null) And "
			 + " (c.degree = :#{#cp.degree} or :#{#cp.degree} is null) And "
			 + " (c.branch = :#{#cp.branch} or :#{#cp.branch} is null) " )
	List<Student> findAsParam(@Param("cp") Student cp);

}
