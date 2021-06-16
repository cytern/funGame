package com.dam.gaming;

import com.dam.gaming.config.thread.SpringThreadPoolConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
@EnableConfigurationProperties({SpringThreadPoolConfig.class} )
public class GamingApplication {

    public static void main(String[] args) {
        SpringApplication.run(GamingApplication.class, args);
    }

}
