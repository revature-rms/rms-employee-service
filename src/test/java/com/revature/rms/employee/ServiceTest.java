//package com.revature.rms.employee;
//
//
//import com.revature.rms.employee.entities.Employee;
//import com.revature.rms.employee.entities.ResourceMetadata;
//import com.revature.rms.employee.services.EmployeeService;
//import com.revature.rms.employee.services.ResourceMetadataService;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.context.junit4.SpringRunner;
//
//import java.time.LocalDate;
//
//import static org.assertj.core.api.Assertions.*;
//
//
//
//@RunWith(SpringRunner.class)
//@SpringBootTest
//public class ServiceTest {
//
//    @Autowired
//    private EmployeeService employeeService;
//    @Autowired
//    private ResourceMetadataService resourceMetadataService;
//
//    @Test
//    public void testAddEmp_AndSearchEmp() {
//        String name = "test1";
//
//        Employee check = new Employee("test1","test1","test1","test1");
//
//        employeeService.addEmployee(check);
//
//        Employee found = employeeService.findByFirstname(name);
//
//        assertThat(found.getFirstName())
//                .isEqualTo(name);
//    }
//
//    @Test
//    public void testAddResource_AndSearchResource() {
//        int a = 1;
//        LocalDate today = LocalDate.now();
//        String createdDate = today.toString();
//        String lastModifiedDate = today.toString();
//        ResourceMetadata check = new ResourceMetadata(1,createdDate,1,lastModifiedDate,1);
//
//        resourceMetadataService.save(check);
//
//        ResourceMetadata found = resourceMetadataService.findbyCreator(1);
//
//        assertThat(found.getResourceCreator())
//                .isEqualTo(a);
//    }
//
//}
