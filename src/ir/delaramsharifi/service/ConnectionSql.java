package ir.delaramsharifi.service;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectionSql {

    Connection connection;

    public Connection getConnection() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/java", "root", "Aram3689");
        } catch (Exception var4) {
            var4.printStackTrace();
        }

        return connection;
    }

}
