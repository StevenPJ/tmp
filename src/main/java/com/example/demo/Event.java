package com.example.demo;

import com.google.gson.Gson;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.HashMap;
import java.util.Map;

@AllArgsConstructor
@Getter
public class Event {
    private String destination;
    private String content;
    private Map<String, String> props = new HashMap<>();

    public String props(String key) {
        return this.props.get(key);
    }
}
