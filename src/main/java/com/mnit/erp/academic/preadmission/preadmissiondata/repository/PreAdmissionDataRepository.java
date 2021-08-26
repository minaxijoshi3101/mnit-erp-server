/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mnit.erp.academic.preadmission.preadmissiondata.repository;

import com.mnit.erp.academic.preadmission.preadmissiondata.helper.AvailableRooms;
import com.mnit.erp.academic.preadmission.preadmissiondata.helper.SpecializationResponse;
import com.mnit.erp.academic.preadmission.preadmissiondata.model.PreAdmissionData;
import com.mnit.erp.academic.selectionboard.model.SelectionBoard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 *
 * @author praha
 */
@Repository
public interface PreAdmissionDataRepository extends JpaRepository<PreAdmissionData, Long> {
    PreAdmissionData findBySelectionBoardAndYearAndRollNoAndDob(SelectionBoard selectionBoard, int year, long rollno, String dob);

    @Query(value = "SELECT t1.specialization AS specializationId, t1.specializationValue AS specializationValue, COUNT(*) AS noOfStudents\n" +
            "FROM PreAdmissionData t1\n" +
            "WHERE t1.id NOT IN (SELECT t2.preadmissiondata_id FROM RoomStudentLink t2) AND \n" +
            "t1.specializationValue IS NOT NULL GROUP BY t1.specializationValue", nativeQuery = true)
    public List<SpecializationResponse> getSpecializationsAndNumberOfStudents();

    @Query(value = "SELECT t1.id AS id, t1.name as name, t1.capacity as capacity\n" +
            "FROM Room t1 WHERE t1.id NOT IN (SELECT t2.room_id FROM RoomAllotment t2\n" +
            "WHERE t2.allotmentFromdate BETWEEN :fromDate AND :toDate OR\n" +
            ":fromDate BETWEEN t2.allotmentFromdate AND t2.allotmentTodate) ORDER BY t1.name\n", nativeQuery = true)
    public List<AvailableRooms> getAvailableRooms(@Param("fromDate") String fromDate, @Param("toDate") String toDate);

    @Query(value = "SELECT id FROM PreAdmissionData WHERE specialization = :cp", nativeQuery = true)
    public List<Long> getPreAdmissionDataBySpecialization(@Param("cp") long specialization);

}
