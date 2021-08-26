package com.mnit.erp.document.controller;

import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mnit.erp.AbstractController;
import com.mnit.erp.document.model.Document;
import com.mnit.erp.document.service.DocumentService;
import com.mnit.erp.response.CustomResponseMessage;
import com.mnit.erp.util.ResponseMessageType;

@RestController
@RequestMapping("/document")
public class DocumentController extends AbstractController {

    @Autowired
    DocumentService documentService;

    @PostMapping("add")
    public CustomResponseMessage saveDocumentInfo(@RequestBody Document document){
        document = this.documentService.add(document);
        ResponseMessageType responseMessageType = Objects.nonNull(document) ? ResponseMessageType.SUCCESS : ResponseMessageType.ERROR;
        return CustomResponseMessage.builder().message(Objects.nonNull(document) ? "Document added successfully!" : "Unable to add document!")
                .messageType(responseMessageType)
                .response(document).build();
    }

    @PutMapping("update")
    public CustomResponseMessage updateDocumentInfo(@RequestBody Document document){
        document = this.documentService.update(document);
        ResponseMessageType responseMessageType = Objects.nonNull(document) ? ResponseMessageType.SUCCESS : ResponseMessageType.ERROR;
        return CustomResponseMessage.builder().message(Objects.nonNull(document) ? "Document updated successfully!" : "Unable to update document!")
                .messageType(responseMessageType)
                .response(document).build();
    }

    @GetMapping("/find/{id}")
    public CustomResponseMessage find(@PathVariable Long id){
        Document document = this.documentService.find(id);
        ResponseMessageType responseMessageType = Objects.nonNull(document) ? ResponseMessageType.SUCCESS : ResponseMessageType.ERROR;
        return CustomResponseMessage.builder().message(Objects.nonNull(document) ? "Document found successfully!" : "Unable to find document!")
                .messageType(responseMessageType)
                .response(document).build();
    }
    @GetMapping("/findAll")
    public CustomResponseMessage findAll(){
        List<Document> document = this.documentService.findALL();
        ResponseMessageType responseMessageType = Objects.nonNull(document) ? ResponseMessageType.SUCCESS : ResponseMessageType.ERROR;
        return CustomResponseMessage.builder().message(Objects.nonNull(document) ? "Document found successfully!" : "Unable to find document!")
                .messageType(responseMessageType)
                .response(document).build();
    }
    
}
