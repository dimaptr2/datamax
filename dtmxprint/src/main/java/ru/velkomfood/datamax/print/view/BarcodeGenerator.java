package ru.velkomfood.datamax.print.view;

import datamaxoneil.connection.Connection_TCP;
import datamaxoneil.printer.DocumentDPL;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Created by dpetrov on 10.11.16.
 */

public class BarcodeGenerator {

    private Connection_TCP connection_tcp;
    private DocumentDPL documentDPL;
    private Properties printerProperties;

    public void initPrinter() {

        printerProperties = new Properties();
        InputStream input = null;

        try {

            input = new FileInputStream("printer.properties");
            printerProperties.load(input);
            connection_tcp = Connection_TCP.createClient(printerProperties.getProperty("printer_ip"),
                    Integer.parseInt(printerProperties.getProperty("printer_port")));

        } catch (IOException fex) {
            fex.printStackTrace();
        } finally {
            if (input != null) {
                try {
                    input.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    } // initPrinter

    public void openSession() throws IOException {
        connection_tcp.open();
    }

    public void closeSession() {
        if (connection_tcp != null && connection_tcp.getIsActive())
            connection_tcp.close();
    }

}
