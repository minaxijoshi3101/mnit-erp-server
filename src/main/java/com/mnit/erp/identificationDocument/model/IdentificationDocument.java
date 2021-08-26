package com.mnit.erp.identificationDocument.model;

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
public class IdentificationDocument {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    IdentificationDocumentType identificationDocumentType;

    String documentNumber;
    Date validFrom;
    Date validThrough;
    String remarks;

}
