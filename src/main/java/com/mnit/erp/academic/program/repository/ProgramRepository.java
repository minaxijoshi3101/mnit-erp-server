package com.mnit.erp.academic.program.repository;

import com.mnit.erp.academic.program.model.Program;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProgramRepository extends JpaRepository<Program, Long> {
    Program findByAbbreviation(String abbreviation);
}

