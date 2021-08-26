/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mnit.erp.academic.preadmission.preadmissiondata.service;

import com.mnit.erp.academic.preadmission.preadmissiondata.helper.AvailableRooms;
import com.mnit.erp.academic.preadmission.preadmissiondata.helper.ExcelHelper;
import com.mnit.erp.academic.preadmission.preadmissiondata.helper.RoomAllotmentRequestWrapper;
import com.mnit.erp.academic.preadmission.preadmissiondata.helper.SpecializationResponse;
import com.mnit.erp.academic.preadmission.preadmissiondata.repository.PreAdmissionDataRepository;
import com.mnit.erp.academic.preadmission.preadmissiondata.model.PreAdmissionData;
import com.mnit.erp.academic.selectionboard.model.SelectionBoard;
import com.mnit.erp.academic.selectionboard.repository.SelectionBoardRepository;
import com.mnit.erp.room.model.RoomAllotment;
import com.mnit.erp.room.model.RoomStudentLink;
import com.mnit.erp.room.repository.RoomAllotmentRepository;
import com.mnit.erp.room.repository.RoomStudentLinkRepository;

import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author praha
 */
@Service
public class PreAdmissionDataService {
    @Autowired
    PreAdmissionDataRepository preAdmissionDataRepository;
    
    @Autowired
    SelectionBoardRepository selectionBoardRepo;

    @Autowired
    RoomAllotmentRepository roomAllotmentRepository;

    @Autowired
    RoomStudentLinkRepository roomStudentLinkRepository;
    
	public List<PreAdmissionData> getAll() {
        List<PreAdmissionData> dataList = new ArrayList<>();
        preAdmissionDataRepository.findAll().forEach(dataList::add); 
        return dataList;        
    }
    
    public PreAdmissionData fetchStudentData(int year, long selectionBoardId, long rollno, String dob) {
        SelectionBoard sb = selectionBoardRepo.findById(selectionBoardId).orElse(null);
        if(Objects.isNull(sb)){
            return null;
        }
        PreAdmissionData preAdmissiondata = preAdmissionDataRepository.findBySelectionBoardAndYearAndRollNoAndDob(sb, year, rollno, dob);
        if(Objects.isNull(preAdmissiondata)){
            return null;
        }
        else{
            return preAdmissiondata;
        }
    }
    
    public PreAdmissionData getById(long id) {
        return preAdmissionDataRepository.findById(id).orElse(null);
    }
    
    public PreAdmissionData create(PreAdmissionData createData) {        
        return preAdmissionDataRepository.save(createData);
    }
    
    public List<PreAdmissionData> save(MultipartFile file, SelectionBoard selectionBoard) throws IOException {
        List<PreAdmissionData> saveData = ExcelHelper.excelToPreAdmissionData(file.getInputStream(), selectionBoard);
        saveData = preAdmissionDataRepository.saveAll(saveData);
		/*
		 * //update saved rows with uId RandomGenerator rg = new RandomGenerator(4);
		 * for(PreAdmissionData preAdmissionData:saveData) {
		 * preAdmissionData.setUploadId(rg.generateRandonString() +
		 * preAdmissionData.getId() + rg.generateRandonString()); } saveData =
		 * preAdmissionDataRepository.saveAll(saveData);
		 */        
        return saveData;        
    }
    
    public PreAdmissionData update(PreAdmissionData updateData) {
        return preAdmissionDataRepository.save(updateData); 
    }

    public List<SpecializationResponse> getSpecializationsAndNumberOfStudents() {
        return preAdmissionDataRepository.getSpecializationsAndNumberOfStudents();
    }

    public List<AvailableRooms> getAvailableRooms(String fromDate, String toDate) {
        return preAdmissionDataRepository.getAvailableRooms(fromDate, toDate);
    }

    public List<Long> getPreAdmissionDataBySpecialization(Long specializationId) {
        return preAdmissionDataRepository.getPreAdmissionDataBySpecialization(specializationId);
    }

    public List<Long> allotRoomForExam(List<RoomAllotmentRequestWrapper.AllotRoom> rooms, Long specializationId, Date fromDate, Date toDate) {
	    List<Long> allottedRoomList = new ArrayList<>();
        List<Long> preAdmissionDataList = getPreAdmissionDataBySpecialization(specializationId);

        if (preAdmissionDataList != null && preAdmissionDataList.size() > 0) {
            for (RoomAllotmentRequestWrapper.AllotRoom room : rooms) {
                System.out.println("PreAdmissionDataList Size = " + preAdmissionDataList.size());

                Long id = room.getId();
                Long capacity = room.getCapacity();
                System.out.println("Room Capacity = " + capacity);
                RoomAllotment roomAllotment = new RoomAllotment();
                roomAllotment.setRoom_id(id);
                roomAllotment.setAllotmentFromdate(fromDate);
                roomAllotment.setAllotmentTodate(toDate);

                Long roomAllotmentId = roomAllotmentRepository.save(roomAllotment).getId();
                allottedRoomList.add(roomAllotmentId);
                List<RoomStudentLink> roomStudentLinkList = new ArrayList<>();

                for (int i = 0; i < capacity; i++) {
                    if (i < preAdmissionDataList.size() || (preAdmissionDataList.size() == 1)) {
                        Long preAdmissionDataId = (preAdmissionDataList.size() == 1) ? preAdmissionDataList.get(0) : preAdmissionDataList.get(i);
                        RoomStudentLink roomStudentLink = new RoomStudentLink();
                        roomStudentLink.setPreadmissiondata_id(preAdmissionDataId);
                        roomStudentLink.setRoomallotment_id(roomAllotmentId);

                        roomStudentLinkList.add(roomStudentLink);
                        preAdmissionDataList.remove(preAdmissionDataList.size() == 1 ? 0 : i);
                    } else {
                        break;
                    }
                }

                roomStudentLinkRepository.saveAll(roomStudentLinkList);
                if (preAdmissionDataList.size() == 0)
                    break;
            }
        }

        return allottedRoomList;
    }
}
