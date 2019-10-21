package com.example.demo;

import com.example.demo.config.TestData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.task.TaskExecutor;

@SpringBootApplication
public class DemoApplication implements CommandLineRunner {

    @Autowired
    TaskExecutor threadPoolTaskExecutor;

    Dispatcher dispatcher = (event) -> System.out.println(event.getContent());

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);

    }

    @Override
    public void run(String... args) throws Exception {
        TestData.scenarios.stream()
            .map(s -> new ScenarioRunner(s, dispatcher))
            .forEach(threadPoolTaskExecutor::execute);
    }
}
