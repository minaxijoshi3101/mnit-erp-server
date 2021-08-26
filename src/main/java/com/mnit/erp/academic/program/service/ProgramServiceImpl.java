package com.mnit.erp.academic.program.service;

import com.mnit.erp.academic.program.model.Program;
import com.mnit.erp.academic.program.repository.ProgramRepository;
import com.mnit.erp.exceptions.ServiceException;
import com.mnit.erp.util.CommonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class ProgramServiceImpl implements ProgramService{

    @Autowired
    ProgramRepository programRepository;

    @Override
    public Program add(Program program) {
        Program byAbbreviation = programRepository.findByAbbreviation(program.getAbbreviation());
        if(Objects.nonNull(byAbbreviation)){
            throw new ServiceException("Program:" + program.getAbbreviation() + " already exists. Can't add again!");
        }
        if(this.validate(program))
            return this.programRepository.save(program);
        return null;
    }

    @Override
    public Program update(Program program) {
        Program savedProgram = this.programRepository.findById(program.getId()).orElse(null);
        if(Objects.isNull(savedProgram)){
            throw new ServiceException("Program not found. Can't update!");
        }
        if(this.validate(program)) {
            CommonUtils.copyNonNullProperties(program, savedProgram);
            return this.programRepository.save(program);
        }
        return null;
    }

    @Override
    public Program find(Long id) {
        return this.programRepository.findById(id).orElse(null);
    }

    @Override
    public List<Program> findAll() {
        return this.programRepository.findAll();
    }

    private boolean validate(Program program){
        return true;
    }

}
