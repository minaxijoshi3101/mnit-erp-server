package com.mnit.erp.hr.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mnit.erp.hr.model.Kra;

@Repository
public interface KraRepository extends JpaRepository<Kra, Long> {

}
