package com.mnit.erp.academic.exam.service;

import com.mnit.erp.academic.exam.model.Exam;
import com.mnit.erp.academic.exam.repository.ExamRepository;
import com.mnit.erp.exceptions.ServiceException;
import com.mnit.erp.util.CommonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
/**
 * Contains PreExamAttendance Service Implementation
 *
 * @author: Tejpal Singh
 * @definition: add/upate/find/findAll
 * @date: 08 June, 2021
 */
@Service
public class ExamServiceImpl implements ExamService{

    @Autowired
    ExamRepository examRepository;

    @Override
    public Exam add(Exam exam) {
        Exam byName = examRepository.findByNameAndExamType(exam.getName(), exam.getExamType());
        if(Objects.nonNull(byName)){
            throw new ServiceException("Exam:" + exam.getName() + " of selected Exam Type already exists. Can't add again!");
        }
        if(this.validate(exam))
            return this.examRepository.save(exam);
        return null;
    }

    @Override
    public Exam update(Exam exam) {
        Exam savedExam = this.examRepository.findById(exam.getId()).orElse(null);
        if(Objects.isNull(savedExam)){
            throw new ServiceException("Exam not found. Can't update!");
        }
        if(this.validate(exam)) {
            CommonUtils.copyNonNullProperties(exam, savedExam);
            return this.examRepository.save(exam);
        }
        return null;
    }

    @Override
    public Exam find(Long id) {
        return this.examRepository.findById(id).orElse(null);
    }

    @Override
    public List<Exam> findAll() {
        return this.examRepository.findAll();
    }

    private boolean validate(Exam exam){
        return true;
    }

}
