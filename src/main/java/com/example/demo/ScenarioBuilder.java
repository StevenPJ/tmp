package com.example.demo;

import com.example.demo.config.NamedOverride;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.stream.Collectors;


public class ScenarioBuilder {
    private String description;
    private List<EventType> types = new ArrayList<>();
    private List<Override> overrides = NamedOverride.DEFAULTS;
    private Callable<Boolean> verifier = () -> true;

    public ScenarioBuilder event(EventType event) {
        this.types.add(event);
        return this;
    }

    public ScenarioBuilder override(Override override) {
        this.overrides.add(override);
        return this;
    }

    public ScenarioBuilder description(String description) {
        this.description = description;
        return this;
    }

    public ScenarioBuilder verifier(Callable<Boolean> verifier) {
        this.verifier = verifier;
        return this;
    }

    public Scenario build() {
        List<Event> events = types.stream().map(this::toEvent).collect(Collectors.toList());
        return new Scenario(description, events, verifier);
    }

    private Event toEvent(EventType type) {
        String content = Resources.get(type.getPath());
        for (Override o : overrides) {
            content = o.applyTo(content);
        }
        return new Event(type.getDestination(), content, new HashMap<>());
    }

}
