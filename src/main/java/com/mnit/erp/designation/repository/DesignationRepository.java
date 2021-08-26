package com.mnit.erp.designation.repository;

import com.mnit.erp.designation.model.Designation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DesignationRepository extends JpaRepository<Designation, Long> {
}
