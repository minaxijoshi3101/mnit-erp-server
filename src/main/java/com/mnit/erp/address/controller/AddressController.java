package com.mnit.erp.address.controller;

import com.mnit.erp.address.model.Address;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/address")
public class AddressController {

    @Autowired
    AddressService addressService;

    @PostMapping
    public Address add(@RequestBody Address address){
        return this.addressService.add(address);
    }

    @PutMapping
    public Address update(@RequestBody Address address){
        return this.addressService.update(address);
    }

    @GetMapping
    public Page<Address> findAll(Pageable pageable){
        return this.addressService.findAll(pageable);
    }

    @GetMapping("/ofStudent/{studentId}")
    public List<Address> findAll(@PathVariable Long studentId){
        return null;
    }

}
