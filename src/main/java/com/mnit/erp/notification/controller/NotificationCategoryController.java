package com.mnit.erp.notification.controller;

import com.mnit.erp.AbstractController;
import com.mnit.erp.notification.model.NotificationCategory;
import com.mnit.erp.notification.service.NotificationCategoryService;
import com.mnit.erp.response.CustomResponseMessage;
import com.mnit.erp.util.ResponseMessageType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/manage-notification-categories")
public class NotificationCategoryController extends AbstractController {

    @Autowired
    NotificationCategoryService notificationCategoryService;

    @PostMapping("add")
    public CustomResponseMessage add(@RequestBody NotificationCategory notificationCategory){
        NotificationCategory category1 = this.notificationCategoryService.add(notificationCategory);
        ResponseMessageType responseMessageType = Objects.nonNull(category1) ? ResponseMessageType.SUCCESS : ResponseMessageType.ERROR;
        return CustomResponseMessage.builder().message(Objects.nonNull(category1) ? "Notification Category added successfully in masters!" : "Unable to add notification category in masters!")
                .messageType(responseMessageType)
                .response(category1).build();
    }

    @PutMapping("update")
    public CustomResponseMessage update(@RequestBody NotificationCategory notificationCategory){
        NotificationCategory category1 = this.notificationCategoryService.update(notificationCategory);
        ResponseMessageType responseMessageType = Objects.nonNull(category1) ? ResponseMessageType.SUCCESS : ResponseMessageType.ERROR;
        return CustomResponseMessage.builder().message(Objects.nonNull(category1) ? "Notification Category updated successfully in masters!" : "Unable to update notification category in masters!")
                .messageType(responseMessageType)
                .response(category1).build();
    }

    @GetMapping("find/{id}")
    public CustomResponseMessage find(@PathVariable Long id){
        NotificationCategory category1 = this.notificationCategoryService.find(id);
        ResponseMessageType responseMessageType = Objects.nonNull(category1) ? ResponseMessageType.SUCCESS : ResponseMessageType.ERROR;
        return CustomResponseMessage.builder().message(Objects.nonNull(category1) ? "Notification Category found successfully in masters!" : "Unable to find notification category in masters!")
                .messageType(responseMessageType)
                .response(category1).build();
    }

    @GetMapping("findAll")
    public CustomResponseMessage findAll(){
        List<NotificationCategory> notificationCategories = this.notificationCategoryService.findAll();
        ResponseMessageType responseMessageType = Objects.nonNull(notificationCategories) ? ResponseMessageType.SUCCESS : ResponseMessageType.ERROR;
        return CustomResponseMessage.builder().message(Objects.nonNull(notificationCategories) && !notificationCategories.isEmpty() ? "Notification Categories found successfully in masters!" : "Unable to find notification category in masters!")
                .messageType(responseMessageType)
                .response(notificationCategories).build();
    }

}
