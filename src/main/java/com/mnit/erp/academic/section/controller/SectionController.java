package com.mnit.erp.academic.section.controller;

import com.mnit.erp.AbstractController;
import com.mnit.erp.academic.section.model.Section;
import com.mnit.erp.academic.section.service.SectionService;
import com.mnit.erp.response.CustomResponseMessage;
import com.mnit.erp.util.ResponseMessageType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/section")
public class SectionController extends AbstractController {

    @Autowired
    SectionService sectionService;

    @GetMapping
    public CustomResponseMessage findAll(){
        List<Section> sections = this.sectionService.findAll();
        ResponseMessageType responseMessageType = Objects.nonNull(sections) ? ResponseMessageType.SUCCESS : ResponseMessageType.ERROR;
        return CustomResponseMessage.builder().message(Objects.nonNull(sections) && sections.size() > 0 ? "Sections found in masters!" : "Unable to find sections in masters!")
                .messageType(responseMessageType)
                .response(sections).build();
    }

}
