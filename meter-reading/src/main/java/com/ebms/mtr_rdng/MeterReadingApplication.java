package com.ebms.mtr_rdng;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.PropertySource;


@SpringBootApplication
@PropertySource("classpath:application-db.properties")
public class MeterReadingApplication {

    public static void main(String[] args) {
        SpringApplication.run(MeterReadingApplication.class, args);
    }

}
