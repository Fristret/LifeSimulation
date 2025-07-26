package org.simulation.logs;


import java.util.ArrayList;
import java.util.List;

public class ConsoleLogger implements Logger{

    List<String> logs;

    public ConsoleLogger() {
        logs = new ArrayList<>();
    }

    @Override
    public void log(String message) {
        logs.add(message);
    }

    @Override
    public String getFormattedLogs() {
        return String.join("\n", logs);
    }

    @Override
    public void clear() {
        logs.clear();
    }
}
