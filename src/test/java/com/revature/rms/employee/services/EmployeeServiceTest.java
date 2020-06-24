package com.revature.rms.employee.services;

import com.revature.rms.employee.dtos.EmployeeCreds;
import com.revature.rms.employee.entities.Department;
import com.revature.rms.employee.entities.Employee;
import com.revature.rms.employee.entities.ResourceMetadata;
import com.revature.rms.employee.repositories.EmployeeRepository;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.autoconfigure.ldap.embedded.EmbeddedLdapAutoConfiguration;
import org.springframework.boot.autoconfigure.mongo.embedded.EmbeddedMongoProperties;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

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
        //Arrange
        Employee employee = new Employee("Steven", "Kelsey",
                "steven.kelsey@revature.com", "Manager of Technology",
                department, new ResourceMetadata());
        List<Employee> mockEmployeeList = Arrays.asList(employee);
        //Act
        when(employeeRepository.findAll()).thenReturn(mockEmployeeList);
        //Assert
        assertEquals(mockEmployeeList, employeeService.getall());
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
        Employee actualEmployee = employeeService.getEmployeeById(1);
        //Assert
        assertEquals(actualEmployee, expectedEmployee);
    }

    /**
     * testGetEmployeeByOwnerId EmployeeService().findEmployeeByOwnerId() returns an existing Employee object.
     */
    @Test
    @Ignore
    //TODO: fix ResourceNotFoundException
    public void testGetEmployeeByOwnerId() {
        //Arrange
        Employee expectedEmployee = new Employee("Steven", "Kelsey",
                "steven.kelsey@revature.com", "Manager of Technology",
                department, new ResourceMetadata());
        //Act
        when(employeeRepository.findById(1)).thenReturn(expectedEmployee);
        Employee actualEmployee = (Employee) employeeService.findEmployeeByOwnerId(1);
        //Assert
        assertEquals(actualEmployee, expectedEmployee);
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
    @Ignore
    //TODO: NullPointerException issue due to EmployeeCreds to Employee conversion
    public void testUpdateWithValidEmployee() {
        //Arrange
        EmployeeCreds testEmployee = new EmployeeCreds(1,"Steven", "Kelsey",
                "steven.kelsey@revature.com", "Manager of Technology",
                department);
        EmployeeCreds expectedResult = new EmployeeCreds(1,"Steven", "Kelsey",
                "steven.kelsey@revature.com", "Manager of Technology",
                department);
        //Act

        Employee actualResult = employeeService.update(testEmployee, 1);
        //Assert
        assertEquals(expectedResult, actualResult);
    }

    /**
     * testAddEmployeeWithValidEmployee() ensures employeeService.save() is functioning properly with the valid or correct input.
     * A non-null building object should be returned.
     */
    @Test
    @Ignore
    //TODO: NullPointerException issue due to EmployeeCreds to Employee conversion
    public void testAddEmployeeWithValidEmployee() {
        //Arrange
        EmployeeCreds testEmployee = new EmployeeCreds("Steven", "Kelsey",
                "steven.kelsey@revature.com", "Manager of Technology",
                department);
        Employee expectedResult = new Employee("Steven", "Kelsey",
                "steven.kelsey@revature.com", "Manager of Technology",
                department);
        //Act
        when(employeeRepository.save(Mockito.any())).thenReturn(expectedResult);
        Employee actualResult = employeeService.update(testEmployee, 1);
        //Assert
        assertEquals(expectedResult, actualResult);
    }

}