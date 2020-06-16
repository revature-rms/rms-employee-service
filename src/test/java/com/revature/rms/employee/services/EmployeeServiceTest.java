package com.revature.rms.employee.services;

import com.revature.rms.employee.entities.Department;
import com.revature.rms.employee.entities.Employee;
import com.revature.rms.employee.entities.ResourceMetadata;
import com.revature.rms.employee.repositories.EmployeeRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
public class EmployeeServiceTest {
    @Mock
    EmployeeRepository employeeRepository;
    Department department;
    @InjectMocks
    EmployeeService employeeService;

    /**
     * testGetAll() ensures EmployeeService().getAll() returns a list of all the existing Employee objects.
     */
    @Test
    public void testGetAll() {
        Employee employee = new Employee("Steven", "Kelsey",
                "steven.kelsey@revature.com", "Manager of Technology",
                department, new ResourceMetadata());
        List<Employee> mockEmployeeList = Arrays.asList(employee);
        when(employeeRepository.findAll()).thenReturn(mockEmployeeList);
        assertEquals(mockEmployeeList, employeeService.getall());

    }
}