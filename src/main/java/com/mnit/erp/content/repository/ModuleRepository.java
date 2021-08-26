package com.mnit.erp.content.repository;

import com.mnit.erp.content.model.Module;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ModuleRepository extends JpaRepository<Module, Long> {
    List<Module> findByActive(boolean b);
}
