package com.example.topkwords.utils;

import org.springframework.mock.web.MockMultipartFile;

public class CustomSizeMockMultipartFile extends MockMultipartFile {

    public CustomSizeMockMultipartFile() {
        super("file", "test data".getBytes());
    }

    @Override
    public long getSize() {
        return 2L * 1024 * 1024 * 1024; // 2Gb
    }
}
