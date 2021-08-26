package com.mnit.erp.academic.course.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.mnit.erp.academic.course.model.CourseRegistrationDetail;

@Repository
public interface CourseRegistrationDetailRepository extends JpaRepository<CourseRegistrationDetail,Long> {
	List<CourseRegistrationDetail> findByCourseRegistration_Id(Long id);
	@Query("select c from CourseRegistrationDetail c where (c.courseRegistration = :#{#cp.courseRegistration} or :#{#cp.courseRegistration} is null) And "
			 + " (c.id = :#{#cp.id} or :#{#cp.id} is null) And "
			 + " (c.courseScheme = :#{#cp.courseScheme} or :#{#cp.courseScheme} is null) And "
			 + " (c.courseState = :#{#cp.courseState} or :#{#cp.courseState} is null) And "
			 + " (c.progAdvUser = :#{#cp.progAdvUser} or :#{#cp.progAdvUser} is null) And "
			 + " (c.progAdvStatus = :#{#cp.progAdvStatus} or :#{#cp.progAdvStatus} is null) And "
			 + " (c.deanDugcUser = :#{#cp.deanDugcUser} or :#{#cp.deanDugcUser} is null) And "
			 + " (c.deanDugcStatus = :#{#cp.deanDugcStatus} or :#{#cp.deanDugcStatus} is null) And "
			 + " (c.status = :#{#cp.status} or :#{#cp.status} is null) " )
	List<CourseRegistrationDetail> findAsParam(@Param("cp") CourseRegistrationDetail cp);
}
