package com.mnit.erp.academic.interview.selectionBoardMember.service;

import com.mnit.erp.academic.interview.selectionBoardMember.model.InterviewSelectionBoardMember;
import com.mnit.erp.academic.interview.selectionBoardMember.model.SelectionBoardMemberStatus;
import com.mnit.erp.academic.interview.selectionBoardMember.repository.InterviewSelectionBoardMemberRepository;
import com.mnit.erp.exceptions.ServiceException;
import com.mnit.erp.util.CommonUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

/**
 * Contains Interview selection board member Service Implementation
 *
 * @author: Tejpal Singh
 * @definition: add/update/find/findAll
 * @date: 30 July, 2021
 */
@Service
@Slf4j
public class InterviewSelectionBoardMemberServiceImpl implements InterviewSelectionBoardMemberService {

    @Autowired
    InterviewSelectionBoardMemberRepository interviewSelectionBoardMemberRepository;

    @Override
    public InterviewSelectionBoardMember add(InterviewSelectionBoardMember interviewSelectionBoardMember) {

        InterviewSelectionBoardMember bySelectionBoardAndEmployee = this.interviewSelectionBoardMemberRepository.findByInterviewSelectionBoard_IdAndEmployee_Id(interviewSelectionBoardMember.getInterviewSelectionBoardId(), interviewSelectionBoardMember.getEmployeeId());
        if (Objects.nonNull(bySelectionBoardAndEmployee)) {
            throw new ServiceException("Interview selection board member for this student already exists. Can't add again!");
        }
        if (this.validate(interviewSelectionBoardMember)){
            interviewSelectionBoardMember.setStatus(SelectionBoardMemberStatus.PENDING);
            return this.interviewSelectionBoardMemberRepository.save(interviewSelectionBoardMember);
        }
        return null;
    }

    @Override
    public InterviewSelectionBoardMember update(InterviewSelectionBoardMember interviewSelectionBoardMember) {
        InterviewSelectionBoardMember bySelectionBoardAndEmployeeAndIdNot = this.interviewSelectionBoardMemberRepository.findByInterviewSelectionBoard_IdAndEmployee_IdAndIdNot(interviewSelectionBoardMember.getInterviewSelectionBoardId(), interviewSelectionBoardMember.getEmployeeId(), interviewSelectionBoardMember.getId());
        if(Objects.nonNull(bySelectionBoardAndEmployeeAndIdNot)){
            throw new ServiceException("Interview selection board member for this student already exists. Can't update!");
        }

        InterviewSelectionBoardMember savedInterviewSelectionBoardMember = this.interviewSelectionBoardMemberRepository.findById(interviewSelectionBoardMember.getId()).orElse(null);
        if(Objects.isNull(savedInterviewSelectionBoardMember)){
            throw new ServiceException("Interview selection board member not found. Can't update!");
        }
        if(this.validate(interviewSelectionBoardMember)) {
            CommonUtils.copyNonNullProperties(interviewSelectionBoardMember, savedInterviewSelectionBoardMember);
            savedInterviewSelectionBoardMember.setStatus(SelectionBoardMemberStatus.PENDING);
            return this.interviewSelectionBoardMemberRepository.save(interviewSelectionBoardMember);
        }
        return null;
    }

    @Override
    public InterviewSelectionBoardMember changeStatus(InterviewSelectionBoardMember interviewSelectionBoardMember) {
        InterviewSelectionBoardMember saveInterviewSelectionBoardMember = this.interviewSelectionBoardMemberRepository.findByIdAndStatus(interviewSelectionBoardMember.getId(), SelectionBoardMemberStatus.PENDING);
        if(Objects.isNull(saveInterviewSelectionBoardMember)){
            throw new ServiceException("Interview selection board member not found. Can't update!");
        }
        if(this.validate(interviewSelectionBoardMember)) {
            saveInterviewSelectionBoardMember.setStatus(interviewSelectionBoardMember.getStatus());
            saveInterviewSelectionBoardMember.setRemarks(interviewSelectionBoardMember.getRemarks());
            return this.interviewSelectionBoardMemberRepository.save(saveInterviewSelectionBoardMember);
        }
        return null;
    }

    @Override
    public InterviewSelectionBoardMember find(Long id) {
        return this.interviewSelectionBoardMemberRepository.findById(id).orElse(null);
    }

    @Override
    public List<InterviewSelectionBoardMember> findAllByInterviewSelectionBoard(Long interviewSelectionBoardId) {
        return this.interviewSelectionBoardMemberRepository.findByInterviewSelectionBoard_Id(interviewSelectionBoardId);
    }
    @Override
    public List<InterviewSelectionBoardMember> findAllByEmployeeId(Long employeeId) {
        return this.interviewSelectionBoardMemberRepository.findByEmployee_IdAndStatus(employeeId, SelectionBoardMemberStatus.PENDING);
    }
    private boolean validate(InterviewSelectionBoardMember interviewSelectionBoardMember){
        return true;
    }

}
