package com.mnit.erp.academic.course.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.mnit.erp.academic.course.model.CourseScheme;

@Repository
public interface CourseSchemeRepository extends JpaRepository<CourseScheme,Long> {
	@Query("select c from CourseScheme c where (c.course = :#{#cp.course} or :#{#cp.course} is null) And "
		 + " (c.degree = :#{#cp.degree} or :#{#cp.degree} is null) And "
		 + " (c.department = :#{#cp.department} or :#{#cp.department} is null) And "
		 + " (c.semester = :#{#cp.semester} or :#{#cp.semester} is null) And "
		 + " (c.electiveType = :#{#cp.electiveType} or :#{#cp.electiveType} is null) And "
		 + " (c.academicYear = :#{#cp.academicYear} or :#{#cp.academicYear} is null) And "
		 + " (c.coordinator = :#{#cp.coordinator} or :#{#cp.coordinator} is null) " )
	List<CourseScheme> findAsParam(@Param("cp") CourseScheme cp);
}
