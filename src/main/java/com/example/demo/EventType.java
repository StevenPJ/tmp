package com.example.demo;

import lombok.Getter;

@Getter
public enum EventType {
    CREATED("created.json", "gol.v1.created");

    private String path;
    private String destination;

    EventType(String path, String destination) {
        this.path = path;
        this.destination = destination;
    }

}
