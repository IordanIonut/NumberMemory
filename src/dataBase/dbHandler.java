package dataBase;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class dbHandler extends Configs{
    Connection dbconnection;

    public Connection getConnection() {
        String connectionString="jdbc:mysql://"+Configs.dbhost+":"+Configs.dbport+"/"+Configs.dbname;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        }catch (ClassNotFoundException e){
            e.printStackTrace();
        }
        try {
            dbconnection = DriverManager.getConnection(connectionString, Configs.dbuser, Configs.dbpass);
        }catch (SQLException e){
            e.printStackTrace();
        }
        return dbconnection;
    }
}
