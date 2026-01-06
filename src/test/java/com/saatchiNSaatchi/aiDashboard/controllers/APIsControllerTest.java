package com.saatchiNSaatchi.aiDashboard.controllers;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

@SpringBootTest(classes = APIsController.class)
@AutoConfigureMockMvc
public class APIsControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @Test
    public void greetingShouldReturnDefaultMessage() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api"))
                .andDo(print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string("Hello, World!"));
    }

    @Test
    void getAIStats() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("http://localhost:8080/api/usage")
                .contentType(MediaType.APPLICATION_JSON)
                .header(HttpHeaders.ORIGIN, "http://specific-allowed-origin.com") // Simulate cross-origin
                .header(HttpHeaders.ACCESS_CONTROL_REQUEST_METHOD, "GET"))
                .andDo(print())
                .andExpect(MockMvcResultMatchers.status().is(406));
    }
}
