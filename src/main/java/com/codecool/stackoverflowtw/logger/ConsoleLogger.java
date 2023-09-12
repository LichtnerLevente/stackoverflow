package com.codecool.stackoverflowtw.logger;

import java.time.LocalDate;

public class ConsoleLogger implements Logger {
    @Override
    public void logInfo(String message) {
        System.out.println("INFO " + message + " " + LocalDate.now());
    }

    @Override
    public void logError(String message) {
        System.out.println("ERROR " + message + " " + LocalDate.now());
    }
}
