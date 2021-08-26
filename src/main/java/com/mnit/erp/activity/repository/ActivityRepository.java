package com.mnit.erp.activity.repository;

import com.mnit.erp.activity.model.Activity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ActivityRepository extends JpaRepository<Activity, Long> {

    Activity findByName(String name);
}
