package com.revature.rms.employee.services;

import com.revature.rms.employee.dtos.EmployeeDto;
import com.revature.rms.employee.entities.Department;
import com.revature.rms.employee.entities.Employee;
import com.revature.rms.core.metadata.ResourceMetadata;
import com.revature.rms.employee.exceptions.InvalidRequestException;
import com.revature.rms.employee.exceptions.ResourceNotFoundException;
import com.revature.rms.employee.repositories.EmployeeRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
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
        //Arrange
        Employee employee = new Employee("Steven", "Kelsey",
                "steven.kelsey@revature.com", "Manager of Technology",
                department, new ResourceMetadata());
        List<Employee> mockEmployeeList = Arrays.asList(employee);
        //Act
        when(employeeRepository.findAll()).thenReturn(mockEmployeeList);
        //Assert
        assertEquals(mockEmployeeList, employeeService.findAll());
    }

    /**
     * testGetEmployeeById ensures EmployeeService().getEmployeeById() returns an existing Employee object.
     */
    @Test
    public void testGetEmployeeById() {
        //Arrange
        Employee expectedEmployee = new Employee("Steven", "Kelsey",
                "steven.kelsey@revature.com", "Manager of Technology",
                department, new ResourceMetadata());
        //Act
        when(employeeRepository.findById(1)).thenReturn(expectedEmployee);
        Employee actualEmployee = employeeService.findById(1);
        //Assert
        assertEquals(actualEmployee, expectedEmployee);
    }

    /**
     * tests findEmployeeByOwnerId to see if it throws a BadRequestException if given an invalid id number
     */
    @Test(expected = InvalidRequestException.class)
    public void testFindEmployeeByOwnerIdBad(){
        employeeService.findByOwnerId(0);
    }

    /**
     * tests resourceNotFoundException by giving one employee with a resourceMetaData different than the owner id put into employeeService.findEmployeeByOwnerId
     */
    @Test(expected = ResourceNotFoundException.class)
    public void testFindEmployeeByOwnerIdNotFound(){
        Employee differentEmployee = new Employee("Steven", "Kelsey",
                "steven.kelsey@revature.com", "Manager of Technology",
                department, new ResourceMetadata(1, "test", 1, "test", 1, true));
        List<Employee> employees = new ArrayList<>();
        employees.add(differentEmployee);
        when(employeeRepository.findAll()).thenReturn(employees);
        employeeService.findByOwnerId(2);
    }
    /**
     * testGetEmployeeByOwnerId EmployeeService().findEmployeeByOwnerId() returns an existing Employee object.
     */
    @Test
    public void testGetEmployeeByOwnerId() {
        //Arrange
        Employee expectedEmployee = new Employee("Steven", "Kelsey",
                "steven.kelsey@revature.com", "Manager of Technology",
                department, new ResourceMetadata(1, "test", 1, "test", 1, true));
        //Act
        List<Employee> expectedEmployees = new ArrayList<>();
        expectedEmployees.add(expectedEmployee);

        when(employeeRepository.findAll()).thenReturn(expectedEmployees);
        List<Employee> actualEmployees = employeeService.findByOwnerId(1);
        //Assert
        assertEquals(expectedEmployees, actualEmployees);
    }

    /**
     * testFindByFirstname ensures EmployeeService().findByFirstname() returns an existing Employee object.
     */
    @Test
    public void testFindByFirstname() {
        //Arrange
        Employee expectedEmployee = new Employee("Steven", "Kelsey",
                "steven.kelsey@revature.com", "Manager of Technology",
                department, new ResourceMetadata());
        //Act
        when(employeeRepository.findByFirstName(Mockito.any())).thenReturn(expectedEmployee);
        Employee actualEmployee = employeeService.findByFirstname("Steven");
        //Assert
        assertEquals(expectedEmployee, actualEmployee);
    }

    /**
     * testUpdateWithValidEmployee() passes in an employee object with changed fields to the employeeRepository.save() to
     * persist the changes and return the updated saved employee object. There is no need for a null employee check since the
     * object already exists.
     */
    @Test
    public void testUpdateWithValidEmployee() {
        //Arrange
        EmployeeDto testEmployeeDto = new EmployeeDto(1,"Steven", "Kelsey",
                "steven.kelsey@revature.com", "Manager of Technology",
                Department.HR);
        Employee expectedResult = new Employee(1,"Steven", "Kelsey",
                "steven.kelsey@revature.com", "Manager of Technology",
                Department.HR, new ResourceMetadata(1, "test", 1, "test", 1, true));
        Employee testEmployee = new Employee(testEmployeeDto);
        //Act
        when(employeeRepository.findById(1)).thenReturn(expectedResult);
        when(employeeRepository.save(any())).thenReturn(expectedResult);
        Employee actualResult = employeeService.update(testEmployeeDto,1);
        //Assert
        assertEquals(expectedResult, actualResult);
    }

    /**
     * testAddEmployeeWithValidEmployee() ensures employeeService.save() is functioning properly with the valid or correct input.
     * A non-null building object should be returned.
     */
    @Test
    public void testAddEmployeeWithValidEmployee() {
        //Arrange
        EmployeeDto testEmployee = new EmployeeDto("Steven", "Kelsey",
                "steven.kelsey@revature.com", "Manager of Technology",
                department);
        Employee expectedResult = new Employee("Steven", "Kelsey",
                "steven.kelsey@revature.com", "Manager of Technology",
                department);
        //Act

        when(employeeRepository.save(Mockito.any())).thenReturn(expectedResult);
        Employee actualResult = employeeService.save(testEmployee, 1);
        //Assert
        assertEquals(expectedResult, actualResult);
    }

    /**
     * tests for invalid id number in delete throws a InvalidRequestException
     */
    @Test(expected = InvalidRequestException.class)
    public void testDeleteInvalid() {
        employeeService.delete(0);
    }

    /**
     * tests for a no error delete method
     */

    @Test
    public void testDelete(){
        employeeService.delete(1);
    }

}
