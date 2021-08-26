package com.mnit.erp.academic.interview.selectionBoard.service;

import com.mnit.erp.academic.interview.selectionBoard.model.InterviewSelectionBoard;
import com.mnit.erp.response.CustomResponseMessage;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * Contains Interview selection board Service
 *
 * @author: Tejpal Singh
 * @declaration: add/update/find/findAll
 * @date: 28 July, 2021
 */
public interface InterviewSelectionBoardService {
    InterviewSelectionBoard add(InterviewSelectionBoard interviewSelectionBoard);
    InterviewSelectionBoard update(InterviewSelectionBoard interviewSelectionBoard);
    InterviewSelectionBoard find(Long interviewSelectionBoardId);
    List<InterviewSelectionBoard> findAll();
    CustomResponseMessage uploadFile(Long interviewSelectionBoardId, MultipartFile momFile);
}
