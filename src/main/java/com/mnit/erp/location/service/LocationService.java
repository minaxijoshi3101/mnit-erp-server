package com.mnit.erp.location.service;

import com.mnit.erp.location.model.Location;

import java.util.List;

/**
 * Contains Location Service
 *
 * @author: Tejpal Singh
 * @declaration: add/update/find/findAll
 * @date: 16 June, 2021
 */
public interface LocationService {
    Location add(Location location);
    Location update(Location location);
    Location find(Long locationId);
    List<Location> findAll();
}
