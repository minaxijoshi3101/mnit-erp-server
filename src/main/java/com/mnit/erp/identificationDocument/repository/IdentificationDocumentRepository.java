package com.mnit.erp.identificationDocument.repository;

import com.mnit.erp.identificationDocument.model.IdentificationDocument;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IdentificationDocumentRepository extends JpaRepository<IdentificationDocument, Long> {
}
