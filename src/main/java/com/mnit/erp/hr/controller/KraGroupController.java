package com.mnit.erp.hr.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mnit.erp.hr.model.Kra;
import com.mnit.erp.hr.model.KraGroup;
import com.mnit.erp.hr.service.KraGroupService;
import com.mnit.erp.response.CustomResponseMessage;

@RestController
@RequestMapping("/kragroup")
public class KraGroupController {

    @Autowired
    KraGroupService kraGroupService;

    @PostMapping("/add")
    public CustomResponseMessage add(@RequestBody KraGroup kraGroupObj) {
        return kraGroupService.addOrUpdate(kraGroupObj);
    }

    @GetMapping
    public CustomResponseMessage findAll(){
        return kraGroupService.findAll();
    }

    @GetMapping("/{id}")
    public CustomResponseMessage findById(@PathVariable(value="id") Long id){
        return kraGroupService.findById(id);
    }

    @PutMapping("/update/{id}")
    public CustomResponseMessage update(@PathVariable(value="id") Long id, @RequestBody KraGroup kraGroupObj) {
    	kraGroupObj.setId(id);
        return kraGroupService.addOrUpdate(kraGroupObj);
    }
}
