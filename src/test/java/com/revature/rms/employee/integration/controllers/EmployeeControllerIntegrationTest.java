package com.revature.rms.employee.integration.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.rms.employee.EmployeeServiceApplication;
import com.revature.rms.employee.dtos.EmployeeDto;
import com.revature.rms.employee.entities.Employee;
import com.revature.rms.employee.services.EmployeeService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
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


import static org.hamcrest.CoreMatchers.is;
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
     * This ensures Employee.getAllEmployees hits the desired endpoint provided, produces a JSON object retrieved from
     * the database, and returns a status of 200 if everything was successful.
     * Set to Ignore while refactoring ResourceMetaData to the rms-core repository.
     * @throws Exception from perform()
     */
    @Test
    public void testGetAllEmployees() throws Exception {
        this.mockMvc.perform(get("/employees").contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));
    }

    @Test
    public void testGetEmployeeById() throws Exception{
        Mockito.when(employeeService.findById(1)).thenReturn(employee);
        RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/employees/id/1")
                .accept(MediaType.APPLICATION_JSON);
        mockMvc.perform(requestBuilder).andExpect(status().isOk())
                .andExpect(jsonPath("$.firstName", is("test")));
    }

    @Test
    public void testGetEmployeesByIds() throws Exception{
        Mockito.when(employeeService.findById(1)).thenReturn(employee);
        Mockito.when(employeeService.findById(2)).thenReturn(employee);
        RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/employees/ids/1,2")
                .accept(MediaType.APPLICATION_JSON);
        mockMvc.perform(requestBuilder).andExpect(status().isOk())
                .andExpect(jsonPath("$[0].firstName", is("test")));
    }

    @Test
    public void testAddNewEmployee() throws Exception{
        Mockito.when(employeeService.save(employeeDto, 1)).thenReturn(employee);
        RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/employees")
                .accept(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(employeeDto))
                .contentType(MediaType.APPLICATION_JSON)
                .header("Authorization", 1);
        mockMvc.perform(requestBuilder).andExpect(status().isOk());
    }
}
