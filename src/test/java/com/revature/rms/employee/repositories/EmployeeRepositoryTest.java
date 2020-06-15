package com.revature.rms.employee.repositories;

import com.revature.rms.employee.entities.Department;
import com.revature.rms.employee.entities.Employee;
import com.revature.rms.employee.entities.ResourceMetadata;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.TestInstance;
import static org.junit.jupiter.api.Assertions.*;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class EmployeeRepositoryTest {
    @Autowired
    private EmployeeRepository employeeRepository;
    @Mock
    private Department department;
    @BeforeEach
    public void setup() throws Exception {
        Employee employee1 = new Employee(1, "Test", "Tester",
                "test.tester@revature.com","Manager of Technology",
                department, new ResourceMetadata());
        Employee employee2 = new Employee(2, "Test2", "Tester2",
                "test2.tester2@revature.com","Lead Trainer",
                department, new ResourceMetadata());
        assertNull(employee1.getId());
        assertNull(employee2.getId());
        this.employeeRepository.save(employee1);
        this.employeeRepository.save(employee2);
        assertNotNull(employee1.getId());
        assertNotNull(employee2.getId());
    }
    //TODO: find out how to properly mock the ID for employees for testing.
    @Test
    public void testFindAll() {
        Iterable<Employee> employees = employeeRepository.findAll();
        int count = 0;
        for (Employee e : employees) {
            count++;
        }
        assertEquals(22, count);
    }
}
