package com.mnit.erp.location.repository;

import com.mnit.erp.location.model.Location;
import com.mnit.erp.location.model.LocationType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Contains Location repository
 *
 * @author: Tejpal Singh
 * @date: 16 June, 2021
 */
@Repository
public interface LocationRepository extends JpaRepository<Location, Long> {
    Location findByNameAndLocationType(String name, LocationType locationType);
}
