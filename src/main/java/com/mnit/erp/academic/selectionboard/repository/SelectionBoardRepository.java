package com.mnit.erp.academic.selectionboard.repository;

import com.mnit.erp.academic.selectionboard.model.SelectionBoard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SelectionBoardRepository extends JpaRepository<SelectionBoard, Long> {
}
