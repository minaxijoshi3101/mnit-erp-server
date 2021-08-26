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
import com.mnit.erp.hr.service.KraService;
import com.mnit.erp.response.CustomResponseMessage;

@RestController
@RequestMapping("/kra")
public class KraController {

    @Autowired
    KraService kraService;

    @PostMapping("/add")
    public CustomResponseMessage add(@RequestBody Kra kraObj) {
        return kraService.addOrUpdate(kraObj);
    }

    @GetMapping
    public CustomResponseMessage findAll(){
        return kraService.findAll();
    }

    @GetMapping("/{id}")
    public CustomResponseMessage findById(@PathVariable(value="id") Long id){
        return kraService.findById(id);
    }

    @PutMapping("/update/{id}")
    public CustomResponseMessage update(@PathVariable(value="id") Long id, @RequestBody Kra kraObj) {
    	kraObj.setId(id);
        return kraService.addOrUpdate(kraObj);
    }
}
