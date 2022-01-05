package com.cjx.server;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.core.task.TaskExecutor;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

@SpringBootApplication
@MapperScan("com.cjx.server.mapper")
@EnableScheduling
public class LinkDoApplication {

    public static void main(String[] args) {
        SpringApplication.run(LinkDoApplication.class , args);
    }

    @Primary
    @Bean
    public TaskExecutor primaryTaskExecutor(){
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        return executor;
    }
}
