package com.example.app;

import org.springframework.mock.web.MockMultipartFile;

import java.io.IOException;
import java.io.InputStream;

public class CustomSizeMockMultipartFile extends MockMultipartFile {

    public CustomSizeMockMultipartFile() {
        super("file", "test data".getBytes());
    }

    @Override
    public long getSize() {
        return 2L * 1024 * 1024 * 1024; // 2Gb
    }
}