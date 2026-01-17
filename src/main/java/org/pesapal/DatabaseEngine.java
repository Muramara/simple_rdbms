package org.pesapal;

public interface DatabaseEngine {
    QueryResult execute(String sql);
}
