package com.mnit.erp.academic.semester.service;

import com.mnit.erp.academic.semester.model.Semester;
import com.mnit.erp.academic.semester.repository.SemesterRepository;
import com.mnit.erp.exceptions.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class SemesterService {

    @Autowired
    SemesterRepository semesterRepository;


    public List<Semester> findAll() {
        return this.semesterRepository.findAll();
    }

    public Semester findSemester(Long semester) {
        return this.semesterRepository.findBySemester(semester);
    }


    public List<Semester> findAllByType(String type) {
        return this.semesterRepository.findByType(type);
    }

    public Semester add(Semester semester) {
        Semester bySemester = this.semesterRepository.findBySemester(semester.getSemester());
        if(Objects.nonNull(bySemester)){
            throw new ServiceException("Semester already exists. Can't add new");
        }
        return this.semesterRepository.save(semester);
    }

}
