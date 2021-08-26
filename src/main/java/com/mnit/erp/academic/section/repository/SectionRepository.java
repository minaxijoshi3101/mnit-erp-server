package com.mnit.erp.academic.section.repository;

import com.mnit.erp.academic.section.model.Section;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SectionRepository extends JpaRepository<Section, Long> {
    Section findByName(String name);
}
