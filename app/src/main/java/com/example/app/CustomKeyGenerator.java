package com.example.app;

import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import org.apache.commons.codec.digest.DigestUtils;

import java.lang.reflect.Method;

@Component("customKeyGenerator")
public class CustomKeyGenerator implements KeyGenerator {
    
    @Override
    public Object generate(Object target, Method method, Object... params) {
        StringBuilder key = new StringBuilder();
        for (Object param : params) {
            if (param instanceof MultipartFile) {
                String fileHash = getMultipartFileHash((MultipartFile) param);
                key.append(fileHash);
            } else {
                key.append(param.toString());
            }
        }
        return key.toString();
    }

    private String getMultipartFileHash(MultipartFile file) {
        try {
            String fileHash = DigestUtils.md5Hex(file.getBytes());
            return fileHash;
        } catch(Exception e) {
            System.out.println("Error on trying to calculate MultipartFile hash: " + e);
			return file.getOriginalFilename();
        }
    }
}
