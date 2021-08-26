package com.mnit.erp.location.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.mnit.erp.academic.branch.model.Branch;
import com.mnit.erp.academic.degree.model.Degree;
import com.mnit.erp.academic.program.model.Program;
import com.mnit.erp.academic.semester.model.Semester;
import com.mnit.erp.department.model.Department;
import com.mnit.erp.util.Month;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;

/**
 * Contains Location model and its dependencies
 *
 * @author: Tejpal Singh
 * @return: Object of location model
 * @date: 16 June, 2021
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Location {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String name;
    LocationType locationType;
    Boolean status;
    public Location(Long locationId) {
        this.id = locationId;
    }
}
