package com.example.demo.config;

import com.example.demo.Override;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

@Getter
public enum NamedOverride implements Override {
    RANDOM_ORDER_ID(Map.entry("${ORDER_ID}", "sfsdfsd")),
    SUCCESSFUL_CARD_TOKEN(Map.entry("${CARD_TOKEN}", "sdfsdfsdfsdfds")),
    FAILURE_CARD_TOKEN(Map.entry("${CARD_TOKEN}", "sdfsdfsdfsdfds"));

    private Entry<String, String> override;

    NamedOverride(Entry<String, String> override) {
        this.override = override;
    }

    @java.lang.Override
    public Entry<String, String> get() {
        return override;
    }

    public String applyTo(String content) {
        return content.replace(this.override.getKey(), this.override.getValue());
    }

    public static List<Override> DEFAULTS = new ArrayList<>();
    static {
        DEFAULTS.add(RANDOM_ORDER_ID);
    }
}
