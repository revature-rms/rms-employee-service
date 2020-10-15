package com.revature.rms.employee.controller;

import com.revature.rms.employee.dtos.EmployeeDto;
import com.revature.rms.employee.entities.Department;
import com.revature.rms.employee.entities.Employee;
import com.revature.rms.core.metadata.ResourceMetadata;
import com.revature.rms.employee.exceptions.InvalidRequestException;
import com.revature.rms.employee.exceptions.ResourceNotFoundException;
import com.revature.rms.employee.services.EmployeeService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
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


    /**
     * tests getAllEmployees by having mockito return a valid employee
     */
    @Test
    public void testFindAllEmployeesWithValidEmployee(){
        Employee testEmployee = new Employee("Steven", "Kelsey",
                "steven.kelsey@revature.com", "Manager of Technology",
                department, new ResourceMetadata());
        List<Employee> testEmployeeList = Arrays.asList(testEmployee);
        when(employeeService.findAll()).thenReturn(testEmployeeList);
        assertEquals(testEmployeeList, employeeController.getAllEmployees());
    }

    /**
     * tests getAllEmployees if mockito returns an empty list
     */
    @Test
    public void testFindAllEmployeesWithNoEmployee() {
        List<Employee> testEmployeeList = Collections.emptyList();
        when(employeeService.findAll()).thenReturn(testEmployeeList);
        assertEquals(employeeController.getAllEmployees(), testEmployeeList);
    }

    /**
     * tests getEmployeeById by having mockito return a valid employee and giving it a valid id
     */
    @Test
    public void testGetEmployeeByValidId() {
        int id = 1;

        Employee expectedResult = new Employee("Steven", "Kelsey",
                "steven.kelsey@revature.com","Manager of Technology",
                department, new ResourceMetadata());
        when(employeeService.findById(1)).thenReturn(expectedResult);
        assertEquals(employeeController.getEmployeeById(id), expectedResult);
    }

    /**
     * tests getEmployeeById if the employee is not found by throwing a ResourceNotFoundException
     */
    @Test(expected = ResourceNotFoundException.class)
    public void testGetEmployeeByNotFoundId() {
        int id = 1;

        when(employeeService.findById(1)).thenThrow(new ResourceNotFoundException());
        employeeController.getEmployeeById(id);
    }

    /**
     * tests getEmployeesById by giving a set of ids and returning through mockito a set of employees
     */
    @Test
    public void testGetEmployeesByValidIds() {
        Employee expectedResult = new Employee("Steven", "Kelsey",
                "steven.kelsey@revature.com","Manager of Technology",
                department, new ResourceMetadata());
        when(employeeService.findById(anyInt())).thenReturn(expectedResult);
        Set<Employee> actualResult = new HashSet<>();
        actualResult.add(expectedResult);
        Set<Integer>  ids = new HashSet<>();
        ids.add(1);
        assertEquals(employeeController.getEmployeesByIds(ids), actualResult);
    }

    /**
     * tests getByFirstName by giving an employeeCred and getting an employee back
     */
    @Test
    public void testGetEmployeeByValidFirstName() {
        EmployeeDto testEmployee = new EmployeeDto("Steven", "Kelsey",
                "steven.kelsey@revature.com", "Manager of Technology",
                department);
        Employee expectedResult = new Employee("Steven", "Kelsey",
                "steven.kelsey@revature.com", "Manager of Technology",
                department);
        when(employeeService.findByFirstname("Steven")).thenReturn(expectedResult);
        assertEquals(employeeController.getByFirstName(testEmployee.getFirstName()), expectedResult);
    }


    /**
     * tests getAllEmployees by having mockito return a list of employees
     */

    @Test
    public void testGetAllEmployees() {
        Employee expectedEmployee = new Employee("Steven", "Kelsey",
                "steven.kelsey@revature.com","Manager of Technology",
                department, new ResourceMetadata());
        List<Employee> expectedResult = new ArrayList<>();
        expectedResult.add(expectedEmployee);
        List<Integer>  ids = new ArrayList<>();
        ids.add(1);
        when(employeeService.findAll()).thenReturn(expectedResult);
        assertEquals(expectedResult, employeeController.getAllEmployees());
    }

    /**
     * tests getEmployeeId with an invalid id number and returning a null employee
     */
    @Test
    public void testGetEmployeeWithInvalidEmployee() {
        int id = 0;
        when(employeeService.findById(id)).thenReturn(null);
        assertEquals(employeeController.getEmployeeById(id), null);
    }

    /**
     * tests addNewEmployee by having mockito return an employee through employeeService.addEmployee
     */
    @Test
    public void testAddNewEmployeeWithValidEmployee() {
        EmployeeDto testEmployee = new EmployeeDto("Steven", "Kelsey",
                "steven.kelsey@revature.com", "Manager of Technology",
                department);
        Employee persistedEmployee = new Employee("Steven", "Kelsey",
                "steven.kelsey@revature.com", "Manager of Technology",
                department);
        when(employeeService.save(testEmployee,1)).thenReturn(persistedEmployee);
        assertEquals(employeeController.addNewEmployee(testEmployee, 1), persistedEmployee);
    }

    /**
     * tests updateEmployee by having mockito return an employee through employeeService.update
     */
    @Test
    public void testAddNewEmployeeWithResourcesWithValidEmployee() {
        EmployeeDto testEmployee = new EmployeeDto("Steven", "Kelsey",
                "steven.kelsey@revature.com", "Manager of Technology",
                department);
        Employee persistedEmployee = new Employee("Steven", "Kelsey",
                "steven.kelsey@revature.com", "Manager of Technology",
                department);
        when(employeeService.update(testEmployee,1)).thenReturn(persistedEmployee);
        assertEquals(employeeController.updateEmployee(testEmployee, 1), persistedEmployee);
    }

    /**
     * tests delete employee with a valid id
     */
    @Test
    public void testDeleteEmployeeWithValidId() {
        int id = 1;

        employeeController.deleteEmployeeById(id);
        verify(employeeService, times(1)).delete(id);
    }

    /**
     * tests delete employee with an invalid id and having it throw an InvalidRequestException
     */
    @Test(expected = InvalidRequestException.class)
    public void testDeleteEmployeeWithInvalidId() {
        Employee testEmployee = new Employee("Steven", "Kelsey",
                "steven.kelsey@revature.com","Manager of Technology",
                department);
                int testId = -1;
        when(employeeService.findById(testId)).thenReturn((testEmployee));
        employeeController.deleteEmployeeById(testId);
        verify(employeeService, times(0)).delete(testId);
    }

}
