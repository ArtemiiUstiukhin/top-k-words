package com.example.app;

import com.example.app.logic.TopKWordsService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

class TopKWordsServiceTest {

    private TopKWordsService topKWordsService;

    @BeforeEach
    void setUp() {
        topKWordsService = new TopKWordsService();
    }
    @Test
    void findTopKWordsSimple() throws IOException {
        int k = 2;
        MultipartFile file = new MockMultipartFile("testFile.txt", "test test data".getBytes());
        List<String> topKWords = topKWordsService.findTopKWords(file, k);

        List<String> expectedWords = Arrays.asList("test", "data");
        Assertions.assertLinesMatch(expectedWords, topKWords);
    }

    @Test
    void findTopKWordsUpperCase() throws IOException {
        int k = 2;
        MultipartFile file = new MockMultipartFile("testFile.txt", "!test tEst,; data Test Data".getBytes());
        List<String> topKWords = topKWordsService.findTopKWords(file, k);

        List<String> expectedWords = Arrays.asList("test", "data");
        Assertions.assertLinesMatch(expectedWords, topKWords);
    }
}