package com.example.employeedemo.service;
import com.example.employeedemo.exception.InvalidEmployeeDataException;
import com.example.employeedemo.exception.TryException;
import com.example.employeedemo.model.Employee;
import com.example.employeedemo.repository.EmployeeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service

public class EmployeeService {
    @Autowired
    EmployeeRepo employeeRepo;

    @Cacheable
    public Iterable<Employee>showAll(){
        return employeeRepo.findAll();
    }
    @Cacheable(value = "employee",key = "#id")
    public Employee byId(Integer id){
        return employeeRepo.findById(id).orElseThrow(()-> new TryException("user is not in the database"));
    }
    @CachePut(value = "employee",key = "#employee.id")
    public Employee add(Employee employee){
        return employeeRepo.save(employee);
    }

    @CachePut(value = "employee",key = "#employee.id")
    public Employee update(Integer id,Employee employee){
        Employee oldEmployee=employeeRepo.findById(id).orElseThrow(()-> new InvalidEmployeeDataException("You are trying to update but this id is not in database"));
        oldEmployee.setName(employee.getName());
        return employeeRepo.save(oldEmployee);
    }
    @CacheEvict(value = "employee",key = "#id")
    public void delete(Integer id){
        employeeRepo.deleteById(id);
    }
}
