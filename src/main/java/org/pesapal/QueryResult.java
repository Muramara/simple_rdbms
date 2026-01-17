package org.pesapal;

import java.util.List;

public class QueryResult {
    private String message;
    private List<String> headers;
    private List<String[]> rows;

    public QueryResult(String message) {
        this.message = message;
    }

    public static QueryResult table(List<String> headers, List<String[]> rows) {
        QueryResult qr = new QueryResult(null);
        qr.headers = headers;
        qr.rows = rows;
        return qr;
    }

    public boolean isTable() {
        return rows != null;
    }

    public String getMessage() {
        return message;
    }

    public List<String> getHeaders() {
        return headers;
    }

    public List<String[]> getRows() {
        return rows;
    }
}


