package com.ebms.mtr_rdng;


import com.ebms.mtr_rdng.domain.model.ResidentialMeterReading;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.core.KafkaTemplate;

import java.time.LocalDate;
import java.time.Month;
import java.time.YearMonth;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

@SpringBootApplication
public class KafkaQueueApplication {

    public static void main(String[] args){
        SpringApplication.run(KafkaQueueApplication.class,args);
    }

   /* @Bean
    CommandLineRunner commandLineRunner(KafkaTemplate<String, Object> kafkaTemplate){
        return args -> {

            int year = 2020;

            List<Long> meterIds = Arrays.asList(86419618L,
                    78679679L,
                    47866787L,
                    16475745L,
                    86543424L,
                    63475675L,
                    45367567L,
                    45633543L,
                    67463523L,
                    52567667L,
                    78675445L,
                    87696745L,
                    23435465L,
                    45764646L,
                    43634522L,
                    43676763L,
                    87545223L,
                    14556565L,
                    65654555L);

            List<Month> months = Arrays.asList(Month.JANUARY,
                    Month.FEBRUARY,
                    Month.MARCH,
                    Month.APRIL,
                    Month.MAY,
                    Month.JUNE,
                    Month.JULY,
                    Month.AUGUST,
                    Month.SEPTEMBER,
                    Month.OCTOBER,
                    Month.NOVEMBER,
                    Month.DECEMBER);

            List<Long> employeeIds = Arrays.asList(
                    123456789L,987654321L,321654987L,789456123L,123654789L
            );

            for(Long meterId : meterIds) {

                for (int i = 0; i < months.size(); i++) {
                    YearMonth billingCycle;
                    if (i == 0) {
                        billingCycle = YearMonth.of(year-1, Month.DECEMBER);
                    } else {
                        billingCycle = YearMonth.of(year, months.get(i - 1));
                    }



                    ResidentialMeterReading residentialMeterReading = ResidentialMeterReading.builder()
                            .unitReading(new Random().nextInt(1200))
                            .meter_id(meterId.longValue())
                            .billingCycle(billingCycle)
                            .reportingDate(LocalDate.of(year, months.get(i), 5))
                            .reportingEmp(employeeIds.get(new Random().nextInt(employeeIds.size())))
                            .build();

                    kafkaTemplate.send("METER-READING-DEV", residentialMeterReading);

                }

            }


        };
    }*/
}
