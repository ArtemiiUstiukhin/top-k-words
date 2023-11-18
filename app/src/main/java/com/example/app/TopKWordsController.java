package com.example.app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/topk")
public class TopKWordsController {

    private final TopKWordsService topKWordsService;

    @Autowired
    public TopKWordsController(TopKWordsService topKWordsService) {
        this.topKWordsService = topKWordsService;
    }

    @PostMapping("/analyze")
    public ResponseEntity<List<String>> analyzeFile(
            @RequestParam("file") MultipartFile file,
            @RequestParam("k") int k) {
        try {
            List<String> topKWords = topKWordsService.findTopKWords(file, k);
            return ResponseEntity.ok(topKWords);
        } catch (IOException e) {
            // Handle file processing exception
            return ResponseEntity.badRequest().body(null);
        }
    }
}
