package com.mnit.erp.academic.program.service;

import com.mnit.erp.academic.program.model.Program;

import java.util.List;

public interface ProgramService {
    Program add(Program program);
    Program update(Program program);
    Program find(Long id);
    List<Program> findAll();
}
