package ru.velkomfood.datamax.print.controller;

import com.sap.conn.jco.JCoDestination;
import com.sap.conn.jco.JCoDestinationManager;
import com.sap.conn.jco.JCoException;
import com.sap.conn.jco.ext.DestinationDataProvider;

import java.io.File;
import java.io.FileOutputStream;
import java.util.Properties;

/**
 * Created by dpetrov on 07.11.16.
 */
public class ErpDataEngine {

    static String DEST_NAME = "PRD500RUPS15";

    private JCoDestination jCoDestination;

    static {
        Properties connectProperties = new Properties();
        connectProperties.setProperty(DestinationDataProvider.JCO_ASHOST, "XXXXXXX");
        connectProperties.setProperty(DestinationDataProvider.JCO_SYSNR, "XX");
        connectProperties.setProperty(DestinationDataProvider.JCO_R3NAME, "XXX");
        connectProperties.setProperty(DestinationDataProvider.JCO_CLIENT, "XXX");
        connectProperties.setProperty(DestinationDataProvider.JCO_USER, "XXXXXX");
        connectProperties.setProperty(DestinationDataProvider.JCO_PASSWD, "XXXXXX");
        connectProperties.setProperty(DestinationDataProvider.JCO_LANG, "XX");
        createDestinationFile(DEST_NAME, connectProperties);
    }

    static void createDestinationFile(String destName, Properties props) {

        File destCfg = new File(destName + ".jcoDestination");

        if (!destCfg.exists() && !destCfg.isDirectory()) {

            try {
                FileOutputStream fos = new FileOutputStream(destCfg, false);
                props.store(fos, "PRD instance");
                fos.close();
            } catch (Exception e) {
                throw new RuntimeException("Unable to create the destination file", e);
            }

        }
    }

    // Main public section

    public void initConnection() throws JCoException {
        jCoDestination = JCoDestinationManager.getDestination(DEST_NAME);
    }

    public boolean connectionIsValid() throws JCoException {
        if (jCoDestination == null) return false;
        else return true;
    }



}
