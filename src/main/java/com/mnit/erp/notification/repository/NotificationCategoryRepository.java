package com.mnit.erp.notification.repository;

import com.mnit.erp.notification.model.NotificationCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NotificationCategoryRepository extends JpaRepository<NotificationCategory, Long> {
    NotificationCategory findByName(String name);

    List<NotificationCategory> findAllByIdIn(List<Long> notificationCategoryIds);
}
