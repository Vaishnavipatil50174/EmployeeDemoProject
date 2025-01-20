package com.example.employeedemo;


import com.example.employeedemo.exception.TryException;
import com.example.employeedemo.model.Employee;
import com.example.employeedemo.repository.EmployeeRepo;
import com.example.employeedemo.service.EmployeeService;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.util.Assert;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class EmployeeServiceTest {

//    @Mock
//    EmployeeRepo employeeRepo;
//
//    @Captor
//    ArgumentCaptor<Integer>emp;
//
//    @InjectMocks
//    EmployeeService employeeService;
//
//    @BeforeEach
//    public void setUp(){
//        MockitoAnnotations.openMocks(this);
//    }
//
//    @Test
//    void testSucssesSave(){
//        // Create a sample employee object
//        Employee employee = new Employee();
//        employee.setId(1);
//        employee.setName("Vaish");
//
//        // Mock the findById method of employeeRepo to return the employee
//        when(employeeRepo.findById(1)).thenReturn(Optional.of(employee));
//
//        // Call the service method by passing employee ID
//        Employee result = employeeService.byId(1);
//
//        // Verify that findById was called exactly once and capture the argument passed
//        verify(employeeRepo, times(1)).findById(emp.capture());
//
////        // Use the captor to get the captured value (the argument passed to findById)
////        Integer capturedId = emp.getValue();
////
////        // Assert that the captured argument is 1 (the ID we passed to findById)
////        assertEquals(Integer.valueOf(1), capturedId);
//
//        // Assert that the result returned by the service is the same employee object
//        assertEquals(employee, result);
//    }

    @Mock
    EmployeeRepo employeeRepo;

    @InjectMocks
    EmployeeService employeeService;

//    @BeforeEach
//    public void setup(){
//        MockitoAnnotations.openMocks(this);
//    }

    @Test
    public void succesfull(){
        Employee employee=new Employee();
        employee.setId(1);
        employee.setName("sonu");
        when(employeeRepo.findById(1)).thenReturn(Optional.of(employee));

        Employee e=employeeService.byId(1);

        assertEquals("sonu",e.getName());
        verify(employeeRepo,times(1)).findById(1);
    }

    @Test
    void throwException(){
        Exception exception=assertThrows(TryException.class,()->{
            employeeService.byId(2);
        });
        assertEquals("user is not in the database",exception.getMessage());
    }
    @Test
    void update(){
        Employee employee=new Employee();
        Integer id1 = 1;
        employee.setName("old");
        employee.setId(id1);
        Employee employee1=new Employee();
        employee1.setName("new");
        when(employeeRepo.findById(id1)).thenReturn(Optional.of(employee));
        when(employeeRepo.save(employee)).thenReturn(employee);

        Employee employee2=employeeService.update(id1,employee1);
        assertEquals(employee2.getName(),"new");


    }

}
