package com.mnit.erp.document.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mnit.erp.document.model.Document;

@Repository
public interface DocumentRepository extends JpaRepository<Document, Long> {
}
