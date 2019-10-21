package com.example.demo.config;

import com.example.demo.EventType;
import com.example.demo.Override;
import com.example.demo.Scenario;
import com.example.demo.ScenarioBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.TaskExecutor;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;

@Configuration
public class TestData {

    public static Callable<Boolean> declinedOrderInCos = () -> true;

    private static final ScenarioBuilder pricedBasket = Scenario.builder()
        .event(EventType.CREATED)
        .override(() -> Map.entry("${ORDER_ID}", "something"))
        .verifier(declinedOrderInCos);


    public static final Scenario CHA_SUCCESS = pricedBasket
        .description("CHA successfully take payment")
        .override(NamedOverride.SUCCESSFUL_CARD_TOKEN)
        .build();

    public static final Scenario CHA_FAILURE = pricedBasket
        .description("CHA failed to take payment")
        .override(NamedOverride.FAILURE_CARD_TOKEN)
        .build();

    public static List<Scenario> scenarios = Arrays.asList(CHA_SUCCESS, CHA_FAILURE);

    @Bean
    public TaskExecutor threadPoolTaskExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(4);
        executor.setMaxPoolSize(4);
        executor.setThreadNamePrefix("default_task_executor_thread");
        executor.initialize();
        return executor;
    }

}
