/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mnit.erp.audit;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.mnit.erp.user.model.User;
import lombok.Data;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.LastModifiedBy;

import javax.persistence.*;
import java.util.Objects;

/**
 *
 * @author praha
 */
@Data
@MappedSuperclass
@JsonIgnoreProperties(
        value = {"createdById", "updatedById"},
        allowGetters = true
)
public abstract class UserDateAudit extends DateAudit {

    @CreatedBy
    @Transient
    private Long createdById;

    @LastModifiedBy
    @Transient
    private Long updatedById;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="created_by_id", referencedColumnName = "id", insertable =  true, updatable = true)
    private User createdBy;

    public Long getCreatedById() {
        return Objects.nonNull(this.createdBy) ? this.createdBy.getId() : null;
    }
    public void setCreatedById(Long createdById) {
        this.createdById = createdById;
        this.createdBy = Objects.nonNull(createdById) ? new User(createdById) : null;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="updated_by_id", referencedColumnName = "id", insertable =  true, updatable = true)
    private User updatedBy;

    public Long getUpdatedById() {
        return Objects.nonNull(this.updatedBy) ? this.updatedBy.getId() : null;
    }
    public void setUpdatedById(Long updatedById) {
        this.updatedById = updatedById;
        this.updatedBy = Objects.nonNull(updatedById) ? new User(updatedById) : null;
    }

}
