package ru.velkomfood.datamax.print.controller;

import com.sap.conn.jco.JCoDestination;
import com.sap.conn.jco.JCoDestinationManager;
import com.sap.conn.jco.JCoException;
import com.sap.conn.jco.ext.DestinationDataProvider;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * Created by dpetrov on 13.11.16.
 */
public class ErpEngine {

    private static ErpEngine instance;
    static String DEST_NAME = "PRD500";
    private JCoDestination destination;

    private ErpEngine() { }

    public static ErpEngine getInstance() {
        if (instance == null) instance = new ErpEngine();
        return instance;
    }

    static {
    }

    static void createDestinationDataFile(String destName, Properties props) {

        File destFile = new File(destName + ".jcoDestination");

        if (!destFile.exists()) {

            try {

                FileOutputStream fos = new FileOutputStream(destFile, false);
                props.store(fos, "Production system, client 500");
                fos.close();

            } catch (IOException e) {
                new RuntimeException("Unable to create the destination file", e);
            }

        }

    }

    public void initSapConnection() throws JCoException {
        destination = JCoDestinationManager.getDestination(DEST_NAME);
    }

}
