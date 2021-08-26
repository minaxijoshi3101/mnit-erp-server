package com.mnit.erp.content.controller;

import com.mnit.erp.AbstractController;
import com.mnit.erp.content.model.Menu;
import com.mnit.erp.content.model.Module;
import com.mnit.erp.content.service.ContentService;
import com.mnit.erp.response.CustomResponseMessage;
import com.mnit.erp.security.CurrentUser;
import com.mnit.erp.util.ResponseMessageType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/content")
public class ContentController extends AbstractController {

    @Autowired
    ContentService contentService;


    @GetMapping("/modules")
    public CustomResponseMessage findAllModules() {
        List<Module> modules = this.contentService.findAllModules();
        ResponseMessageType responseMessageType = Objects.nonNull(modules) ? ResponseMessageType.SUCCESS : ResponseMessageType.ERROR;
        return CustomResponseMessage.builder().message(Objects.nonNull(modules) && modules.size() > 0 ? "Modules found !" : "No modules found!")
        .messageType(responseMessageType)
        .response(modules).build();
    }

    @GetMapping("/activeModules")
    public CustomResponseMessage findAllActiveModules(){
        List<Module> modules = this.contentService.findAllActiveModules();
        ResponseMessageType responseMessageType = Objects.nonNull(modules) ? ResponseMessageType.SUCCESS : ResponseMessageType.ERROR;
        return CustomResponseMessage.builder().message(Objects.nonNull(modules) && modules.size() > 0 ? "Active modules found !" : "No active modules found!")
                .messageType(responseMessageType)
                .response(modules).build();
    }

    @GetMapping("/menuTreeOfModule/{moduleId}")
    public CustomResponseMessage findMenuTreeOfModule(@PathVariable Long moduleId){
        List<Menu> menuTree = this.contentService.findMenuTreeOfModule(moduleId);
//        List<Menu> menuTree = this.contentService.findAccessibleMenuTreeOfModuleToUser(moduleId, CurrentUser.getCurrentInMemoryUser().getId());
        ResponseMessageType responseMessageType = Objects.nonNull(menuTree) ? ResponseMessageType.SUCCESS : ResponseMessageType.ERROR;
        return CustomResponseMessage.builder().message(Objects.nonNull(menuTree) && menuTree.size() > 0 ? "Menu Tree of module found !" : "No menu tree under modules found!")
                .messageType(responseMessageType)
                .response(menuTree).build();
    }

    @GetMapping("/listMenusInModule/{moduleId}")
    public CustomResponseMessage listMenusInModule(@PathVariable Long moduleId){
        List<Menu> menuTree = this.contentService.listMenusInModule(moduleId);
        ResponseMessageType responseMessageType = Objects.nonNull(menuTree) ? ResponseMessageType.SUCCESS : ResponseMessageType.ERROR;
        return CustomResponseMessage.builder().message(Objects.nonNull(menuTree) && menuTree.size() > 0 ? "Menu Tree of module found !" : "No menu tree under modules found!")
                .messageType(responseMessageType)
                .response(menuTree).build();
    }

    @PostMapping("/menu")
    public CustomResponseMessage addMenu(@RequestBody Menu menu){
        Menu menu1 = this.contentService.addMenu(menu);
        ResponseMessageType responseMessageType = Objects.nonNull(menu1) ? ResponseMessageType.SUCCESS : ResponseMessageType.ERROR;
        return CustomResponseMessage.builder().message(Objects.nonNull(menu1) ? "Menu added successfully!" : "Unable to add menu!")
                .messageType(responseMessageType)
                .response(menu1).build();
    }

    @PutMapping("/menu")
    public CustomResponseMessage updateMenu(@RequestBody Menu menu){
        Menu menu1 = this.contentService.updateMenu(menu);
        ResponseMessageType responseMessageType = Objects.nonNull(menu1) ? ResponseMessageType.SUCCESS : ResponseMessageType.ERROR;
        return CustomResponseMessage.builder().message(Objects.nonNull(menu1) ? "Menu updated successfully!" : "Unable to update menu!")
                .messageType(responseMessageType)
                .response(menu1).build();
    }

}
