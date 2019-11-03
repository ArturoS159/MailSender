package deafult;

import java.sql.*;
import java.util.ArrayList;

public class DatabaseManager {

    public static ArrayList getUsers(){
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
}
