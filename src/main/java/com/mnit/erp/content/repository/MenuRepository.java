package com.mnit.erp.content.repository;

import com.mnit.erp.content.model.Menu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MenuRepository extends JpaRepository<Menu, Long> {
    List<Menu> findAllByModuleId(Long moduleId);

    List<Menu> findAllByModuleIdAndParentIdIsNull(Long moduleId);
}
