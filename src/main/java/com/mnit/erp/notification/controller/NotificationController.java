package com.mnit.erp.notification.controller;

import com.mnit.erp.AbstractController;
import com.mnit.erp.notification.model.Notification;
import com.mnit.erp.notification.service.NotificationService;
import com.mnit.erp.response.CustomResponseMessage;
import com.mnit.erp.security.CurrentUser;
import com.mnit.erp.user.model.User;
import com.mnit.erp.util.ResponseMessageType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("notifications")
public class NotificationController extends AbstractController {

    @Autowired
    NotificationService notificationService;

    @PostMapping("add")
    public CustomResponseMessage add(@RequestBody Notification notification){
        Notification notification1 = this.notificationService.add(notification);
        ResponseMessageType responseMessageType = Objects.nonNull(notification1) ? ResponseMessageType.SUCCESS : ResponseMessageType.ERROR;
        return CustomResponseMessage.builder().message(Objects.nonNull(notification1) ? "Notification published successfully!" : "Unable to publish notification!")
                .messageType(responseMessageType)
                .response(notification1).build();
    }

    @PutMapping("update")
    public CustomResponseMessage update(@RequestBody Notification notification){
        Notification notification1 = this.notificationService.update(notification);
        ResponseMessageType responseMessageType = Objects.nonNull(notification1) ? ResponseMessageType.SUCCESS : ResponseMessageType.ERROR;
        return CustomResponseMessage.builder().message(Objects.nonNull(notification1) ? "Notification updated successfully!" : "Unable to update notification!")
                .messageType(responseMessageType)
                .response(notification1).build();
    }

    @GetMapping("find/{id}")
    public CustomResponseMessage find(@PathVariable Long id){
        Notification notification1 = this.notificationService.find(id);
        ResponseMessageType responseMessageType = Objects.nonNull(notification1) ? ResponseMessageType.SUCCESS : ResponseMessageType.ERROR;
        return CustomResponseMessage.builder().message(Objects.nonNull(notification1) ? "Notification found successfully!" : "Unable to find notification!")
                .messageType(responseMessageType)
                .response(notification1).build();
    }

    @GetMapping("myNotifications")
    public CustomResponseMessage myNotifications(Pageable pageable){
        Page<Notification> notificationsOfUser = this.notificationService.findNotificationsOfUser(CurrentUser.getCurrentInMemoryUser().getId(), pageable);
        ResponseMessageType responseMessageType = Objects.nonNull(notificationsOfUser) ? ResponseMessageType.SUCCESS : ResponseMessageType.ERROR;
        return CustomResponseMessage.builder().message(Objects.nonNull(notificationsOfUser) && notificationsOfUser.getSize() > 0 ? "Notification of user found successfully!" : "Unable to find user notification!")
                .messageType(responseMessageType)
                .response(notificationsOfUser).build();
    }

    @GetMapping("myPublishedNotifications")
    public CustomResponseMessage myPublishedNotifications(Pageable pageable){
        Page<Notification> notificationsOfUser = this.notificationService.findNotificationsPublishedByUser(CurrentUser.getCurrentInMemoryUser().getId(), pageable);
        ResponseMessageType responseMessageType = Objects.nonNull(notificationsOfUser) ? ResponseMessageType.SUCCESS : ResponseMessageType.ERROR;
        return CustomResponseMessage.builder().message(Objects.nonNull(notificationsOfUser) && notificationsOfUser.getSize() > 0 ? "Notification published by you found successfully!" : "Unable to find your published!")
                .messageType(responseMessageType)
                .response(notificationsOfUser).build();
    }

}
