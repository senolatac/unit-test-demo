package com.example.worker;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;

/**
 * @author sa
 * @date 14.06.2021
 * @time 11:15
 */
@SpringBootApplication
public class WorkerAppConfig
{
    public static void main(String[] args)
    {
        SpringApplication.run(WorkerAppConfig.class, args);
    }
}
