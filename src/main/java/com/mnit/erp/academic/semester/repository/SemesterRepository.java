package com.mnit.erp.academic.semester.repository;

import com.mnit.erp.academic.semester.model.Semester;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SemesterRepository extends JpaRepository<Semester, Long> {
    Semester findBySemester(Long semester);

    List<Semester> findByType(String type);
}
