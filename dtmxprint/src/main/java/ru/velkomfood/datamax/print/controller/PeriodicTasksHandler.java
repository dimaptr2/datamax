package ru.velkomfood.datamax.print.controller;

import com.sap.conn.jco.JCoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * Created by dpetrov on 07.11.16.
 */

@Component
public class PeriodicTasksHandler {

    @Autowired
    private ErpDataEngine erpDataEngine; // SAP connection

    // Run every 1 minutes
    @Scheduled(cron = "0 */1 * * * *") //second, minute, hour, day, month, weekday
    public void synchronizeData() throws IOException, JCoException {

    } // end of synchronization method

}
