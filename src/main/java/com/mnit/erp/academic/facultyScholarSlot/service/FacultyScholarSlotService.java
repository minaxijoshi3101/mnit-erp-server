package com.mnit.erp.academic.facultyScholarSlot.service;

import com.mnit.erp.academic.facultyScholarSlot.model.FacultyScholarSlot;

import java.util.List;

/**
 * Contains faculty scholar slot Service
 *
 * @author: Tejpal Singh
 * @declaration: add/update/find/findAll
 * @date: 27 July, 2021
 */
public interface FacultyScholarSlotService {
    FacultyScholarSlot add(FacultyScholarSlot facultyScholarSlot);
    FacultyScholarSlot update(FacultyScholarSlot facultyScholarSlot);
    FacultyScholarSlot find(Long facultyScholarSlotId);
    List<FacultyScholarSlot> findAll();
    FacultyScholarSlot approveOrReject(FacultyScholarSlot facultyScholarSlot);
}
