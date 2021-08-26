package com.mnit.erp.notification.service;

import com.mnit.erp.notification.model.Notification;
import com.mnit.erp.role.model.Role;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NotificationRepository extends JpaRepository<Notification, Long> {
    Page<Notification> findAllByUserIdOrRolesIn(Long userId, List<Role> roles, Pageable pageable);

    Page<Notification> findAllByAddedBy(Long userId, Pageable pageable);
}
