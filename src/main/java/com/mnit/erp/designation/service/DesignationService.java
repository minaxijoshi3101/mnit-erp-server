package com.mnit.erp.designation.service;

import com.mnit.erp.designation.model.Designation;

import java.util.List;

public interface DesignationService {
    Designation add(Designation designation);
    Designation update(Designation designation);
    Designation find(Long id);
    List<Designation> findAll();
}
