//package com.revature.rms.employee;
//
//import static org.hamcrest.Matchers.containsString;
//import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
//
//
//import org.junit.jupiter.api.Test;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.web.servlet.MockMvc;
//
//@SpringBootTest
//@AutoConfigureMockMvc
//public class ControllerTest {
//
//    @Autowired
//    private MockMvc mockMvc;
//
//    @Test
//    public void testEmpController() throws Exception {
//        this.mockMvc.perform(get("/employee/test")).andDo(print()).andExpect(status().isOk())
//                .andExpect(content().string(containsString("employeeController loaded")));
//    }
//
//    @Test
//    public void testResourceController() throws Exception {
//        this.mockMvc.perform(get("/resource/test")).andDo(print()).andExpect(status().isOk())
//                .andExpect(content().string(containsString("resourceController loaded")));
//    }
//}