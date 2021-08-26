package com.mnit.erp.academic.interview.selectionBoard.repository;

import com.mnit.erp.academic.interview.selectionBoard.model.InterviewSelectionBoard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Contains Interview selection board repository
 *
 * @author: Tejpal Singh
 * @date: 28 July, 2021
 */
@Repository
public interface InterviewSelectionBoardRepository extends JpaRepository<InterviewSelectionBoard, Long> {
    InterviewSelectionBoard findByName(String name);
    InterviewSelectionBoard findByNameAndIdNot(String name, Long Id);
}
