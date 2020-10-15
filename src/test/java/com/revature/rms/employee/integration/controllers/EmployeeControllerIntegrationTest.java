package com.revature.rms.employee.integration.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.rms.employee.EmployeeServiceApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;


import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = EmployeeServiceApplication.class)
@AutoConfigureMockMvc
public class EmployeeControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

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
    public void testGetAllEmployeesWithExistingEmployeesExpecting200() throws Exception {
        this.mockMvc.perform(get("/employees").contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));
    }
}
