package com.mnit.erp.common.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.mnit.erp.common.model.BankDetail;
import com.mnit.erp.common.model.BankMaster;
import com.mnit.erp.common.repository.BankDetailRepository;
import com.mnit.erp.common.repository.BankMasterRepository;

@Service
public class BankDetailService {

    @Autowired
    BankDetailRepository bankDetailRepository;
    @Autowired
    BankMasterRepository bankMasterRepository;

    public BankDetail add(BankDetail bankDetail) {
        if(this.validate(bankDetail)){
            return this.bankDetailRepository.save(bankDetail);
        }
        return null;
    }

    private boolean validate(BankDetail bankDetail){
        return true;
    }

    public BankDetail update(BankDetail bankDetail) {
        if(this.validate(bankDetail)){
            return this.bankDetailRepository.save(bankDetail);
        }
        return null;
    }

    public Page<BankDetail> findAll(Pageable pageable) {
        return this.bankDetailRepository.findAll(pageable);
    }

    public BankMaster findByIFSC(String ifsc) {
        return this.bankMasterRepository.findByIFSC(ifsc);
    }

}
