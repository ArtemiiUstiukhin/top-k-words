package com.example.topkwords.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class HelloController {
    @GetMapping("/")
    public String hello() {
        return "Hello! This is a simple Web API that takes a text file and finds the top K most frequent words";
    }
}
