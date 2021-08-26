package com.mnit.erp.notification.model;

import com.mnit.erp.role.model.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Notification {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    String title;
    String content;
    String targetLink;
    String fileName;

    NotificationType notificationType;

    Date addedOn;
    Long addedBy;

    Long userId;

    @ManyToMany
    List<Role> roles;

    @Transient
    List<Long> roleIds;

    public List<Long> getRoleIds() {
        if(Objects.nonNull(this.roles)){
            return this.roles.stream().map(Role::getId).collect(Collectors.toList());
        }
        return roleIds;
    }

    public void setRoleIds(List<Long> roleIds) {
        if(Objects.nonNull(roleIds)) {
            this.roles = roleIds.stream().map(Role::new).collect(Collectors.toList());
        }
        this.roleIds = roleIds;
    }

    @ManyToMany
    List<NotificationCategory> notificationCategories;

    @Transient
    List<Long> notificationCategoryIds;

    public List<Long> getNotificationCategoryIds() {
        if(Objects.nonNull(this.notificationCategories)){
            return this.notificationCategories.stream().map(NotificationCategory::getId).collect(Collectors.toList());
        }
        return notificationCategoryIds;
    }

    public void setNotificationCategoryIds(List<Long> notificationCategoryIds) {
        if(Objects.nonNull(notificationCategoryIds)){
            this.notificationCategories = notificationCategoryIds.stream().map(NotificationCategory::new).collect(Collectors.toList());
        }
        this.notificationCategoryIds = notificationCategoryIds;
    }


}
