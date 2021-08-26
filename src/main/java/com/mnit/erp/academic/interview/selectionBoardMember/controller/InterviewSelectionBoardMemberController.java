package com.mnit.erp.academic.interview.selectionBoardMember.controller;

import com.mnit.erp.AbstractController;
import com.mnit.erp.academic.interview.selectionBoardMember.model.InterviewSelectionBoardMember;
import com.mnit.erp.academic.interview.selectionBoardMember.model.SelectionBoardMemberStatus;
import com.mnit.erp.academic.interview.selectionBoardMember.service.InterviewSelectionBoardMemberService;
import com.mnit.erp.response.CustomResponseMessage;
import com.mnit.erp.util.ResponseMessageType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

/**
 * Contains Interview selection board member rest api controller
 *
 * @author: Tejpal Singh
 * @return: add/update/find/findAll APIs
 * @date: 30 July, 2021
 */
@RestController
@RequestMapping("/interview-selection-board-member")
public class InterviewSelectionBoardMemberController extends AbstractController {

    @Autowired
    InterviewSelectionBoardMemberService interviewSelectionBoardMemberService;

    @PostMapping("/add")
    public CustomResponseMessage add(@RequestBody InterviewSelectionBoardMember interviewSelectionBoardMember){
        InterviewSelectionBoardMember interviewSelectionBoardMember1 = this.interviewSelectionBoardMemberService.add(interviewSelectionBoardMember);
        ResponseMessageType responseMessageType = Objects.nonNull(interviewSelectionBoardMember1) ? ResponseMessageType.SUCCESS : ResponseMessageType.ERROR;
        return CustomResponseMessage.builder().message(Objects.nonNull(interviewSelectionBoardMember1) ? "Interview selection board member added successfully!" : "Unable to add Interview selection board member!")
                .messageType(responseMessageType)
                .response(interviewSelectionBoardMember1).build();
    }

    @PutMapping("/update")
    public CustomResponseMessage update(@RequestBody InterviewSelectionBoardMember interviewSelectionBoardMember){
        InterviewSelectionBoardMember interviewSelectionBoardMember1 = this.interviewSelectionBoardMemberService.update(interviewSelectionBoardMember);
        ResponseMessageType responseMessageType = Objects.nonNull(interviewSelectionBoardMember1) ? ResponseMessageType.SUCCESS : ResponseMessageType.ERROR;
        return CustomResponseMessage.builder().message(Objects.nonNull(interviewSelectionBoardMember1) ? "Interview selection board member updated successfully!" : "Unable to update Interview selection board member!")
                .messageType(responseMessageType)
                .response(interviewSelectionBoardMember1).build();
    }

    @PutMapping("/acceptOrReject")
    public CustomResponseMessage acceptOrReject(@RequestBody InterviewSelectionBoardMember interviewSelectionBoardMember){
        String action="";
        String action_past_participle;
        if(interviewSelectionBoardMember.getStatus() == SelectionBoardMemberStatus.ACCEPTED){
            action = "accept";
            action_past_participle = "accepted";
        }else if(interviewSelectionBoardMember.getStatus() == SelectionBoardMemberStatus.REJECTED){
            action = "reject";
            action_past_participle = "rejected";
        }else{
            return CustomResponseMessage.builder().message("Bad request")
                    .messageType(ResponseMessageType.ERROR)
                    .response(null).build();
        }
        InterviewSelectionBoardMember interviewSelectionBoardMember1 = this.interviewSelectionBoardMemberService.changeStatus(interviewSelectionBoardMember);
        ResponseMessageType responseMessageType = Objects.nonNull(interviewSelectionBoardMember1) ? ResponseMessageType.SUCCESS : ResponseMessageType.ERROR;
        return CustomResponseMessage.builder().message(Objects.nonNull(interviewSelectionBoardMember1) ? "Student type change request "+action_past_participle+" successfully!" : "Unable to "+action+" student type change request!")
                .messageType(responseMessageType)
                .response(interviewSelectionBoardMember1).build();
    }

    @PutMapping("/remove")
    public CustomResponseMessage pendingOrRemove(@RequestBody InterviewSelectionBoardMember interviewSelectionBoardMember){
        String action="";
        String action_past_participle;
        if(interviewSelectionBoardMember.getStatus() == SelectionBoardMemberStatus.REMOVED){
            action = "remove";
            action_past_participle = "removed";
        }else{
            return CustomResponseMessage.builder().message("Bad request")
                    .messageType(ResponseMessageType.ERROR)
                    .response(null).build();
        }
        InterviewSelectionBoardMember interviewSelectionBoardMember1 = this.interviewSelectionBoardMemberService.changeStatus(interviewSelectionBoardMember);
        ResponseMessageType responseMessageType = Objects.nonNull(interviewSelectionBoardMember1) ? ResponseMessageType.SUCCESS : ResponseMessageType.ERROR;
        return CustomResponseMessage.builder().message(Objects.nonNull(interviewSelectionBoardMember1) ? "Interview selection board member "+action_past_participle+" successfully!" : "Unable to "+action+" interview selection board member request!")
                .messageType(responseMessageType)
                .response(interviewSelectionBoardMember1).build();
    }

    @GetMapping("/{id}")
    public CustomResponseMessage find(@PathVariable Long id){
        InterviewSelectionBoardMember interviewSelectionBoardMember1 = this.interviewSelectionBoardMemberService.find(id);
        ResponseMessageType responseMessageType = Objects.nonNull(interviewSelectionBoardMember1) ? ResponseMessageType.SUCCESS : ResponseMessageType.ERROR;
        return CustomResponseMessage.builder().message(Objects.nonNull(interviewSelectionBoardMember1) ? "Interview selection board member found successfully!" : "Unable to find Interview selection board member!")
                .messageType(responseMessageType)
                .response(interviewSelectionBoardMember1).build();
    }

    @GetMapping("/findAllByInterviewSelectionBoard/{interviewSelectionBoardId}")
    public CustomResponseMessage findAllByInterviewSelectionBoard(@PathVariable Long interviewSelectionBoardId){
        List<InterviewSelectionBoardMember> interviewSelectionBoardMembers = this.interviewSelectionBoardMemberService.findAllByInterviewSelectionBoard(interviewSelectionBoardId);
        ResponseMessageType responseMessageType = Objects.nonNull(interviewSelectionBoardMembers) ? ResponseMessageType.SUCCESS : ResponseMessageType.ERROR;
        return CustomResponseMessage.builder().message(Objects.nonNull(interviewSelectionBoardMembers) && !interviewSelectionBoardMembers.isEmpty() ? "Interview selection board member found!" : "Unable to find Interview selection board members!")
                .messageType(responseMessageType)
                .response(interviewSelectionBoardMembers).build();
    }

    @GetMapping("/findAllByEmployee")
    public CustomResponseMessage findAllByEmployee(){
        Long employeeId = 55L;
        List<InterviewSelectionBoardMember> interviewSelectionBoardMembers = this.interviewSelectionBoardMemberService.findAllByEmployeeId(employeeId);
        ResponseMessageType responseMessageType = Objects.nonNull(interviewSelectionBoardMembers) ? ResponseMessageType.SUCCESS : ResponseMessageType.ERROR;
        return CustomResponseMessage.builder().message(Objects.nonNull(interviewSelectionBoardMembers) && !interviewSelectionBoardMembers.isEmpty() ? "Interview selection board member request found!" : "Unable to find Interview selection board member requests!")
                .messageType(responseMessageType)
                .response(interviewSelectionBoardMembers).build();
    }

}
