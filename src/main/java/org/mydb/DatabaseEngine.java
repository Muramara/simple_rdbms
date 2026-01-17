package org.mydb;

public interface DatabaseEngine {
    QueryResult execute(String sql);
}
