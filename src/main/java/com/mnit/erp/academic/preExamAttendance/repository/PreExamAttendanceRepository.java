package com.mnit.erp.academic.preExamAttendance.repository;

import com.mnit.erp.academic.course.model.Course;
import com.mnit.erp.academic.exam.model.Exam;
import com.mnit.erp.academic.preExamAttendance.model.PreExamAttendance;
import com.mnit.erp.academic.preExamAttendance.model.Student;
import com.mnit.erp.academic.section.model.Section;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Contains PreExamAttendance Service
 *
 * @author: Tejpal Singh
 * @definition: add/upate/find/findAll
 * @date: 11 June, 2021
 */
public interface PreExamAttendanceRepository extends JpaRepository<PreExamAttendance, Long> {
    PreExamAttendance findByExamAndCourseAndSection(Exam exam, Course course, Section section);

    @Query(value = "SELECT id, NAME FROM Student WHERE id IN (\n" +
            "SELECT j.studentId AS id \n" +
            "FROM PreExamAttendance, JSON_TABLE(PreExamAttendance.students, '$[*]' COLUMNS (\n" +
            "  isFa TINYINT(1) PATH '$.isFa',\n" +
            "  studentId BIGINT PATH '$.studentId')\n" +
            ") AS j WHERE j.isFa = :isFa AND course_id = :courseId AND " +
            "exam_id = :examId AND section_id = :sectionId)", nativeQuery = true)
    List<Student> findEligibleStudents(@Param("isFa") boolean isFa, @Param("examId") long examId,
                                       @Param("courseId") long courseId, @Param("sectionId") long sectionId);
}
