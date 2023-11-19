package com.example.app;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.multipart;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@AutoConfigureMockMvc
class TopKWordsControllerTest {

    @MockBean
    private TopKWordsService topKWordsService;

    @Autowired
    private MockMvc mockMvc;
    @BeforeEach
    void setUp() throws IOException {
        when(topKWordsService.findTopKWords(any(MultipartFile.class), any(Integer.class)))
                .thenReturn(new ArrayList<>());
    }

    @Test
    void analyzeEmptyFile() throws Exception {
        mockMvc.perform(multipart("/api/topk/analyze")
                        .file("file", "".getBytes())
                        .param("k", "5")
                        .contentType(MediaType.MULTIPART_FORM_DATA))
                .andExpect(status().isBadRequest());
    }

    @Test
    void analyzeFileAndNegativeKValue() throws Exception {
        mockMvc.perform(multipart("/api/topk/analyze")
                        .file("file", "test file content".getBytes())
                        .param("k", "-1")
                        .contentType(MediaType.MULTIPART_FORM_DATA))
                .andExpect(status().isBadRequest());
    }

    @Test
    void analyzeRegularFile() throws Exception {
        mockMvc.perform(multipart("/api/topk/analyze")
                        .file("file", "test file content".getBytes())
                        .param("k", "5")
                        .contentType(MediaType.MULTIPART_FORM_DATA))
                .andExpect(status().isOk());
    }

//    @Test
//    void analyzeLargeFile() throws Exception {
//        MockMultipartFile largeFile = new CustomSizeMockMultipartFile();
//        mockMvc.perform(multipart("/api/topk/analyze")
//                        .file(largeFile)
//                        .param("k", "5")
//                        .contentType(MediaType.MULTIPART_FORM_DATA))
//                .andExpect(status().isPayloadTooLarge());
//    }
}