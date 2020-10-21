package com.revature.rms.employee.integration.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.rms.employee.EmployeeServiceApplication;
import com.revature.rms.employee.dtos.EmployeeDto;
import com.revature.rms.employee.entities.Employee;
import com.revature.rms.employee.services.EmployeeService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import java.util.Arrays;
import java.util.List;
import static org.hamcrest.CoreMatchers.is;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = EmployeeServiceApplication.class)
@AutoConfigureMockMvc
public class EmployeeControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    EmployeeService employeeService;

    Employee employee;
    EmployeeDto employeeDto;
    ObjectMapper mapper;

    @Before
    public void setUp() {
        employee = new Employee("test", "test", "test", "test");
        employeeDto = new EmployeeDto(1, "test", "test", "test", "test", null);
        mapper = new ObjectMapper();
    }

    public static String asJSON(final Object object) {
        try {
            return new ObjectMapper().writeValueAsString(object);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * This ensures Employee.getEmployeeById hits the desired endpoint provided, produces a JSON object retrieved from
     * the database, and returns a status of 200 if everything was successful.
     * @throws Exception from perform()
     */
    @Test
    public void testGetEmployeeById() throws Exception{
        Mockito.when(employeeService.findById(1)).thenReturn(employee);
        RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/employees/id/1")
                .accept(MediaType.APPLICATION_JSON);
        mockMvc.perform(requestBuilder).andExpect(status().isOk())
                .andExpect(jsonPath("$.firstName", is("test")));
    }

    /**
     * This ensures Employee.getEmployeesByIds hits the desired endpoint provided, produces a JSON object retrieved from
     * the database, and returns a status of 200 if everything was successful.
     * @throws Exception from perform()
     */
    @Test
    public void testGetEmployeesByIds() throws Exception{
        Mockito.when(employeeService.findById(1)).thenReturn(employee);
        Mockito.when(employeeService.findById(2)).thenReturn(employee);
        RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/employees/ids/1,2")
                .accept(MediaType.APPLICATION_JSON);
        mockMvc.perform(requestBuilder).andExpect(status().isOk())
                .andExpect(jsonPath("$[0].firstName", is("test")));
    }

    /**
     * This ensures Employee.addNewEmployee hits the desired endpoint provided, produces a JSON object retrieved from
     * the database, and returns a status of 200 if everything was successful.
     * @throws Exception from perform()
     */
    @Test
    public void testAddNewEmployee() throws Exception{
        Mockito.when(employeeService.save(Mockito.any(EmployeeDto.class), anyInt())).thenReturn(employee);
        RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/employees")
                .accept(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(employeeDto))
                .contentType(MediaType.APPLICATION_JSON)
                .header("Authorization", 1);
        mockMvc.perform(requestBuilder).andExpect(status().isOk())
                .andExpect(jsonPath("$.firstName", is("test")));
    }

    /**
     * This ensures Employee.updateEmployee hits the desired endpoint provided, produces a JSON object retrieved from
     * the database, and returns a status of 200 if everything was successful.
     * @throws Exception from perform()
     */
    @Test
    public void testUpdateEmployee() throws Exception{
        Mockito.when(employeeService.update(ArgumentMatchers.any())).thenReturn(employee);
        RequestBuilder requestBuilder = MockMvcRequestBuilders.put("/employees")
                .accept(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(employeeDto))
                .contentType(MediaType.APPLICATION_JSON);
        mockMvc.perform(requestBuilder).andExpect(status().isOk())
                .andExpect(jsonPath("$.firstName", is("test")));

    }

    /**
     * This ensures Employee.getEmployeeByFirstName hits the desired endpoint provided, produces a JSON object retrieved from
     * the database, and returns a status of 200 if everything was successful.
     * @throws Exception from perform()
     */
    @Test
    public void testGetByFirstName() throws Exception{
        Mockito.when(employeeService.findByFirstname("test")).thenReturn(employee);
        RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/employees/firstname")
                .accept(MediaType.APPLICATION_JSON)
                .param("firstName", "test");
        mockMvc.perform(requestBuilder).andExpect(status().isOk())
                .andExpect(jsonPath("$.firstName", is("test")));
    }

    /**
     * This ensures Employee.getAllEmployees hits the desired endpoint provided, produces a JSON object retrieved from
     * the database, and returns a status of 200 if everything was successful.
     * @throws Exception from perform()
     */
    @Test
    public void testGetAllEmployees() throws Exception {
        this.mockMvc.perform(get("/employees").contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));
    }

    /**
     * This ensures Employee.deleteEmployeeById hits the desired endpoint provided, produces a JSON object retrieved from
     * the database, and returns a status of 200 if everything was successful.
     * @throws Exception from perform()
     */
    @Test
    public void testDeleteEmployeeById() throws Exception{
        RequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/employees/1")
                .accept(MediaType.APPLICATION_JSON);
        mockMvc.perform(requestBuilder).andExpect(status().isOk());
    }

    /**
     * This ensures Employee.getEmployeeByOwnerId hits the desired endpoint provided, produces a JSON object retrieved from
     * the database, and returns a status of 200 if everything was successful.
     * @throws Exception from perform()
     */
    @Test
    public void testGetEmployeesByOwnerId() throws Exception{
        List<Employee> employeeList = Arrays.asList(employee);
        Mockito.when(employeeService.findByOwnerId(1)).thenReturn(employeeList);
        RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/employees/owners/id/1")
                .accept(MediaType.APPLICATION_JSON);
        mockMvc.perform(requestBuilder).andExpect(status().isOk())
                .andExpect(jsonPath("$[0].firstName", is("test")));
    }
}
