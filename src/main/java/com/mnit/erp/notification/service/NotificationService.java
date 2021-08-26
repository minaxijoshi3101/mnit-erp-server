package com.mnit.erp.notification.service;

import com.mnit.erp.exceptions.ServiceException;
import com.mnit.erp.notification.model.Notification;
import com.mnit.erp.notification.model.NotificationCategory;
import com.mnit.erp.notification.model.NotificationType;
import com.mnit.erp.notification.repository.NotificationCategoryRepository;
import com.mnit.erp.role.model.Role;
import com.mnit.erp.role.repository.RoleRepository;
import com.mnit.erp.security.CurrentUser;
import com.mnit.erp.user.service.UserRoleService;
import com.mnit.erp.util.CommonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Objects;

@Service
public class NotificationService {

    @Autowired
    NotificationRepository notificationRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    UserRoleService userRoleService;

    @Autowired
    NotificationCategoryRepository notificationCategoryRepository;

    public Notification add(Notification notification) {
        if(this.validate(notification)){
            notification.setAddedBy(CurrentUser.getCurrentInMemoryUser().getId());
            notification.setAddedOn(new Date());
            notification.setNotificationType(NotificationType.USER_GENERATED);
            return this.notificationRepository.save(notification);
        }
        return null;
    }

    private boolean validate(Notification notification) {
        // check role(s)
        if(Objects.isNull(notification.getRoleIds()) || notification.getRoleIds().isEmpty()){
            throw new ServiceException("You must select group(s) for which notification is being published");
        }
        List<Role> roles = this.roleRepository.findAllByIdIn(notification.getRoleIds());
        if(roles.size() != notification.getRoleIds().size()){
            throw new ServiceException("Invalid role(s) requested. Can't publish notification");
        }
        notification.setRoles(roles);
        // check notification categories
        List<NotificationCategory> notificationCategories = this.notificationCategoryRepository.findAllByIdIn(notification.getNotificationCategoryIds());
        if(notificationCategories.size() != notification.getNotificationCategoryIds().size()){
            throw new ServiceException("Invalid notification category provided. Please check!");
        }
        notification.setNotificationCategories(notificationCategories);
        return true;
    }

    public Notification update(Notification notification) {
        if(this.validate(notification)){
            Notification notification1 = this.notificationRepository.findById(notification.getId()).orElse(null);
            CommonUtils.copyNonNullProperties(notification, notification1);
            return this.notificationRepository.save(notification1);
        }
        return null;
    }

    public Notification find(Long id) {
        return this.notificationRepository.findById(id).orElse(null);
    }

    public Page<Notification> findNotificationsOfUser(Long userId, Pageable pageable) {
        List<Role> roles = this.userRoleService.findAllRolesOfUser(userId);
        return this.notificationRepository.findAllByUserIdOrRolesIn(userId, roles, pageable);
    }

    public Page<Notification> findNotificationsPublishedByUser(Long userId, Pageable pageable) {
        return this.notificationRepository.findAllByAddedBy(userId, pageable);
    }

    public Notification publishNotification(String title, String content, Long userId, Long addedBy){
        Notification notification = Notification.builder().title(title)
                .content(content)
                .userId(userId)
                .addedOn(new Date())
                .addedBy(addedBy)
                .notificationType(NotificationType.SYSTEM_GENERATED)
                .build();
        if(this.validate(notification)){
            return this.notificationRepository.save(notification);
        }
        return null;
    }

}
