package com.mnit.erp.academic.interview.selectionBoardMember.repository;

import com.mnit.erp.academic.interview.selectionBoardMember.model.InterviewSelectionBoardMember;
import com.mnit.erp.academic.interview.selectionBoardMember.model.SelectionBoardMemberStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Contains Interview selection board member repository
 *
 * @author: Tejpal Singh
 * @date: 30 July, 2021
 */
@Repository
public interface InterviewSelectionBoardMemberRepository extends JpaRepository<InterviewSelectionBoardMember, Long> {
    List<InterviewSelectionBoardMember> findByInterviewSelectionBoard_Id(Long interviewSelectionBoardId);
    List<InterviewSelectionBoardMember> findByEmployee_IdAndStatus(Long employeeId, SelectionBoardMemberStatus selectionBoardMemberStatus);
    InterviewSelectionBoardMember findByInterviewSelectionBoard_IdAndEmployee_Id(Long interviewSelectionBoardId, Long employeeId);
    InterviewSelectionBoardMember findByInterviewSelectionBoard_IdAndEmployee_IdAndIdNot(Long interviewSelectionBoardId, Long employeeId, Long Id);
    InterviewSelectionBoardMember findByIdAndStatus(Long interviewSelectionBoardMemberId, SelectionBoardMemberStatus status);
}
