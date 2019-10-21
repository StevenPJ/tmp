package com.example.demo;

import java.util.Map.Entry;

public interface Override {
    Entry<String, String> get();
    String applyTo(String content);
}
