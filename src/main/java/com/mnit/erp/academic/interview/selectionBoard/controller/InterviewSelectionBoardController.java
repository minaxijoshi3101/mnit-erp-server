package com.mnit.erp.academic.interview.selectionBoard.controller;

import com.mnit.erp.AbstractController;
import com.mnit.erp.academic.facultyScholarSlot.model.FacultyScholarSlot;
import com.mnit.erp.academic.facultyScholarSlot.model.SlotApproveStatus;
import com.mnit.erp.academic.interview.selectionBoard.model.InterviewSelectionBoard;
import com.mnit.erp.academic.interview.selectionBoard.service.InterviewSelectionBoardService;
import com.mnit.erp.response.CustomResponseMessage;
import com.mnit.erp.util.ResponseMessageType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Objects;

/**
 * Contains Interview selection board rest api controller
 *
 * @author: Tejpal Singh
 * @return: add/update/find/findAll APIs
 * @date: 28 July, 2021
 */
@RestController
@RequestMapping("/interview-selection-board")
public class InterviewSelectionBoardController extends AbstractController {

    @Autowired
    InterviewSelectionBoardService interviewSelectionBoardService;

    @PostMapping("/add")
    public CustomResponseMessage add(@RequestBody InterviewSelectionBoard interviewSelectionBoard){
        InterviewSelectionBoard interviewSelectionBoard1 = this.interviewSelectionBoardService.add(interviewSelectionBoard);
        ResponseMessageType responseMessageType = Objects.nonNull(interviewSelectionBoard1) ? ResponseMessageType.SUCCESS : ResponseMessageType.ERROR;
        return CustomResponseMessage.builder().message(Objects.nonNull(interviewSelectionBoard1) ? "Interview selection board added successfully!" : "Unable to add Interview selection board!")
                .messageType(responseMessageType)
                .response(interviewSelectionBoard1).build();
    }

    @PutMapping("/update")
    public CustomResponseMessage update(@RequestBody InterviewSelectionBoard interviewSelectionBoard){
        InterviewSelectionBoard interviewSelectionBoard1 = this.interviewSelectionBoardService.update(interviewSelectionBoard);
        ResponseMessageType responseMessageType = Objects.nonNull(interviewSelectionBoard1) ? ResponseMessageType.SUCCESS : ResponseMessageType.ERROR;
        return CustomResponseMessage.builder().message(Objects.nonNull(interviewSelectionBoard1) ? "Interview selection board updated successfully!" : "Unable to update Interview selection board!")
                .messageType(responseMessageType)
                .response(interviewSelectionBoard1).build();
    }

    @GetMapping("/{id}")
    public CustomResponseMessage find(@PathVariable Long id){
        InterviewSelectionBoard interviewSelectionBoard1 = this.interviewSelectionBoardService.find(id);
        ResponseMessageType responseMessageType = Objects.nonNull(interviewSelectionBoard1) ? ResponseMessageType.SUCCESS : ResponseMessageType.ERROR;
        return CustomResponseMessage.builder().message(Objects.nonNull(interviewSelectionBoard1) ? "Interview selection board found successfully!" : "Unable to find Interview selection board!")
                .messageType(responseMessageType)
                .response(interviewSelectionBoard1).build();
    }

    @GetMapping
    public CustomResponseMessage findAll(){
        List<InterviewSelectionBoard> interviewSelectionBoards = this.interviewSelectionBoardService.findAll();
        ResponseMessageType responseMessageType = Objects.nonNull(interviewSelectionBoards) ? ResponseMessageType.SUCCESS : ResponseMessageType.ERROR;
        return CustomResponseMessage.builder().message(Objects.nonNull(interviewSelectionBoards) && !interviewSelectionBoards.isEmpty() ? "Interview selection board found!" : "Unable to find Interview selection boards!")
                .messageType(responseMessageType)
                .response(interviewSelectionBoards).build();
    }

    @PostMapping("/upload")
    public CustomResponseMessage uploadFile(@RequestParam("interviewSelectionBoardId") Long courseId, @RequestParam("momFile") MultipartFile momFile ) {
        return interviewSelectionBoardService.uploadFile(courseId, momFile);
    }

}
