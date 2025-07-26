package org.simulation.logs;

public interface Logger {
    void log(String message);
    String getFormattedLogs();
    void clear();
}
