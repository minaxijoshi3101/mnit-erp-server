package com.mnit.erp.facility.service;

import com.mnit.erp.facility.model.Facility;

import java.util.List;

/**
 * Contains Facility Service
 *
 * @author: Tejpal Singh
 * @declaration: add/update/find/findAll
 * @date: 16 June, 2021
 */
public interface FacilityService {
    Facility add(Facility facility);
    Facility update(Facility facility);
    Facility find(Long facilityId);
    List<Facility> findAll();
}
