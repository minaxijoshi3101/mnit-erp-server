package com.mnit.erp.academic.interview.selectionBoardMember.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.mnit.erp.academic.interview.selectionBoard.model.InterviewSelectionBoard;
import com.mnit.erp.audit.UserDateAudit;
import com.mnit.erp.hr.model.Employee;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Objects;

/**
 * Contains Interview selection board member model and its dependencies
 *
 * @author: Tejpal Singh
 * @return: Object of Interview selection board member model
 * @date: 30 July, 2021
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(uniqueConstraints={
        @UniqueConstraint(columnNames={"interview_selection_board_id", "employee_id"})
})
public class InterviewSelectionBoardMember extends UserDateAudit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    @Transient
    Long interviewSelectionBoardId;
    @Transient
    Long employeeId;
    @Column(nullable = false)
    SelectionBoardMemberStatus status;

    String remarks;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="interview_selection_board_id", nullable = false, referencedColumnName = "id", insertable =  true, updatable = true)
    private InterviewSelectionBoard interviewSelectionBoard;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    @JoinColumn(name="employee_id", nullable = false, referencedColumnName = "id", insertable =  true, updatable = true)
    private Employee employee;

    public void setInterviewSelectionBoardId(Long interviewSelectionBoardId){
        this.interviewSelectionBoard = Objects.nonNull(interviewSelectionBoardId) ? new InterviewSelectionBoard(interviewSelectionBoardId) : null;
    }
    public Long getInterviewSelectionBoardId(){
        return Objects.nonNull(this.interviewSelectionBoard) ? this.interviewSelectionBoard.getId() : null;
    }

    public void setEmployeeId(Long employeeId){
        this.employee = Objects.nonNull(employeeId) ? new Employee(employeeId) : null;
    }
    public Long getEmployeeId(){
        return Objects.nonNull(this.employee) ? this.employee.getId() : null;
    }

}