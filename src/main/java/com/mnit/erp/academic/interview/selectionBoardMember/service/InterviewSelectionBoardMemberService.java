package com.mnit.erp.academic.interview.selectionBoardMember.service;

import com.mnit.erp.academic.interview.selectionBoardMember.model.InterviewSelectionBoardMember;

import java.util.List;

/**
 * Contains Interview selection board member Service
 *
 * @author: Tejpal Singh
 * @declaration: add/update/find/findAll
 * @date: 30 July, 2021
 */
public interface InterviewSelectionBoardMemberService {
    InterviewSelectionBoardMember add(InterviewSelectionBoardMember interviewSelectionBoardMember);
    InterviewSelectionBoardMember update(InterviewSelectionBoardMember interviewSelectionBoardMember);
    InterviewSelectionBoardMember find(Long interviewSelectionBoardId);
    List<InterviewSelectionBoardMember> findAllByInterviewSelectionBoard(Long interviewSelectionBoardId);
    List<InterviewSelectionBoardMember> findAllByEmployeeId(Long employeeId);
    InterviewSelectionBoardMember changeStatus(InterviewSelectionBoardMember interviewSelectionBoardMember);
}
