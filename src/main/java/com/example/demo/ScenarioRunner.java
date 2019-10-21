package com.example.demo;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import static java.util.concurrent.TimeUnit.SECONDS;
import static org.awaitility.Awaitility.await;

@Slf4j
@AllArgsConstructor
public class ScenarioRunner implements Runnable {

    private Scenario scenario;
    private Dispatcher dispatcher;

    @Override
    public void run() {
        try {
            scenario.getEvents().forEach(dispatcher::dispatch);
            await().atMost(15, SECONDS).until(scenario.getVerified());
        } catch (Exception e) {
            log.error("UnverifiedScenario");
        }
    }
}
