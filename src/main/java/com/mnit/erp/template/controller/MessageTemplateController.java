package com.mnit.erp.template.controller;

import com.mnit.erp.AbstractController;
import com.mnit.erp.response.CustomResponseMessage;
import com.mnit.erp.template.model.MessageTemplate;
import com.mnit.erp.template.service.MessageTemplateService;
import com.mnit.erp.util.ResponseMessageType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

@RestController
@RequestMapping("manage-message-templates")
public class MessageTemplateController extends AbstractController {

    @Autowired
    MessageTemplateService messageTemplateService;

    @PostMapping("add")
    public CustomResponseMessage add(@RequestBody MessageTemplate messageTemplate){
        MessageTemplate template = this.messageTemplateService.add(messageTemplate);
        ResponseMessageType responseMessageType = Objects.nonNull(template) ? ResponseMessageType.SUCCESS : ResponseMessageType.ERROR;
        return CustomResponseMessage.builder().message(Objects.nonNull(template) ? "Message Template added successfully!" : "Unable to add message template!")
                .messageType(responseMessageType)
                .response(template).build();
    }

    @PutMapping("update")
    public CustomResponseMessage update(@RequestBody MessageTemplate messageTemplate){
        MessageTemplate template = this.messageTemplateService.update(messageTemplate);
        ResponseMessageType responseMessageType = Objects.nonNull(template) ? ResponseMessageType.SUCCESS : ResponseMessageType.ERROR;
        return CustomResponseMessage.builder().message(Objects.nonNull(template) ? "Message Template updated successfully!" : "Unable to update message template!")
                .messageType(responseMessageType)
                .response(template).build();
    }

    @GetMapping("find/{id}")
    public CustomResponseMessage findById(@PathVariable Long id){
        MessageTemplate template = this.messageTemplateService.find(id);
        ResponseMessageType responseMessageType = Objects.nonNull(template) ? ResponseMessageType.SUCCESS : ResponseMessageType.ERROR;
        return CustomResponseMessage.builder().message(Objects.nonNull(template) ? "Message Template found successfully!" : "Unable to find message template!")
                .messageType(responseMessageType)
                .response(template).build();
    }

    @GetMapping("findByName/{name}")
    public CustomResponseMessage findByName(@PathVariable String name){
        MessageTemplate template = this.messageTemplateService.findByName(name);
        ResponseMessageType responseMessageType = Objects.nonNull(template) ? ResponseMessageType.SUCCESS : ResponseMessageType.ERROR;
        return CustomResponseMessage.builder().message(Objects.nonNull(template) ? "Message Template found successfully!" : "Unable to find message template!")
                .messageType(responseMessageType)
                .response(template).build();
    }

    @GetMapping("findAll")
    public CustomResponseMessage findAll(Pageable pageable){
        Page<MessageTemplate> templates = this.messageTemplateService.findAll(pageable);
        ResponseMessageType responseMessageType = Objects.nonNull(templates) ? ResponseMessageType.SUCCESS : ResponseMessageType.ERROR;
        return CustomResponseMessage.builder().message(Objects.nonNull(templates) && templates.getSize() > 0 ? "Message Templates found successfully!" : "Unable to find message template!")
                .messageType(responseMessageType)
                .response(templates).build();
    }

}
