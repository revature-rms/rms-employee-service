package com.revature.rms.employee.controller;

import com.revature.rms.employee.controller.EmployeeController;
import com.revature.rms.employee.dtos.EmployeeCreds;
import com.revature.rms.employee.entities.Department;
import com.revature.rms.employee.entities.Employee;
import com.revature.rms.employee.entities.ResourceMetadata;
import com.revature.rms.employee.exceptions.InvalidRequestException;
import com.revature.rms.employee.exceptions.ResourceNotFoundException;
import com.revature.rms.employee.services.EmployeeService;
import oracle.security.crypto.util.InvalidInputException;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.data.util.Optionals;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import java.util.*;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;
/**
 * These tests are for additional validation and to ensure the EmployeeService methods are working successfully when called
 * by the EmployeeController methods. See EmployeeServiceTests for more details on testing for EmployeeService methods.
 */
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
        when(employeeService.getall()).thenReturn(testEmployeeList);
        assertEquals(employeeController.getAllEmployees(), testEmployeeList);
    }

    @Test
    public void testGetEmployeeByValidId() {
        int id = 1;

        Employee expectedResult = new Employee("Steven", "Kelsey",
                "steven.kelsey@revature.com","Manager of Technology",
                department, new ResourceMetadata());
    }

    @Test
    public void testGetEmployeeWithInvalidEmployee() {
        int id = 5013;
        when(employeeService.getEmployeeById(id)).thenReturn(null);
        assertEquals(employeeController.getEmployeeById(id), null);
    }

    @Test(expected = ResourceNotFoundException.class)
    public void testGetEmployeeByNotFoundId() {
        int id = 1;

        when(employeeService.getEmployeeById(1)).thenThrow(new ResourceNotFoundException());
        employeeController.getEmployeeById(id);
    }

    @Test
    public void testAddNewEmployeeWithValidEmployee() {
        EmployeeCreds testEmployee = new EmployeeCreds("Steven", "Kelsey",
                "steven.kelsey@revature.com", "Manager of Technology",
                department);
        Employee persistedEmployee = new Employee("Steven", "Kelsey",
                "steven.kelsey@revature.com", "Manager of Technology",
                department);
        when(employeeService.addEmployee(testEmployee,1)).thenReturn(persistedEmployee);
        assertEquals(employeeController.addNewEmployee(testEmployee, 1), persistedEmployee);
    }

    @Test(expected = ResourceNotFoundException.class)
    @Ignore
    //TODO: fix it. Everything is broken.
    public void testAddNewEmployeeWithNullEmployee() {
        Employee expectedResult = new Employee("Steven", "Kelsey",
                "steven.kelsey@revature.com","Manager of Technology",
                department);
        when(employeeService.addEmployee(Mockito.any(), 1)).thenReturn(expectedResult);
        assertEquals(employeeController.addNewEmployee(null, 1), expectedResult);
    }

    @Test
    public void testDeleteEmployeeWithValidId() {
        int id = 1;

        employeeController.deleteEmployeeById(id);
        verify(employeeService, times(1)).delete(id);
    }

    @Test(expected = InvalidRequestException.class)
    public void testDeleteEmployeeWithInvalidId() {
        Employee testEmployee = new Employee("Steven", "Kelsey",
                "steven.kelsey@revature.com","Manager of Technology",
                department);
                int testId = -1;
        when(employeeService.getEmployeeById(testId)).thenReturn((testEmployee));
        employeeController.deleteEmployeeById(testId);
        verify(employeeService, times(0)).delete(testId);
    }
}