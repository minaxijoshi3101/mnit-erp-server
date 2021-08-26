package com.mnit.erp.template.service;

import com.mnit.erp.template.model.MessageTemplate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MessageTemplateRepository extends JpaRepository<MessageTemplate, Long> {
    MessageTemplate findByName(String name);
}
