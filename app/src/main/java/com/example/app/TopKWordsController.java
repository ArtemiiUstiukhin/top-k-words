package com.example.app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/topk")
public class TopKWordsController {

    private final TopKWordsService topKWordsService;
    private final int MAX_FILE_SIZE = 1024 * 1024 * 1024; // 1Gb

    @Autowired
    public TopKWordsController(TopKWordsService topKWordsService) {
        this.topKWordsService = topKWordsService;
    }

    @PostMapping("/analyze")
    public ResponseEntity<?> analyzeFile(
            @RequestParam("file") MultipartFile file,
            @RequestParam("k") int k) {
        try {
            if (file.isEmpty())
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("File is empty");

            if (k <= 0)
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Invalid value for K. K must be greater than 0");

            if (file.getSize() > MAX_FILE_SIZE)
                return ResponseEntity.status(HttpStatus.REQUEST_ENTITY_TOO_LARGE)
                    .body("File size exceeds the maximum allowed limit");
            
            List<String> topKWords = topKWordsService.findTopKWords(file, k);
            return ResponseEntity.ok(topKWords);
        } catch (IOException e) {
            // Handle file processing exception
            return ResponseEntity.badRequest().body(null);
        }
    }
}
