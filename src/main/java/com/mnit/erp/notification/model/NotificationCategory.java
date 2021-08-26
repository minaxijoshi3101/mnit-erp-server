package com.mnit.erp.notification.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class NotificationCategory {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String name;
    String title;
    String description;
    Boolean active;
    Long displayOrder;
    Date addedOn;
    Long addedBy;

    public NotificationCategory(String name, String title, String description, Boolean active, Long displayOrder) {
        this.name = name;
        this.title = title;
        this.description = description;
        this.active = active;
        this.displayOrder = displayOrder;
    }

    public NotificationCategory(Long id) {
        this.id = id;
    }

}
