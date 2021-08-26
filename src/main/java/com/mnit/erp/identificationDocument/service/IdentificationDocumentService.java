package com.mnit.erp.identificationDocument.service;

import com.mnit.erp.identificationDocument.model.IdentificationDocument;
import com.mnit.erp.identificationDocument.repository.IdentificationDocumentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class IdentificationDocumentService {

    @Autowired
    IdentificationDocumentRepository identificationDocumentRepository;

}
