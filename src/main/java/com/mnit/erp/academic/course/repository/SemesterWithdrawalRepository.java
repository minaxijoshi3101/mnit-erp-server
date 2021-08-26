package com.mnit.erp.academic.course.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.mnit.erp.academic.course.model.SemesterWithdrawal;

@Repository
public interface SemesterWithdrawalRepository extends JpaRepository<SemesterWithdrawal,Long> {
	@Query("select c from SemesterWithdrawal c where (c.program = :#{#cp.program} or :#{#cp.program} is null) And "
			 + " (c.degree = :#{#cp.degree} or :#{#cp.degree} is null) And "
			 + " (c.department = :#{#cp.department} or :#{#cp.department} is null) And "
			 + " (c.semester = :#{#cp.semester} or :#{#cp.semester} is null) And "
			 + " (c.student = :#{#cp.student} or :#{#cp.student} is null) And "
			 + " (c.status = :#{#cp.status} or :#{#cp.status} is null) And "
			 + " (c.academicYear = :#{#cp.academicYear} or :#{#cp.academicYear} is null)")
		List<SemesterWithdrawal> findAsParam(@Param("cp") SemesterWithdrawal cp);
}
