package com.ebms.mtr_rdng.db.domain;

import com.ebms.mtr_rdng.db.domain.repository.DatabaseRepository;
import com.ebms.mtr_rdng.db.domain.repository.MeterReadingRepository;
import org.jooq.DSLContext;
import org.jooq.SQLDialect;
import org.jooq.impl.DSL;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@Configuration
public class DatabaseConfiguration {

    @Value("${spring.datasource.username}")
    private String userName;

    @Value("${spring.datasource.password}")
    private String password;

    @Value("${spring.datasource.url}")
    private String databaseURL;


    public Connection connection() throws SQLException {
        return DriverManager.getConnection(databaseURL,userName,password);
    }

    public DSLContext context() throws SQLException {
        return DSL.using(connection(), SQLDialect.MYSQL);
    }

    @Bean
    public MeterReadingRepository databaseRepository() throws SQLException {
        return new MeterReadingRepository(context());
    }


}
