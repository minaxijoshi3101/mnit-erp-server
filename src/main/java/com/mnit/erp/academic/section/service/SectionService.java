package com.mnit.erp.academic.section.service;

import com.mnit.erp.academic.section.model.Section;
import com.mnit.erp.academic.section.repository.SectionRepository;
import com.mnit.erp.exceptions.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class SectionService {

    @Autowired
    SectionRepository sectionRepository;

    public List<Section> findAll() {
        return this.sectionRepository.findAll();
    }

    public Section add(Section section) {
        Section byName = this.sectionRepository.findByName(section.getName());
        if (Objects.nonNull(byName)) {
            throw new ServiceException("Section : " + section.getName() + " already exists. Can't add new!");
        }
        return this.sectionRepository.save(section);
    }
}
