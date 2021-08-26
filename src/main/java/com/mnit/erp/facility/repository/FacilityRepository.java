package com.mnit.erp.facility.repository;

import com.mnit.erp.facility.model.Facility;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Contains Facility repository
 *
 * @author: Tejpal Singh
 * @date: 16 June, 2021
 */
@Repository
public interface FacilityRepository extends JpaRepository<Facility, Long> {
    Facility findByName(String name);
}
