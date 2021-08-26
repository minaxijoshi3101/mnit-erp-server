package com.mnit.erp.content.service;

import com.mnit.erp.content.model.Menu;
import com.mnit.erp.content.model.Module;

import java.util.List;

public interface ContentService {

    List<Module> findAllModules();

    List<Module> findAllActiveModules();

    List<Menu> findMenuTreeOfModule(Long moduleId);

    Menu addMenu(Menu menu);

    Menu updateMenu(Menu menu);

    List<Menu> listMenusInModule(Long moduleId);

    List<Menu> findAccessibleMenuTreeOfModuleToUser(Long moduleId, Long userId);
}
