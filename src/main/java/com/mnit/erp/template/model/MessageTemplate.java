package com.mnit.erp.template.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class MessageTemplate {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String name;
    String description;
    String content;
    Date addedOn;
    Long addedBy;
    Boolean active;
    MessageTemplateType messageTemplateType;
}
