package com.mnit.erp.academic.admissionType.repository;

import com.mnit.erp.academic.admissionType.model.AdmissionType;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdmissionTypeRepository extends JpaRepository<AdmissionType, Long> {

    AdmissionType findByAbbreviation(String abbreviation);
    List<AdmissionType> findByNameContaining(String searchString);
    AdmissionType findByName(String searchString);
}
