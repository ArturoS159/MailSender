package deafult;

import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableColumn;
import java.sql.*;
import java.util.ArrayList;

public class DatabaseManager {

    public static ArrayList<String> getUsers(){
        Connection connection = null;
        ResultSet rs = null;
        ArrayList<String> list = new ArrayList<>();
        try{
            Class.forName(Main.JDBC_DRIVER);
            connection = DriverManager.getConnection(Main.DB_URL, Main.user, Main.pass);
            Statement statement = connection.createStatement();
            rs = statement.executeQuery("SELECT * FROM appmail.users");
            while(rs.next()){
                list.add(rs.getString("email"));
            }
            return list;
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
        return list;
    }

    public static boolean checkConnectionDatabase(String loginName,String passwordName){
        Connection connection = null;
        try{
            Class.forName(Main.JDBC_DRIVER);
            connection = DriverManager.getConnection(Main.DB_URL, loginName, passwordName);
        }catch (Exception err){
            return false;
        }finally {
            try {
                connection.close();
            } catch (Exception err) {
                return false;
            }
        }
        return true;
    }

    public static ObservableList getRows(javafx.scene.control.TableView tab, ChoiceBox<String> choiceBox){
        Connection connection = null;
        ResultSet rs = null;
        ObservableList rowsData = FXCollections.observableArrayList();
        try {
            Class.forName(Main.JDBC_DRIVER);
            connection = DriverManager.getConnection(Main.DB_URL, Main.user, Main.pass);
            Statement statement = connection.createStatement();
            rs = statement.executeQuery("SELECT * FROM dziekanat."+choiceBox.getSelectionModel().getSelectedItem());
            for (int i = 0; i < rs.getMetaData().getColumnCount(); i++) {
                final int finalIdx = i;
                TableColumn<ObservableList<String>, String> column = new TableColumn<>(
                        rs.getMetaData().getColumnName(i+1)
                );
                column.setCellValueFactory(param ->
                        new ReadOnlyObjectWrapper<>(param.getValue().get(finalIdx))
                );
                tab.getColumns().add(column);
            }
            while (rs.next()) {
                ObservableList row = FXCollections.observableArrayList();
                for (int i = 1; i <= rs.getMetaData().getColumnCount(); i++) {
                    row.add(rs.getString(i));
                }
                rowsData.add(row);
            }
        } catch (Exception err) {
            err.printStackTrace();
        }finally {
            try{
                connection.close();
                rs.close();
            }catch (Exception err){
                err.printStackTrace();
            }
        }
        return rowsData;
    }

    public static ArrayList getTables(){
        ResultSet rs = null;
        Connection connection = null;
        ArrayList<String> tableList = new ArrayList<>();
        try{
            Class.forName(Main.JDBC_DRIVER);
            connection = DriverManager.getConnection(Main.DB_URL, Main.user, Main.pass);
            Statement statement = connection.createStatement();
            rs = statement.executeQuery("select * from pg_tables where schemaname = 'dziekanat'");
            while(rs.next()){
                tableList.add(rs.getString(2));
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
        return tableList;
    }
}
