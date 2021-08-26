package com.mnit.erp.academic.preExamAttendance.service;

import com.mnit.erp.academic.preExamAttendance.model.PreExamAttendance;
import com.mnit.erp.academic.preExamAttendance.model.Student;
import com.mnit.erp.academic.preExamAttendance.repository.PreExamAttendanceRepository;
import com.mnit.erp.exceptions.ServiceException;
import com.mnit.erp.util.CommonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

/**
 * Contains PreExamAttendance Service Implementation
 *
 * @author: Tejpal Singh
 * @declaration: add/upate/find/findAll
 * @date: 11 June, 2021
 */
@Service
public class PreExamAttendanceServiceImpl implements PreExamAttendanceService{
    @Autowired
    PreExamAttendanceRepository preExamAttendanceRepository;

    @Override
    public PreExamAttendance add(PreExamAttendance preExamAttendance) {
        PreExamAttendance byByExamIdAndStudentIdAndCourseId = preExamAttendanceRepository.findByExamAndCourseAndSection(preExamAttendance.getExam(), preExamAttendance.getCourse(), preExamAttendance.getSection());
        if(Objects.nonNull(byByExamIdAndStudentIdAndCourseId)){
            throw new ServiceException("Attendance : of this exam and course already exists. Can't add again!");
        }
        if(this.validate(preExamAttendance))
            return this.preExamAttendanceRepository.save(preExamAttendance);
        return null;
    }
    @Override
    public PreExamAttendance update(PreExamAttendance preExamAttendance) {
        PreExamAttendance savedPreExamAttendance = this.preExamAttendanceRepository.findById(preExamAttendance.getId()).orElse(null);
        if(Objects.isNull(savedPreExamAttendance)){
            throw new ServiceException("Pre Exam Attendance not found. Can't update!");
        }
        if(this.validate(preExamAttendance)) {
            CommonUtils.copyNonNullProperties(preExamAttendance, savedPreExamAttendance);
            return this.preExamAttendanceRepository.save(preExamAttendance);
        }
        return null;
    }

    @Override
    public PreExamAttendance find(Long id) {
        return this.preExamAttendanceRepository.findById(id).orElse(null);
    }

    @Override
    public List<PreExamAttendance> findAll() {
        return this.preExamAttendanceRepository.findAll();
    }

    @Override
    public List<Student> findEligibleStudents(boolean isFa, long examId, long courseId, long sectionId) {
        return this.preExamAttendanceRepository.findEligibleStudents(isFa, examId, courseId, sectionId);
    }

    private boolean validate(PreExamAttendance preExamAttendance){
        return true;
    }
}
