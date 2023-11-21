package com.example.topkwords.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class AuthControllerTests {
    @Autowired
    private MockMvc api;

    @Test
    void authWithWrongCredentials_GetRejected() throws Exception {
        String jsonPayload = "{\"email\":\"test@test.com\",\"password\":\"test123\"}";

        api.perform(post("/auth/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonPayload))
                .andExpect(status().is4xxClientError());
    }

    @Test
    void authWithProperCredentials_GetToken() throws Exception {
        String jsonPayload = "{\"email\":\"test@test.com\",\"password\":\"test\"}";

        api.perform(post("/auth/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonPayload))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.token").exists());
    }
}
