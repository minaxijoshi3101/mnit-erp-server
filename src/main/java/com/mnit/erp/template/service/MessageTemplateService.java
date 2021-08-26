package com.mnit.erp.template.service;

import com.mnit.erp.exceptions.ServiceException;
import com.mnit.erp.security.CurrentUser;
import com.mnit.erp.template.model.MessageTemplate;
import com.mnit.erp.util.CommonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Objects;

@Service
public class MessageTemplateService {

    @Autowired
    MessageTemplateRepository messageTemplateRepository;

    public MessageTemplate add(MessageTemplate messageTemplate) {
        if(this.validate(messageTemplate)){
            messageTemplate.setAddedOn(new Date());
            messageTemplate.setAddedBy(CurrentUser.getCurrentInMemoryUser().getId());
            return this.messageTemplateRepository.save(messageTemplate);
        }
        return null;
    }

    private boolean validate(MessageTemplate messageTemplate) {
        return true;
    }

    public MessageTemplate update(MessageTemplate messageTemplate) {
        MessageTemplate messageTemplate1 = this.messageTemplateRepository.findByName(messageTemplate.getName());
        if(Objects.nonNull(messageTemplate1)){
            throw new ServiceException("Message Template : " + messageTemplate.getName() + " already exists!");
        }
        if(this.validate(messageTemplate)){
            MessageTemplate messageTemplate2 = this.messageTemplateRepository.findById(messageTemplate.getId()).orElse(null);
            if(Objects.isNull(messageTemplate2)){
                throw new ServiceException("Unable to update. Record doesn't exist with ID!" + messageTemplate.getId());
            }
            CommonUtils.copyNonNullProperties(messageTemplate, messageTemplate2);
            return this.messageTemplateRepository.save(messageTemplate2);
        }
        return null;
    }

    public MessageTemplate find(Long id) {
        return this.messageTemplateRepository.findById(id).orElse(null);
    }

    public MessageTemplate findByName(String name) {
        return this.messageTemplateRepository.findByName(name);
    }

    public Page<MessageTemplate> findAll(Pageable pageable) {
        return this.messageTemplateRepository.findAll(pageable);
    }

}
