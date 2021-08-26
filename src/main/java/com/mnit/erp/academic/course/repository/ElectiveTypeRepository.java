package com.mnit.erp.academic.course.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mnit.erp.academic.course.model.ElectiveType;
/**
 * Contains Elective type repository
 *
 * @author: Tejpal Singh
 * @date: 22 May, 2021
 */
@Repository
public interface ElectiveTypeRepository extends JpaRepository<ElectiveType,Long> {

}
