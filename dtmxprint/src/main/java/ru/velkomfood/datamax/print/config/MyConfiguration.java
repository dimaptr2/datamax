package ru.velkomfood.datamax.print.config;

import datamaxoneil.connection.Connection_TCP;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.velkomfood.datamax.print.controller.ErpDataEngine;

/**
 * Created by dpetrov on 07.11.16.
 */
@Configuration
public class MyConfiguration {

    @Bean
    public ErpDataEngine erpDataEngine() {
        return new ErpDataEngine();
    }

}
