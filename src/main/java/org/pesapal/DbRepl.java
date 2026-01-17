package org.pesapal;

import java.util.Scanner;
import java.util.List;
import java.util.stream.Collectors;

public class DbRepl {

    private final DatabaseEngine engine;

    public DbRepl(DatabaseEngine engine) {
        this.engine = engine;
    }

    public void start() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("mydb REPL — type EXIT to quit");

        try {
            while (true) {
                System.out.print("mydb=# ");
                String input = scanner.nextLine().trim();

                if (input.equalsIgnoreCase("exit")) break;
                if (input.isEmpty()) continue;

                try {
                    QueryResult result = engine.execute(input);

                    if (result.isTable()) {
                        printTable(result);
                    } else {
                        System.out.println(result.getMessage());
                    }

                } catch (Exception e) {
                    System.out.println("ERROR: " + e.getMessage());
                }
            }
        } finally {
            scanner.close();
        }
    }

    private void printTable(QueryResult result) {
        List<String> headers = result.getHeaders();
        List<String[]> rows = result.getRows();

        // Print header
        System.out.println(String.join(" | ", headers));
        System.out.println(headers.stream()
                .map(h -> "-".repeat(h.length()))
                .collect(Collectors.joining("-+-")));

        // Print rows
        for (String[] row : rows) {
            System.out.println(String.join(" | ", row));
        }

        System.out.println("(" + rows.size() + " rows)");
    }
}


