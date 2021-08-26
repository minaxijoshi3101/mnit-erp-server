package com.mnit.erp.common.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mnit.erp.common.model.SlotOpen;
import com.mnit.erp.common.service.SlotOpenService;
import com.mnit.erp.response.CustomResponseMessage;

@RestController
@RequestMapping("/slotopen")
public class SlotOpenController {

    @Autowired
    SlotOpenService slotOpenlService;

    @GetMapping("/{id}")
    public CustomResponseMessage findById(@PathVariable Long id){
        return slotOpenlService.findById(id);
    }
    @GetMapping("/findAll")
    public CustomResponseMessage findAll(){
        return slotOpenlService.findAll();
    }
    
    @PostMapping("/findAsParam")
    public CustomResponseMessage findAsParam(@RequestBody SlotOpen  slotOpen){
        return slotOpenlService.findAsParam(slotOpen);
    }
    
    @PostMapping
    public CustomResponseMessage add(@RequestBody SlotOpen slotOpen){
        return slotOpenlService.addOrUpdate(slotOpen);
    }
    @PutMapping
    public CustomResponseMessage update(@RequestBody SlotOpen slotOpen){
        return slotOpenlService.addOrUpdate(slotOpen);
    }
}
