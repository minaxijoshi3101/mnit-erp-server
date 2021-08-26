package com.mnit.erp.content.service;

import com.mnit.erp.content.exception.MenuParentRequiredException;
import com.mnit.erp.content.model.Menu;
import com.mnit.erp.content.model.Module;
import com.mnit.erp.content.repository.MenuRepository;
import com.mnit.erp.content.repository.ModuleRepository;
import com.mnit.erp.exceptions.ServiceException;
import com.mnit.erp.role.permissions.model.MenuUserAccess;
import com.mnit.erp.role.permissions.service.UserRolePermissionService;
import com.mnit.erp.user.service.UserRoleService;
import com.mnit.erp.util.CommonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class ContentServiceImpl implements ContentService{

    @Autowired
    ModuleRepository moduleRepository;

    @Autowired
    MenuRepository menuRepository;

    @Autowired
    UserRoleService userRoleService;

    @Autowired
    UserRolePermissionService userRolePermissionService;

    @Override
    public List<Module> findAllModules() {
        return this.moduleRepository.findAll();
    }

    @Override
    public List<Module> findAllActiveModules() {
        return this.moduleRepository.findByActive(true);
    }

    @Override
    public List<Menu> findMenuTreeOfModule(Long moduleId) {
        return menuRepository.findAllByModuleIdAndParentIdIsNull(moduleId);
    }

    @Override
    public Menu addMenu(Menu menu) {
        if(this.validateMenu(menu)){
            return this.menuRepository.save(menu);
        }
        return null;
    }

    @Override
    public Menu updateMenu(Menu menu) {
        Menu menu1 = this.menuRepository.findById(menu.getId()).orElse(null);
        if(Objects.isNull(menu1)){
            throw new ServiceException("Menu not found. Can't update");
        }
        if(this.validateMenu(menu)){
            menu1.setChildren(null);
            CommonUtils.copyNonNullProperties(menu, menu1);
            return this.menuRepository.save(menu);
        }
        return null;
    }

    @Override
    public List<Menu> listMenusInModule(Long moduleId) {
        List<Menu> menus = menuRepository.findAllByModuleId(moduleId);
        menus.forEach(menu -> menu.setChildren(Arrays.asList()));
        return menus;
    }

    private boolean validateMenu(Menu menu){
//        if(Objects.nonNull(menu.getModuleId()) && Objects.nonNull(menu.getParentId())){
//            throw new ServiceException("A menu can't have parent module and parent menu both together!");
//        }

        if(Objects.isNull(menu.getModuleId()) && Objects.isNull(menu.getParentId())){
            throw new MenuParentRequiredException("Menu must be linked under module or should have a parent menu!");
        }
        return true;
    }

    public List<Menu> findAccessibleMenuTreeOfModuleToUser(Long moduleId, Long userId){
        List<MenuUserAccess> allPermissionsOfUser = this.userRolePermissionService.findAllPermissionsOfUser(userId);
        // FIND complete menu tree of module
        List<Menu> menuTree = this.findMenuTreeOfModule(moduleId);
        // retain accessible only menus in the tree up to leaf nodes including all in the path from root to leaf.
        List<Long> permittedMenuIds = allPermissionsOfUser.stream().map(MenuUserAccess::getMenuId).collect(Collectors.toList());
        Menu rootMenu = new Menu();
        rootMenu.setChildren(menuTree);
        this.retainAccessibleMenusInMenuTree(rootMenu, permittedMenuIds);
        return rootMenu.getChildren();
    }

    private void retainAccessibleMenusInMenuTree(Menu rootMenu, List<Long> permittedMenuIds){
        // TODO : traverse menu tree and retains nodes (menus) are under permissions

        Iterator<Menu> iterator = rootMenu.getChildren().iterator();
        while(iterator.hasNext()){
            Menu menu = iterator.next();

            if(menu.getChildren().size() > 0){
                this.retainAccessibleMenusInMenuTree(menu, permittedMenuIds);
            }
            else if(menu.getChildren().size() == 0 && !permittedMenuIds.contains(menu.getId())){
                iterator.remove();
            }
        }
    }

}
