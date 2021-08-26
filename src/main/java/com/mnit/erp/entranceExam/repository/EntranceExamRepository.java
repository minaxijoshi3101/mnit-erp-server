package com.mnit.erp.entranceExam.repository;

import com.mnit.erp.entranceExam.model.EntranceExam;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EntranceExamRepository extends JpaRepository<EntranceExam, Long> {
}
