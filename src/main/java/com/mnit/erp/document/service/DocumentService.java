package com.mnit.erp.document.service;

import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mnit.erp.document.model.Document;
import com.mnit.erp.document.repository.DocumentRepository;
import com.mnit.erp.exceptions.ServiceException;
import com.mnit.erp.util.CommonUtils;

@Service
public class DocumentService {

    @Autowired
    DocumentRepository documentRepository;

	/*
	 * @Autowired DocumentTypeRepository documentTypeRepository;
	 */
    public Document add(Document document) {
        if(this.validate(document)){
            document = this.documentRepository.save(document);
            return document;
        }
        return null;
    }

    private boolean validate(Document document) {
        return true;
    }

    public Document update(Document document) {
        Document savedDocument = this.documentRepository.findById(document.getId()).orElse(null);
        if(Objects.isNull(savedDocument)){
            throw new ServiceException("Invalid document ID. Can't update!");
        }
        if(this.validate(document)){
            CommonUtils.copyNonNullProperties(document, savedDocument);
            savedDocument = this.documentRepository.save(savedDocument);
            return savedDocument;
        }
        return null;
    }

    public Document find(Long id) {
        Document document = this.documentRepository.findById(id).orElse(null);
        return document;
    }

    public List<Document> findALL() {
    	List<Document> documents = this.documentRepository.findAll();
        return documents;
    }

}
