package com.example.demo;

import org.apache.commons.io.IOUtils;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

public class Resources {

    private static final String SCENARIO_FILE_ROOT = "scenarios";

    public static String get(String path) {
        try {
            Resource resource = new ClassPathResource(SCENARIO_FILE_ROOT + "/" + path);
            return IOUtils.toString(resource.getInputStream(), StandardCharsets.UTF_8.name());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
