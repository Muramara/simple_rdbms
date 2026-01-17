package org.mydb;

import java.sql.*;

public class RealDbEngine implements DatabaseEngine {
    private Connection conn;

    public RealDbEngine(Connection conn) {
        this.conn = conn;
    }

    @Override
    public QueryResult execute(String sql) {
        try (Statement stmt = conn.createStatement()) {
            boolean isResultSet = stmt.execute(sql);

            if (isResultSet) {
                ResultSet rs = stmt.getResultSet();
                // Convert ResultSet to string
                StringBuilder sb = new StringBuilder();
                ResultSetMetaData md = rs.getMetaData();
                int cols = md.getColumnCount();
                while (rs.next()) {
                    for (int i = 1; i <= cols; i++) {
                        sb.append(rs.getString(i)).append("\t");
                    }
                    sb.append("\n");
                }
                return new QueryResult(sb.toString());
            } else {
                int count = stmt.getUpdateCount();
                String command = sql.trim().split("\\s+")[0].toUpperCase();
                switch (command) {
                    case "INSERT":
                        return new QueryResult("INSERT 0 " + count);
                    case "UPDATE":
                        return new QueryResult("UPDATE " + count);
                    case "DELETE":
                        return new QueryResult("DELETE " + count);
                    case "CREATE":
                        return new QueryResult("CREATE TABLE");
                    case "DROP":
                        return new QueryResult("DROP TABLE");
                    default:
                        return new QueryResult("Unfamiliar command");
                }
            }
        } catch (SQLException e) {
            return new QueryResult("ERROR: " + e.getMessage());
        }
    }
}

