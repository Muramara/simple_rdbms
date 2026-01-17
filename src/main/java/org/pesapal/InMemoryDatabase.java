package org.pesapal;

public class InMemoryDatabase implements DatabaseEngine {

    @Override
    public QueryResult execute(String sql) {
        sql = sql.trim().toUpperCase();

        if (sql.startsWith("CREATE TABLE")) {
            return new QueryResult("Table created");
        }

        if (sql.startsWith("INSERT")) {
            return new QueryResult("1 row inserted");
        }

        if (sql.startsWith("SELECT")) {
            return new QueryResult("SELECT executed");
        }

        throw new RuntimeException("Unsupported SQL");
    }
}

