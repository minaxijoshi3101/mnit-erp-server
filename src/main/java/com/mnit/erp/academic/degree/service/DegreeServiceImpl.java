package com.mnit.erp.academic.degree.service;

import com.mnit.erp.academic.degree.model.Degree;
import com.mnit.erp.academic.degree.repository.DegreeRepository;
import com.mnit.erp.academic.program.repository.ProgramRepository;

import com.mnit.erp.exceptions.ServiceException;
import com.mnit.erp.util.CommonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class DegreeServiceImpl implements DegreeService{

    @Autowired
    DegreeRepository degreeRepository;

    @Autowired
    ProgramRepository programRepository;

    @Override
    public Degree add(Degree degree) {
        Degree degree1 = this.degreeRepository.findByAbbreviation(degree.getAbbreviation());
        if(Objects.nonNull(degree1)){
            throw new ServiceException("Degree : " + degree.getAbbreviation() + " already exists. Can't add");
        }
        if(this.validate(degree))
            return this.degreeRepository.save(degree);
        return null;
    }

    @Override
    public Degree update(Degree degree) {
        Degree savedDegree = this.degreeRepository.findById(degree.getId()).orElse(null);
        if(Objects.isNull(savedDegree)){
            throw new ServiceException("Degree not found. Hence can't update!");
        }
        if(this.validate(degree)) {
            CommonUtils.copyNonNullProperties(degree, savedDegree);
            return this.degreeRepository.save(savedDegree);
        }
        return null;
    }

    @Override
    public Degree find(Long id) {
        return this.degreeRepository.findById(id).orElse(null);
    }

    @Override
    public List<Degree> findAll() {
        return this.degreeRepository.findAll();
    }

    @Override
    public List<Degree> findAllUnderProgram(Long programId) {
        return this.degreeRepository.findByProgram(programRepository.findById(programId).orElse(null));
    }

    @Override
    public List<Degree> findByDegreeIdIn(List<Long> degreeIds) {
        return this.degreeRepository.findByIdIn(degreeIds);
    }

    private boolean validate(Degree degree){
        return true;
    }

}
