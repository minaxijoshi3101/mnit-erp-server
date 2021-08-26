package com.mnit.erp.academic.exam.attendance.service;

import com.mnit.erp.academic.exam.attendance.model.ExamAttendance;
import com.mnit.erp.academic.exam.attendance.repository.ExamAttendanceRepository;
import com.mnit.erp.exceptions.ServiceException;
import com.mnit.erp.util.CommonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

/**
 * Contains Exam attendance Service Implementation
 *
 * @author: Tejpal Singh
 * @definition: add/update/find/findAll
 * @date: 24 June, 2021
 */
@Service
public class ExamAttendanceServiceImpl implements ExamAttendanceService {

    @Autowired
    ExamAttendanceRepository examAttendanceRepository;

    @Override
    public ExamAttendance add(ExamAttendance examAttendance) {
        if(this.validate(examAttendance))
            return this.examAttendanceRepository.save(examAttendance);
        return null;
    }

    @Override
    public ExamAttendance update(ExamAttendance examAttendance) {
        ExamAttendance savedExamAttendance = this.examAttendanceRepository.findById(examAttendance.getId()).orElse(null);
        if(Objects.isNull(savedExamAttendance)){
            throw new ServiceException("Exam attendance not found. Can't update!");
        }
        if(this.validate(examAttendance)) {
            CommonUtils.copyNonNullProperties(examAttendance, savedExamAttendance);
            return this.examAttendanceRepository.save(examAttendance);
        }
        return null;
    }

    @Override
    public ExamAttendance find(Long id) {
        return this.examAttendanceRepository.findById(id).orElse(null);
    }

    @Override
    public List<ExamAttendance> findAll() {
        return this.examAttendanceRepository.findAll();
    }

    private boolean validate(ExamAttendance examAttendance){
        return true;
    }

}
