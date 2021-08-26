package com.mnit.erp.academic.course.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.mnit.erp.academic.course.model.CourseProposal;

@Repository
public interface CourseProposalRepository extends JpaRepository<CourseProposal,Long> {
	@Query("select c from CourseProposal c where (c.program = :#{#cp.program} or :#{#cp.program} is null) And "
		 + " (c.degree = :#{#cp.degree} or :#{#cp.degree} is null) And "
		 + " (c.department = :#{#cp.department} or :#{#cp.department} is null) And "
		 + " (c.semester = :#{#cp.semester} or :#{#cp.semester} is null) And "
		 + " (c.courseType = :#{#cp.courseType} or :#{#cp.courseType} is null) And "
		 + " (c.deptSup = :#{#cp.deptSup} or :#{#cp.deptSup} is null) And "
		 + " (c.dugc = :#{#cp.dugc} or :#{#cp.dugc} is null) And "
		 + " (c.senate = :#{#cp.senate} or :#{#cp.senate} is null) And "
		 + " (c.deptSupStatus = :#{#cp.deptSupStatus} or :#{#cp.deptSupStatus} is null) And "
		 + " (c.dugcStatus = :#{#cp.dugcStatus} or :#{#cp.dugcStatus} is null) And "
		 + " (c.senateStatus = :#{#cp.senateStatus} or :#{#cp.senateStatus} is null) " )
	List<CourseProposal> findAsParam(@Param("cp") CourseProposal cp);
}
