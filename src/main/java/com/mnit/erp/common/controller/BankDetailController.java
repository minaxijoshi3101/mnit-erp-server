package com.mnit.erp.common.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mnit.erp.common.model.BankDetail;
import com.mnit.erp.common.model.BankMaster;
import com.mnit.erp.common.service.BankDetailService;

@RestController
@RequestMapping("/bankdetail")
public class BankDetailController {

    @Autowired
    BankDetailService bankDetailService;

    @PostMapping
    public BankDetail add(@RequestBody BankDetail bankDetail){
        return this.bankDetailService.add(bankDetail);
    }

    @PutMapping
    public BankDetail update(@RequestBody BankDetail bankDetail){
        return this.bankDetailService.update(bankDetail);
    }

    @GetMapping
    public Page<BankDetail> findAll(Pageable pageable){
        return this.bankDetailService.findAll(pageable);
    }

    @GetMapping("/findIFSC/{ifsc}")
    public BankMaster findByIFSC(@PathVariable String ifsc) {
        return this.bankDetailService.findByIFSC(ifsc);
    }
    
}
