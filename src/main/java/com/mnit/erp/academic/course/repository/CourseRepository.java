package com.mnit.erp.academic.course.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mnit.erp.academic.course.model.Course;

@Repository
public interface CourseRepository extends JpaRepository<Course,Long> {

}
