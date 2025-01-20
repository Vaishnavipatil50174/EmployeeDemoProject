package com.example.employeedemo.service;

import com.example.employeedemo.exception.InvalidEmployeeDataException;
import com.example.employeedemo.exception.TryException;
import com.example.employeedemo.exception.UserException;
import com.example.employeedemo.model.Employee;
import com.example.employeedemo.repository.EmployeeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.RequestScope;

import java.util.List;
@Service
//@Profile("prod")
public class EmployeeService {
    @Autowired
    EmployeeRepo employeeRepo;



    public Iterable<Employee>showAll(){

        return employeeRepo.findAll();


    }
    public Employee byId(Integer id){
        return employeeRepo.findById(id).orElseThrow(()-> new TryException("user is not in the database"));
    }
    public Employee add(Employee employee){
        return employeeRepo.save(employee);
    }

    public Employee update(Integer id,Employee employee){
        Employee oldEmployee=employeeRepo.findById(id).orElseThrow(()-> new InvalidEmployeeDataException("You are trying to update but this id is not in database"));
//        oldEmployee.setId(employee.getId());
        oldEmployee.setName(employee.getName());
        return employeeRepo.save(oldEmployee);
    }

    public void delete(Integer id){
        employeeRepo.deleteById(id);
    }
}
