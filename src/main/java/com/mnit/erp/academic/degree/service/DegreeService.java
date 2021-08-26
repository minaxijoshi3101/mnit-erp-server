package com.mnit.erp.academic.degree.service;

import com.mnit.erp.academic.degree.model.Degree;

import java.util.List;

public interface DegreeService {

    Degree add(Degree degree);

    Degree update(Degree degree);

    Degree find(Long id);

    List<Degree> findAll();

    List<Degree> findAllUnderProgram(Long programId);

    List<Degree> findByDegreeIdIn(List<Long> asList);
}
