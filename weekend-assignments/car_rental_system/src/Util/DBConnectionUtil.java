package Util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnectionUtil {
    private Connection conn;
    private static final DBConnectionUtil dbConnectionUtil;
    static {
        dbConnectionUtil=new DBConnectionUtil();
    }
    DBConnectionUtil(){}
    public static DBConnectionUtil getInstance(){
        return dbConnectionUtil;
    }
    public Connection getConnection(){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn= DriverManager.getConnection("jdbc:mysql://localhost:3306/car_renatl","root","root");


        } catch (Exception e) {
            e.getMessage();
        }
        return conn;
    }
}
