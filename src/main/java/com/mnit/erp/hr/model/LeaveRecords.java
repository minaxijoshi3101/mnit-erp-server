package com.mnit.erp.hr.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

/**
 *
 * @author Prahalad
 */
@Data
@NoArgsConstructor
@AllArgsConstructor

@Entity
public class LeaveRecords implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = false, referencedColumnName = "id", insertable =  true, updatable = true)
    @OnDelete(action = OnDeleteAction.NO_ACTION)
    private Employee employee;
    @Transient Long employeeId;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = false, referencedColumnName = "id", insertable =  true, updatable = true)
    @OnDelete(action = OnDeleteAction.NO_ACTION)
    private LeaveType leaveType;
    @Transient Long leaveTypeId;
    
    @Column(nullable=false)
    @Enumerated(EnumType.STRING)
    private Operation operation;
    
    @Column(nullable=false)
    private int numOfLeaves;
    
    @Column(nullable=false)
    @Enumerated(EnumType.STRING)
    private Modes mode;
    
    @Column(nullable=false)
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date createdOn;
    
    public LeaveRecords(Long id){
        this.id=id;
    }
    
    public Long getEmployeeId(){
        return Objects.nonNull(this.employee) ? this.employee.getId() : null;
    }
    
    public void setEmployeeId(Long employeeId){
        if(Objects.isNull(this.employee) && Objects.nonNull(employeeId)){
            this.employeeId = employeeId;
            this.employee = new Employee(employeeId);
        }
    }
    
    public Long getLeaveTypeId(){
        return Objects.nonNull(this.leaveType) ? this.leaveType.getId() : null;
    }
    
    public void setLeaveTypeId(Long leaveTypeId){
        if(Objects.isNull(this.leaveType) && Objects.nonNull(leaveTypeId)){
            this.leaveTypeId = leaveTypeId;
            this.leaveType = new LeaveType(leaveTypeId);
        }
    }
}
