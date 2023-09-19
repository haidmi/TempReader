package com.sm.tempreader;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class })
public class TempReaderApplication {

    public static void main(String[] args) {
        SpringApplication.run(TempReaderApplication.class, args);
    }

}
