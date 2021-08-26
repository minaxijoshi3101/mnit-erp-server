package com.mnit.erp.academic.course.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.mnit.erp.academic.course.model.CourseFeedback;

@Repository
public interface CourseFeedbackRepository extends JpaRepository<CourseFeedback,Long> {
	@Query("select c from CourseFeedback c where (c.program = :#{#cp.program} or :#{#cp.program} is null) And "
			 + " (c.degree = :#{#cp.degree} or :#{#cp.degree} is null) And "
			 + " (c.department = :#{#cp.department} or :#{#cp.department} is null) And "
			 + " (c.student = :#{#cp.student} or :#{#cp.student} is null) And "
			 + " (c.semester = :#{#cp.semester} or :#{#cp.semester} is null) And "
			 + " (c.academicYear = :#{#cp.academicYear} or :#{#cp.academicYear} is null)")
		List<CourseFeedback> findAsParam(@Param("cp") CourseFeedback cp);
}
