package com.revature.rms.employee.controllers;

import com.revature.rms.employee.controller.EmployeeController;
import com.revature.rms.employee.entities.Department;
import com.revature.rms.employee.entities.Employee;
import com.revature.rms.employee.entities.ResourceMetadata;
import com.revature.rms.employee.services.EmployeeService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import java.util.*;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

@RunWith(SpringJUnit4ClassRunner.class)
public class EmployeeControllerTest {

    @InjectMocks
    private EmployeeController employeeController;

    @Mock
    private EmployeeService employeeService;
    private Department department;

    @Test
    public void testFindAllEmployeessWithValidEmployee(){
        Employee testEmployee = new Employee("Steven", "Kelsey",
                "steven.kelsey@revature.com", "Manager of Technology",
                department, new ResourceMetadata());
    }

    @Test
    public void testFindAllEmployeesWithNoEmployee() {
        List<Employee> testEmployeeList = Collections.emptyList();
        when(employeeService.getAll()).thenReturn(testEmployeeList);
        assertEquals(employeeController.getAllEmployees(), testEmployeeList);
    }
}
