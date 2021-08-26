package com.mnit.erp.finance.claimBehaviourSetting.model;

import com.mnit.erp.designation.model.Designation;
import com.mnit.erp.hr.model.AppointmentNature;
import com.mnit.erp.hr.model.Cadre;
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
 * Contains claim behaviour setting model and its dependencies
 *
 * @author: Tejpal Singh
 * @return: Object of claim behaviour setting model
 * @date: 16 July, 2021
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(uniqueConstraints={
        @UniqueConstraint(columnNames={"appointment_nature_id", "cadre_id", "designation_id"})
})
public class ClaimBehaviourSetting {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    Date circularDate;
    Date effectiveDate;

    Boolean blockPeriodRequire;
    Long blockPeriod;
    BlockPeriodicity blockPeriodicity;
    Long blockClaimFrequency;
    Double blockEligibilityAmount;
    Boolean blockSplitInSubType;
    Boolean spanPeriodRequire;
    Long spanPeriod;
    SpanPeriodicity spanPeriodicity;
    Long spanClaimFrequency;
    Double spanEligibilityAmount;
    Boolean spanSplitInSubType;
    Boolean proRataApplication;
    Boolean balanceRequire;
    Boolean balanceCF;
    Boolean advanceApplicable;
    Boolean restrictMoreThanEligibleAmount;
    Double approvalMinAmount;
    Double approvalMaxAmount;
    Long addedById;
    Long updatedById;
    Date addedOn;
    Date updatedOne;

    @Column(nullable = false)
    Boolean status;

    public ClaimBehaviourSetting(Long claimBehaviourSettingId) {
        this.id = claimBehaviourSettingId;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="appointment_nature_id", nullable = false, referencedColumnName = "id", insertable =  true, updatable = true)
    private AppointmentNature appointmentNature;

    @Transient
    Long appointmentNatureId;
    public void setAppointmentNatureId(Long appointmentNatureId){
        this.appointmentNature = Objects.nonNull(appointmentNatureId) ? new AppointmentNature(appointmentNatureId) : null;
    }
    public Long getAppointmentNatureId(){
        return Objects.nonNull(this.appointmentNature) ? this.appointmentNature.getId() : null;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="cadre_id", nullable = false, referencedColumnName = "id", insertable =  true, updatable = true)
    private Cadre cadre;

    @Transient
    Long cadreId;
    public void setCadreId(Long cadreId){
        this.cadre = Objects.nonNull(cadreId) ? new Cadre(cadreId) : null;
    }
    public Long getCadreId(){
        return Objects.nonNull(this.cadre) ? this.cadre.getId() : null;
    }
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="designation_id", nullable = false, referencedColumnName = "id", insertable =  true, updatable = true)
    private Designation designation;

    @Transient
    Long designationId;
    public void setDesignationId(Long designationId){
        this.designation = Objects.nonNull(designationId) ? new Designation(designationId) : null;
    }
    public Long getDesignationId(){
        return Objects.nonNull(this.designation) ? this.designation.getId() : null;
    }


}
