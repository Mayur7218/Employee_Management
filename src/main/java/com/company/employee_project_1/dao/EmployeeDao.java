package com.company.employee_project_1.dao;

import com.company.employee_project_1.models.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeDao extends JpaRepository<Employee,Integer> {

}