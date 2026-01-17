package org.pesapal;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) {
        try {
            // Create a database connection
            String url = "db_url"; // change your DB name
            String user = "db_user";   // change your DB user
            String password = "db_password";    // change your DB password
            Connection connection = DriverManager.getConnection(url, user, password);

            // Pass connection to RealDbEngine
            DatabaseEngine engine = new RealDbEngine(connection);

            // Start the REPL
            DbRepl repl = new DbRepl(engine);
            repl.start();

            // Close connection when done
            connection.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}


