package com.mnit.erp.activity.model;

import com.mnit.erp.template.model.MessageTemplate;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Activity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String name;
    String description;
    Date addedOn;
    Long addedBy;
    Boolean active;

    // mapping with MessageTemplate
    @OneToOne
    MessageTemplate messageTemplate;

    @Transient
    Long messageTemplateId;

}
