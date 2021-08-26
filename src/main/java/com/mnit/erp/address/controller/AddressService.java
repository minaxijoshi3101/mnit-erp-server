package com.mnit.erp.address.controller;

import com.mnit.erp.academic.student.st.model.Student;
import com.mnit.erp.address.model.Address;
import com.mnit.erp.address.repository.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AddressService {

    @Autowired
    AddressRepository addressRepository;

    public Address add(Address address) {
        if(this.validate(address)){
            return this.addressRepository.save(address);
        }
        return null;
    }

    private boolean validate(Address address){
        return true;
    }

    public Address update(Address address) {
        if(this.validate(address)){
            return this.addressRepository.save(address);
        }
        return null;
    }

    public Page<Address> findAll(Pageable pageable) {
        return this.addressRepository.findAll(pageable);
    }

    public List<Address> saveAddressesOf(List<Address> addresses, Student student){
//        addresses.forEach(address -> address.setStudent(student));
        return this.addressRepository.saveAll(addresses);
    }

}
