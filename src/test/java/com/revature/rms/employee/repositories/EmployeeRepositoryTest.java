package com.revature.rms.employee.repositories;

import com.revature.rms.employee.dtos.EmployeeCreds;
import com.revature.rms.employee.entities.Department;
import com.revature.rms.employee.entities.Employee;
import com.revature.rms.employee.entities.ResourceMetadata;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.TestInstance;
import static org.junit.jupiter.api.Assertions.*;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class EmployeeRepositoryTest {
    @Autowired
    private EmployeeRepository employeeRepository;
    @Autowired
    TestEntityManager entityManager;
    @Mock
    private Department department;
    @BeforeEach
    public void setup() throws Exception {
        EmployeeCreds employee1 = new EmployeeCreds("Test", "Tester",
                "test.tester@revature.com","Manager of Technology",
                department);
        EmployeeCreds employee2 = new EmployeeCreds("Test2", "Tester2",
                "test2.tester2@revature.com","Lead Trainer",
                department);
    }

    @Test
    @Ignore
    public void testAddEmployee() {
        Employee employee = new Employee("Test", "Tester",
                "test.tester@revature.com","Manager of Technology",
                department);
        employee = entityManager.persistAndFlush(employee);
    }

    @Test
    @Ignore
    //TODO: find out how to properly mock the ID for employees for testing.
    public void testFindAll() {
        Iterable<Employee> employees = employeeRepository.findAll();
        int count = 0;
        for (Employee e : employees) {
            count++;
        }
        assertEquals(22, count);
    }
}