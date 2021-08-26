package com.mnit.erp.academic.facultyScholarSlot.repository;

import com.mnit.erp.academic.facultyScholarSlot.model.SlotApproveStatus;
import com.mnit.erp.academic.facultyScholarSlot.model.FacultyScholarSlot;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Contains faculty scholar slot repository
 *
 * @author: Tejpal Singh
 * @date: 27 July, 2021
 */
@Repository
public interface FacultyScholarSlotRepository extends JpaRepository<FacultyScholarSlot, Long> {
    FacultyScholarSlot findByEmployee_IdAndStatus(Long facultyId, Boolean status);
    FacultyScholarSlot findBySlotApproveStatus(SlotApproveStatus slotApproveStatus);
    FacultyScholarSlot findByIdAndSlotApproveStatus(Long Id, SlotApproveStatus slotApproveStatus);
    FacultyScholarSlot findBySlotApproveStatusAndIdNot(SlotApproveStatus slotApproveStatus, Long Id);
}
