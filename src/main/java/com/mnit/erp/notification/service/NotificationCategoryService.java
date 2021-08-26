package com.mnit.erp.notification.service;

import com.mnit.erp.exceptions.ServiceException;
import com.mnit.erp.notification.model.NotificationCategory;
import com.mnit.erp.notification.repository.NotificationCategoryRepository;
import com.mnit.erp.security.CurrentUser;
import com.mnit.erp.util.CommonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Objects;

@Service
public class NotificationCategoryService {

    @Autowired
    NotificationCategoryRepository notificationCategoryRepository;

    public NotificationCategory add(NotificationCategory notificationCategory) {
        NotificationCategory notificationCategory1 = notificationCategoryRepository.findByName(notificationCategory.getName());
        if(Objects.nonNull(notificationCategory1)){
            throw new ServiceException("Notification Category : "+ notificationCategory.getName() + " already exists. Can't add new!");
        }
        if(this.validate(notificationCategory)){
            notificationCategory.setAddedBy(CurrentUser.getCurrentInMemoryUser().getId());
            notificationCategory.setAddedOn(new Date());
            return this.notificationCategoryRepository.save(notificationCategory);
        }
        return null;
    }

    private boolean validate(NotificationCategory notificationCategory) {
        return true;
    }

    public NotificationCategory update(NotificationCategory notificationCategory) {
        if(Objects.nonNull(notificationCategory.getName())){
            NotificationCategory byName = this.notificationCategoryRepository.findByName(notificationCategory.getName());
            if(Objects.nonNull(byName) && notificationCategory.getId() != byName.getId()){
                throw new ServiceException("Can't update with name : " + notificationCategory.getName() + ". Name already exists!");
            }
        }
        if(this.validate(notificationCategory)){
            NotificationCategory savedNotificationCategory = this.notificationCategoryRepository.findById(notificationCategory.getId()).orElse(null);
            // Don't allow update of fields : addedOn and addedBy
            notificationCategory.setAddedOn(null);
            notificationCategory.setAddedBy(null);
            CommonUtils.copyNonNullProperties(notificationCategory, savedNotificationCategory);
            return this.notificationCategoryRepository.save(savedNotificationCategory);
        }
        return null;
    }

    public NotificationCategory find(Long id) {
        return this.notificationCategoryRepository.findById(id).orElse(null);
    }

    public List<NotificationCategory> findAll() {
        return this.notificationCategoryRepository.findAll();
    }

}
