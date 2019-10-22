package deafult;

import java.sql.*;
import java.util.ArrayList;

public class DatabaseManager {

    public ArrayList getUsers(){
        Connection connection = null;
        ArrayList<String> list = new ArrayList<>();
        try{
            Class.forName(Main.JDBC_DRIVER);
            connection = DriverManager.getConnection(Main.DB_URL, Main.USER, Main.PASS);
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("SELECT * FROM appmail.users");

            while(rs.next()){
                list.add(rs.getString("email"));
            }
            return list;
        }catch (Exception err){
            err.printStackTrace();
        }finally {
            try {
                connection.close();
            } catch (SQLException err) {
                err.printStackTrace();
            }
        }
        return list;
    }


}
