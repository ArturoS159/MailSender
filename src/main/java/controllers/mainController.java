package controllers;

import deafult.DatabaseManager;
import deafult.MailManager;
import deafult.Main;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;

import java.sql.*;
import java.util.ArrayList;
import java.util.Observable;


public class mainController {
    public javafx.scene.control.TableView tab;
    @FXML
    private AnchorPane pane;
    @FXML
    private ChoiceBox choiseBox;

    public void initialize(){
        tab.setColumnResizePolicy(TableView.UNCONSTRAINED_RESIZE_POLICY);
        ResultSet rs = null;
        Connection connection = null;
        try{
            Class.forName(Main.JDBC_DRIVER);
            connection = DriverManager.getConnection(Main.DB_URL, Main.user, Main.pass);
            Statement statement = connection.createStatement();
            rs = statement.executeQuery("select * from pg_tables where schemaname = 'dziekanat'");
            while(rs.next()){
                choiseBox.getItems().add(rs.getString(2));
            }
        }catch (Exception err){
            err.printStackTrace();
        }finally {
            try {
                connection.close();
                rs.close();
            } catch (SQLException err) {
                err.printStackTrace();
            }
        }
    }

    @FXML
    private void selectTableButton(){
        ObservableList data = FXCollections.observableArrayList();
        if(!choiseBox.getSelectionModel().isEmpty()){
            tab.getColumns().clear();
            Connection connection = null;
            ResultSet rs = null;
            try{
                Class.forName(Main.JDBC_DRIVER);
                connection = DriverManager.getConnection(Main.DB_URL, Main.user, Main.pass);
                Statement statement = connection.createStatement();
                rs = statement.executeQuery("SELECT * FROM dziekanat."+choiseBox.getSelectionModel().getSelectedItem().toString());
                TableColumn col = null;
                tab.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
                for (int i = 0; i < rs.getMetaData().getColumnCount(); i++) {
                    col = new TableColumn(rs.getMetaData().getColumnName(i + 1));
                    tab.getColumns().add(col);
                }
                while (rs.next()) {
                    ObservableList row = FXCollections.observableArrayList();
                    for (int i = 1; i <= rs.getMetaData().getColumnCount(); i++) {
                        row.add(rs.getString(i));
                    }
                    data.add(row);
                }

                for(int s=0;s<data.size();s++){
                    System.out.println(data.get(s));
                    tab.getItems().add(data.get(s));
                }

            }catch (Exception err){
                err.printStackTrace();
            }finally {
                try {
                    connection.close();
                    rs.close();
                } catch (SQLException err) {
                    err.printStackTrace();
                }
            }
        }

    }


}
