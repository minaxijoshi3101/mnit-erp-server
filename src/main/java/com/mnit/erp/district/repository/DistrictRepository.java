package com.mnit.erp.district.repository;

import com.mnit.erp.district.model.District;
import com.mnit.erp.state.model.State;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DistrictRepository extends JpaRepository<District, Long> {
    District findByName(String name);

    List<District> findByState(State state);
}
