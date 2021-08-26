package com.mnit.erp.academic.degree.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mnit.erp.academic.degree.model.Degree;
import com.mnit.erp.academic.program.model.Program;

@Repository
public interface DegreeRepository extends JpaRepository<Degree, Long> {
    List<Degree> findByProgram(Program program);
    
    List<Degree> findByNameContaining(String searchStrig); //Added by prahalad
    List<Degree> findByAbbreviationContaining(String searchString); //Added by prahalad

    Degree findByAbbreviation(String abbreviation);

    List<Degree> findByIdIn(List<Long> ids);
}
