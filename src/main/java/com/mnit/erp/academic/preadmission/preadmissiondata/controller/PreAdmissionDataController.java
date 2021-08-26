/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mnit.erp.academic.preadmission.preadmissiondata.controller;

import com.mnit.erp.academic.preadmission.preadmissiondata.helper.AvailableRooms;
import com.mnit.erp.academic.preadmission.preadmissiondata.helper.ExcelHelper;
import com.mnit.erp.academic.preadmission.preadmissiondata.helper.RoomAllotmentRequestWrapper;
import com.mnit.erp.academic.preadmission.preadmissiondata.helper.SpecializationResponse;
import com.mnit.erp.academic.preadmission.preadmissiondata.model.PreAdmissionData;
import com.mnit.erp.academic.preadmission.preadmissiondata.service.PreAdmissionDataService;
import com.mnit.erp.academic.selectionboard.model.SelectionBoard;
import com.mnit.erp.academic.selectionboard.repository.SelectionBoardRepository;
import com.mnit.erp.response.CustomResponseMessage;
import com.mnit.erp.util.ResponseMessageType;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author Prahalad
 */
@RestController
@RequestMapping("/pre_admission")
public class PreAdmissionDataController {
    @Autowired
    PreAdmissionDataService preAdmissionDataService;
    
    @Autowired
    SelectionBoardRepository selectionBoardRepo;
    
    @GetMapping
    public CustomResponseMessage findAll(){
        List<PreAdmissionData> dataList = preAdmissionDataService.getAll();
        ResponseMessageType responseMessageType = Objects.nonNull(dataList) ? ResponseMessageType.SUCCESS : ResponseMessageType.ERROR;
        return CustomResponseMessage.builder().message(Objects.nonNull(dataList) && dataList.size() > 0 ? "Success! Data is fetched." : "No record found.")
                .messageType(responseMessageType)
                .response(dataList).build();
    }
    
    @GetMapping("/student")
    public CustomResponseMessage getStudentData( @RequestParam int year, @RequestParam long selectionBoardId, @RequestParam long rollNo, @RequestParam String dob){
    	String errorStr = "No record found.";
        PreAdmissionData studentData = preAdmissionDataService.fetchStudentData( year, selectionBoardId, rollNo, dob);
        if("Submitted".equals(studentData.getStudentStatus())) {
        	studentData = null;
        	errorStr = "Already submitted, please wait for approval";
        }
        ResponseMessageType responseMessageType = Objects.nonNull(studentData) ? ResponseMessageType.SUCCESS : ResponseMessageType.ERROR;
        return CustomResponseMessage.builder().message(Objects.nonNull(studentData) ? "Success! Data is fetched." : errorStr )
                .messageType(responseMessageType)
                .response(studentData).build();
    }
    
    @PostMapping("/upload")
    public CustomResponseMessage uploadFile(@RequestParam("selectionBoardId") long selectionBoardId, @RequestParam("file") MultipartFile file ) {
        Optional<SelectionBoard> sb = selectionBoardRepo.findById(selectionBoardId);
        if (!sb.isPresent()) {
            ResponseMessageType responseMessageType = ResponseMessageType.ERROR;
            return CustomResponseMessage.builder().message("Error! Invalid Selection Board.")
                .messageType(responseMessageType)
                .response(null).build();
        }
        else{
            String message = "";
            
            if (ExcelHelper.hasExcelFormat(file)) {
                try {
                    List<PreAdmissionData> dataList = preAdmissionDataService.save(file,sb.get());
                    ResponseMessageType responseMessageType = Objects.nonNull(dataList) ? ResponseMessageType.SUCCESS : ResponseMessageType.ERROR;
                    return CustomResponseMessage.builder().message(Objects.nonNull(dataList) && dataList.size() > 0 ? "Success! Data Saved." : "Unable to add data.")
                    .messageType(responseMessageType)
                    .response(dataList).build();
                } catch (Exception e) {
                    ResponseMessageType responseMessageType = ResponseMessageType.ERROR;
                    return CustomResponseMessage.builder().message("Error! "+e.getMessage())
                    .messageType(responseMessageType)
                    .response(null).build();
                }
            }

            message = "Please upload an excel file!";
            ResponseMessageType responseMessageType = ResponseMessageType.ERROR;
                return CustomResponseMessage.builder().message("Error! "+message)
                    .messageType(responseMessageType)
                    .response(null).build();
        }
    }

    @GetMapping("/specializations")
    public CustomResponseMessage getSpecializationsAndNumberOfStudents(){
        List<SpecializationResponse> dataList = preAdmissionDataService.getSpecializationsAndNumberOfStudents();
        System.out.println("Data List = " + dataList);

        ResponseMessageType responseMessageType = Objects.nonNull(dataList) ? ResponseMessageType.SUCCESS : ResponseMessageType.ERROR;
        return CustomResponseMessage.builder().message(Objects.nonNull(dataList) && dataList.size() > 0 ? "Success! Specialization is fetched." : "No record found.")
                .messageType(responseMessageType)
                .response(dataList).build();
    }

    @GetMapping("/getavailablerooms")
    public CustomResponseMessage getAvailableRooms(@RequestParam String fromDateTime, @RequestParam String toDateTime){
        System.out.println("From Date = " + fromDateTime);
        System.out.println("To Date = " + toDateTime);

        List<AvailableRooms> dataList = preAdmissionDataService.getAvailableRooms(fromDateTime, toDateTime);
        System.out.println("Available Rooms = " + dataList);
        ResponseMessageType responseMessageType = Objects.nonNull(dataList) ? ResponseMessageType.SUCCESS : ResponseMessageType.ERROR;
        return CustomResponseMessage.builder().message(Objects.nonNull(dataList) && dataList.size() > 0 ? "Success! Fetched Available Rooms." : "No record found.")
                .messageType(responseMessageType)
                .response(dataList).build();
    }

    @PostMapping("/allotroomforexam")
    public CustomResponseMessage allotRoomForExam(@RequestBody RoomAllotmentRequestWrapper requestWrapper){
        List<Long> dataList = preAdmissionDataService.allotRoomForExam(requestWrapper.getRooms(), requestWrapper.getSpecializationId(), requestWrapper.getFromDate(), requestWrapper.getToDate());
        System.out.println("Allotted Rooms = " + dataList);
        ResponseMessageType responseMessageType = Objects.nonNull(dataList) ? ResponseMessageType.SUCCESS : ResponseMessageType.ERROR;
        return CustomResponseMessage.builder().message(Objects.nonNull(dataList) && dataList.size() > 0 ? "Success! Allotted Rooms." : "Something went wrong.")
                .messageType(responseMessageType)
                .response(dataList).build();
    }
}
