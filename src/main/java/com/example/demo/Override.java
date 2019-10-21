package com.example.demo;

import java.util.Map.Entry;
import java.util.function.Supplier;

public interface Override {
    Entry<String, Supplier<String>> get();
    String applyTo(String content);
}
