package com.revature.rms.employee.repositories;

import com.revature.rms.employee.dtos.EmployeeDto;
import com.revature.rms.employee.entities.Department;
import com.revature.rms.employee.entities.Employee;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.TestInstance;
import static org.junit.jupiter.api.Assertions.*;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;

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
        EmployeeDto employee1 = new EmployeeDto("Test", "Tester",
                "test.tester@revature.com","Manager of Technology",
                "HR");
        EmployeeDto employee2 = new EmployeeDto("Test2", "Tester2",
                "test2.tester2@revature.com","Lead Trainer",
                "HR");
    }

}
