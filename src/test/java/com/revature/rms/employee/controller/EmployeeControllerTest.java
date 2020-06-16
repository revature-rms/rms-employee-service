package com.revature.rms.employee.controller;

import com.revature.rms.employee.controller.EmployeeController;
import com.revature.rms.employee.entities.Department;
import com.revature.rms.employee.entities.Employee;
import com.revature.rms.employee.entities.ResourceMetadata;
import com.revature.rms.employee.services.EmployeeService;
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
    public void testFindEmployeeByValidId() {
        int id = 1;

        Employee expectedResult = new Employee("Steven", "Kelsey",
                "steven.kelsey@revature.com","Manager of Technology",
                department, new ResourceMetadata());
    }

    @Test
    @Ignore
    //TODO: NullPointerException fix.
    public void testGetEmployeeWithInvalidBuilding() {
        int id = 5013;
        when(employeeService.getEmployeeById(Mockito.any())).thenReturn(null);
        assertEquals(employeeController.getEmployeeById(id), null);
    }

//    @Test
//    @Ignore
////    TODO: find out optional issue.
//    public void testGetEmployeeByNotFoundId() {
//        int id = 98723;
//
//        when(employeeService.getEmployeeById(Mockito.any())).thenReturn(Optional.empty());
//        employeeController.getEmployeeById(id);
//    }
}