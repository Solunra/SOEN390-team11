package com.soen390.team11.schedule;

import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

@Configuration
@EnableScheduling
public class ScheduleConfig {

    public static final int ONE_MINUTE = 1000 * 60;
    public static final int ONE_HOUR = 1000 * 60 * 60;

    @Scheduled(fixedRate = ONE_MINUTE)
    public void doesSomething()
    {
        System.out.println("It's been a minute");
    }
}
