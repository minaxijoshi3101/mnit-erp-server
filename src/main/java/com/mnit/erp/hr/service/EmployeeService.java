package com.mnit.erp.hr.service;

import com.mnit.erp.address.repository.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mnit.erp.hr.repository.EmployeeRepository;
import com.mnit.erp.hr.model.Employee;
import com.mnit.erp.exceptions.ServiceException;
import com.mnit.erp.address.model.Address;
import com.mnit.erp.common.Gender;
import com.mnit.erp.common.MaritalStatus;
import com.mnit.erp.common.Pwd;
import com.mnit.erp.common.Tier;
import com.mnit.erp.department.model.Department;
import com.mnit.erp.user.model.User;
import com.mnit.erp.user.service.UserService;
import java.sql.Array;

import java.util.List;
import java.util.ArrayList;
import java.util.Date;
import java.util.Objects;
import java.util.UUID;
import javax.transaction.Transactional;

/**
 *
 * @author Prahalad
 */
@Service
public class EmployeeService {
    @Autowired
    EmployeeRepository employeeRepository;
    
    @Autowired
    AddressRepository addressRepository;
    
    @Autowired
    UserService userService;
    
    public List<Employee> getAll() {
        List<Employee> dataList = new ArrayList<>();
        employeeRepository.findAll().forEach(dataList::add); 
        return dataList;        
    }
    
    public Employee getById(long id) {
        return employeeRepository.findById(id).orElse(null);
    }
    
    public List<Employee> getEmployeesByDepartment(Department dept) {
        List<Employee> dataList = new ArrayList<>();
        employeeRepository.findByDepartment(dept).forEach(dataList::add); 
        return dataList;        
    }
    
    public Employee create(Employee createData) {   
        Employee thisEmployee = employeeRepository.findByEmployeeCode(createData.getEmployeeCode()).orElse(null);
        if(Objects.nonNull(thisEmployee)){
            throw new ServiceException("Duplicate Employee. Can't add!");
        }
        
        if( Objects.nonNull(createData.getAddresses()) && !createData.getAddresses().isEmpty() ){
            addressRepository.saveAll(createData.getAddresses());            
        }
        
        Employee savedEmployee = employeeRepository.save(createData);
        if( Objects.isNull( createEmployeeUser(savedEmployee)) ){
            throw new ServiceException("User login can not be created!");
        }
        return savedEmployee;
    }
    
    public Employee update(Employee updateData) {
        Employee thisEmployee = employeeRepository.findById(updateData.getId()).orElse(null);
        if(Objects.isNull(thisEmployee)){
            throw new ServiceException("Employee not found. Can't update!");
        }
        Employee checkEmployee = employeeRepository.findByEmployeeCode(updateData.getEmployeeCode()).orElse(null);
        if(Objects.nonNull(checkEmployee) && !Objects.equals(updateData.getId(), checkEmployee.getId())){
            throw new ServiceException("Duplicate Employee. Can't update!");
        }
        
        updateData.setAddresses(checkEmployee.getAddresses());      
        
        return employeeRepository.save(updateData);
    }
    
    private User createEmployeeUser(Employee employee){
        User user = new User();
        
        user.setUsername(employee.getEmployeeCode());        
        user.setPassword("123456789");        
        user.setAddedOn(new Date());
        user.setEmail(employee.getPrimaryEmail());
        user.setMobile(employee.getEmergencyContactNum());
        user.setEnabled(true);
        user.setActivated(true);        
        
        if( Objects.nonNull(userService.createUser(user)) ){
            return user;
        }
        return null;
    }
}
