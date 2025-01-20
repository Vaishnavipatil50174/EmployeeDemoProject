package com.example.employeedemo;

import com.example.employeedemo.controller.EmployeeController;
import com.example.employeedemo.model.Employee;
import com.example.employeedemo.service.EmployeeService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper; // For handling JSON conversions
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test; // For using JUnit 5 annotations
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired; // For injecting beans
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest; // To load only the web layer
import org.springframework.boot.test.mock.mockito.MockBean; // To mock beans in Spring Boot tests
import org.springframework.test.web.servlet.MockMvc; // To perform HTTP requests and assert results
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders; // To build HTTP requests
import org.springframework.test.web.servlet.result.MockMvcResultMatchers; // To assert the results of HTTP requests

import static org.mockito.Mockito.*; // For Mockito stubbing and verification
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*; // For `get()`, `post()`, etc.
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*; // For `status()`, `jsonPath()`, etc.

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*; // For `get()`, `post()`, etc.

@WebMvcTest(EmployeeController.class)
public class EmployeeControllerTest {

//    @Autowired
//    private MockMvc mockMvc;
//
//
//
//
//    @MockBean
//    EmployeeService employeeService;
//
//    @Test
//    public void testGetEmployee() throws Exception {
//
//
//
//        Employee emp1=new Employee();
//        emp1.setId(1);
//        emp1.setName("vaish");
//
//        when(employeeService.byId(1)).thenReturn(emp1);
//
//        // Act and Assert
//
//        mockMvc.perform(get("/employee/1"))
//                //.andExpect(status().isOk())
//                .andExpect(jsonPath("$.name").value("vaish"));
//
//
//    }



   @Autowired
    MockMvc mockMvc;

   @MockBean
    EmployeeService employeeService;
   @Test
   public void testSuccess() throws Exception {
       Employee employee=new Employee();
       employee.setId(1);
       employee.setName("sonu");
       when(employeeService.byId(1)).thenReturn(employee);

       mockMvc.perform(get("/employee/1"))
               .andExpect(jsonPath("$.name").value("sonu"));
   }
   @Test
   public void update() throws Exception {
       Employee employee=new Employee();
       employee.setId(1);
       employee.setName("vaish");
       when(employeeService.update(1,employee)).thenReturn(employee);

//       mockMvc.perform(put("/emp/1"))
//               .andExpect(jsonPath("$.name").value("vaish"));


       mockMvc.perform(put("/emp/1")
                       .contentType("application/json")
                       .content(new ObjectMapper().writeValueAsString(employee))) // Convert object to JSON
               .andExpect(jsonPath("$.name").value("vaish"));
   }


   @Test
   void postkeliye() throws Exception {
       Employee employee=new Employee();
       employee.setId(1);
       employee.setName("vaish");
       when(employeeService.add(employee)).thenReturn(employee);

       mockMvc.perform(post("/employees")
               .contentType("application/json")
               .content(new ObjectMapper().writeValueAsString(employee)))
               .andExpect(jsonPath("$.name").value("vaish"));
   }

    @ParameterizedTest
    @ValueSource(ints = {2,4,6})
    public void test(int k){
       Assertions.assertTrue(k%2==0);

   }










}
