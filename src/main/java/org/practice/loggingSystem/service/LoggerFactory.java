package org.practice.loggingSystem.service;

// ===================== Factory Pattern =====================
public class LoggerFactory {

    public static Logger createLoggerChain() {
        Logger debug = new DebugLogger();
        Logger info = new InfoLogger();
        Logger error = new ErrorLogger();

        debug.setNext(info);
        info.setNext(error);

        ConsoleAppender console = new ConsoleAppender();

        debug.addAppender(console);
        info.addAppender(console);
        error.addAppender(console);

        return debug;
    }
}
