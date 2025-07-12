package com.company.employee_project_1.controller;

import com.company.employee_project_1.models.Employee;
import com.company.employee_project_1.services.EmployeeService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class EmployeeController {

    private EmployeeService service;
    private ObjectMapper objectMapper;

    @Autowired
    public EmployeeController(EmployeeService service, ObjectMapper objectMapper){
        this.service=service;
        this.objectMapper=objectMapper;
    }

    @GetMapping("/employees")
    public List<Employee> getAllEmployees(){
        return service.findAll();
    }

    @GetMapping("/employees/{id}")
    public Employee getEmployeeById(@PathVariable int id){
        return service.findById(id);
    }

    @PostMapping("/employees")
    public Employee saveEmployee(@RequestBody Employee employee){
        employee.setId(0);
        return service.save(employee);
    }

    @PutMapping("/employees")
    public Employee updateEmployee(@RequestBody Employee employee){
        System.out.println(employee);
        return service.save(employee);
    }


    @DeleteMapping("/employees/{id}")
    public void deleteEmployee(@PathVariable int id){
        Employee employee=service.findById(id);
       service.delete(employee);
    }

    @PatchMapping("/employee/{id}")
    public Employee updateEmployeeById(@PathVariable int id, @RequestBody Map<String, Object> patchPayLoad){
        Employee employee=service.findById(id);
        if(employee==null){
            throw new RuntimeException("Employee not found for id :"+id);
        }

        if(patchPayLoad.containsKey("id")){
            throw new RuntimeException("Employee id is not allowed in request body");
        }
        Employee newPatchPayLoad=apply(patchPayLoad,employee);

        return service.save(newPatchPayLoad);
    }

    private Employee apply(Map<String, Object> patchPayLoad, Employee employee) {
        ObjectNode employeeNode=objectMapper.convertValue(employee, ObjectNode.class);

        ObjectNode patchNode=objectMapper.convertValue(patchPayLoad,ObjectNode.class);

        employeeNode.setAll(patchNode);

        return objectMapper.convertValue(employeeNode, Employee.class);
    }
}
