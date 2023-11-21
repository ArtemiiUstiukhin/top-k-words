package com.example.topkwords.controller;

import com.example.topkwords.utils.WithMockUser;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
class HelloControllerTests {
    @Autowired
    private MockMvc api;

    @Test
    void anyoneCanAccessHelloEndpoint() throws Exception {
        api.perform(get("/"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsStringIgnoringCase("Hello!")));
    }
}
