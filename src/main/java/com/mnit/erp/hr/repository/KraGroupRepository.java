package com.mnit.erp.hr.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mnit.erp.hr.model.KraGroup;

@Repository
public interface KraGroupRepository extends JpaRepository<KraGroup, Long> {
}
