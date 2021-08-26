package com.mnit.erp.academic.facultyScholarSlot.model;

import com.mnit.erp.audit.UserDateAudit;
import com.mnit.erp.hr.model.Employee;
import com.mnit.erp.user.model.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;

/**
 * Contains faculty scholar slot model and its dependencies
 *
 * @author: Tejpal Singh
 * @return: Object of faculty scholar slot model
 * @date: 27 July, 2021
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class FacultyScholarSlot extends UserDateAudit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    Long approvedSlots;
    Date approvedDate;
    SlotApproveStatus slotApproveStatus;
    @Column(nullable = false, columnDefinition = "BOOLEAN DEFAULT false")
    Boolean status;

    public FacultyScholarSlot(Long facultyScholarSlotId) {
        this.id = facultyScholarSlotId;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="employee_id", referencedColumnName = "id", insertable =  true, updatable = false)
    private Employee employee;

    @Transient
    Long employeeId;

    public Long getEmployeeId() {
        return Objects.nonNull(this.employee) ? this.employee.getId() : null;
    }
    public void setEmployeeId(Long employeeId) {
        this.employee = Objects.nonNull(employeeId) ? new Employee(employeeId) : null;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="approved_by_id", referencedColumnName = "id", insertable =  true, updatable = true)
    private User approvedBy;

    @Transient
    Long approvedById;

    public Long getApprovedById() {
        return Objects.nonNull(this.approvedBy) ? this.approvedBy.getId() : null;
    }
    public void setApprovedById(Long approvedById) {
        this.approvedById = approvedById;
        this.approvedBy = Objects.nonNull(approvedById) ? new User(approvedById) : null;
    }

}
