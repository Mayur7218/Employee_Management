package com.company.employee_project_1.services;

import com.company.employee_project_1.models.Employee;
import com.company.employee_project_1.dao.EmployeeDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {
    private EmployeeDao dao;

    @Autowired
    public EmployeeService(EmployeeDao dao){
        this.dao=dao;
    }

    public List<Employee> findAll() {
        return dao.findAll();
    }

    public Employee findById(int id) {
        Optional<Employee> employee=dao.findById(id);
        return employee.get();
    }

    public Employee save(Employee employee) {
        return dao.save(employee);
    }

    public void delete(Employee employee){
        dao.delete(employee);
    }
}
