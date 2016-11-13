package ru.velkomfood.datamax.print;

import com.sap.conn.jco.JCoException;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import ru.velkomfood.datamax.print.controller.DbManager;
import ru.velkomfood.datamax.print.controller.ErpEngine;

import java.sql.SQLException;

public class Launcher extends Application {

    private DbManager dbm;
    private ErpEngine ee;

    // Widgets
    private Stage window;
    private Label lblHandUnit;
    private TextField fieldHandUnit;
    private Button btnOk;
    private Button btnClear;
    private Button btnCancel;

    private ListView<String> listUnits;
    private ObservableList<String> hunits;

    @Override
    public void init() {

        dbm = DbManager.getInstance();
        ee = ErpEngine.getInstance();

        try {
            dbm.openDbConnection();
            ee.initSapConnection();
        } catch (SQLException | JCoException ex) {
            ex.printStackTrace();
        }

    }

    @Override
    public void start(Stage primaryStage) throws Exception{

        window = primaryStage;

        window.setTitle("Datamax-Oneil testing");
        BorderPane borderPaneTop = new BorderPane();
        HBox hb = addHbox();
        VBox vb = addVbox();
        borderPaneTop.setTop(hb);
        borderPaneTop.setLeft(vb);

        Scene scene = new Scene(borderPaneTop, 800, 500);
        window.setScene(scene);

        window.show();

    }

    @Override
    public void stop() {

        try {
            dbm.closeDbConnection();
        } catch (SQLException dbe) {
            dbe.printStackTrace();
        }

    }

    // starter
    public static void main(String[] args) {
        launch(args);
    }

    // PRIVATE SECTION FOR LOCAL EVENTS

    private HBox addHbox() {

        HBox hBox = new HBox();
        hBox.setPadding(new Insets(15, 12, 15, 12));
        hBox.setSpacing(10);

        lblHandUnit = new Label("HU");
        fieldHandUnit = new TextField();

        btnOk = new Button("Ok");
        btnClear = new Button("Clear");
        btnCancel = new Button("Cancel");

        btnClear.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ENTER) {
                clearListView(hunits);
            }
        });

        btnCancel.setOnAction(event -> {
            closeWindow(window);
        });

        btnCancel.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ENTER) {
                closeWindow(window);
            }
        });

        fieldHandUnit.setOnKeyReleased(event -> {
            if (event.getCode() == KeyCode.ENTER) {
                try {
                    hunits.add(fieldHandUnit.getText());
                    Thread.sleep(300);
                    fieldHandUnit.clear();
                } catch (InterruptedException te) {
                    te.printStackTrace();
                }
            }
        });

        hBox.getChildren().addAll(lblHandUnit, fieldHandUnit, btnOk, btnClear, btnCancel);

        return hBox;
    }

    // Add vertical box
    private VBox addVbox() {

        VBox vBox = new VBox();
        vBox.setPadding(new Insets(10));
        vBox.setSpacing(8);

        listUnits = new ListView<>();
        hunits = FXCollections.observableArrayList();
        listUnits.setItems(hunits);

        vBox.getChildren().add(listUnits);

        return vBox;

    }

    private void clearListView(ObservableList<String> list) {
        list.clear();
    }

    // Close window
    private void closeWindow(Stage stage) {
        stage.close();
    }
}
