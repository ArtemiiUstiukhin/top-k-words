package com.example.app;

import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@SpringBootTest
@AutoConfigureMockMvc
public class CacheTests {

    @MockBean
    private TopKWordsService topKWordsService;

    @Autowired
    private MockMvc mockMvc;

    String testData = "test data";
    int k = 5;
    @BeforeEach
    void setUp() throws IOException {
        // Mock service behavior
        when(topKWordsService.findTopKWords(any(MultipartFile.class), any(Integer.class)))
                .thenReturn(new ArrayList<>());
    }

//    @Test
//    void testAnalyzeCacheWithSamePayload() throws Exception {
//        // Perform the request
//        mockMvc.perform(multipart("/api/topk/analyze")
//                        .file("file", testData.getBytes())
//                        .param("k", Integer.toString(k))
//                        .contentType(MediaType.MULTIPART_FORM_DATA))
//                .andExpect(status().isOk());
//
//        // Verify that findTopKWords method has been called exactly once
//        verify(topKWordsService, times(1)).findTopKWords(any(MultipartFile.class), anyInt());
//
//        // Perform the request with the same payload
//        mockMvc.perform(multipart("/api/topk/analyze")
//                        .file("file", testData.getBytes())
//                        .param("k", Integer.toString(k))
//                        .contentType(MediaType.MULTIPART_FORM_DATA))
//                .andExpect(status().isOk());
//
//
//        // Verify that findTopKWords method has been called exactly once
//        verify(topKWordsService, times(1)).findTopKWords(any(MultipartFile.class), anyInt());
//    }
}
