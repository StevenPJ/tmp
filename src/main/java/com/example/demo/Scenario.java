package com.example.demo;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;
import java.util.concurrent.Callable;

@Getter
@AllArgsConstructor
public class Scenario {
    private String description;
    private List<Event> events;
    private Callable<Boolean> verified;

    public static ScenarioBuilder builder() {
        return new ScenarioBuilder();
    }
}
