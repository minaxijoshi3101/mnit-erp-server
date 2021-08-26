package com.mnit.erp.academic.exam.grade.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.mnit.erp.academic.course.model.Course;
import com.mnit.erp.academic.exam.model.Exam;
import com.mnit.erp.room.model.Room;
import com.mnit.erp.user.model.User;
import com.vladmihalcea.hibernate.type.json.JsonStringType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;

import javax.persistence.*;
import java.util.Date;
import java.util.List;
import java.util.Objects;

/**
 * Contains Grade model and its dependencies
 *
 * @author: Tejpal Singh
 * @return: Object of grade model
 * @date: 29 June, 2021
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Grade {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    @Column(nullable = false, unique = true)
    String name;
    @Column(nullable = false)
    Long points;
    @Column(nullable = false)
    Boolean status;
    String academicPerformance;
}
