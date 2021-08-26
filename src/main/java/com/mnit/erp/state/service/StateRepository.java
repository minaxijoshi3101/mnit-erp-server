package com.mnit.erp.state.service;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mnit.erp.country.model.Country;
import com.mnit.erp.state.model.State;

@Repository
public interface StateRepository extends JpaRepository<State, Long> {
    List<State> findByCountry(Country country);

    State findByName(String name);
}
