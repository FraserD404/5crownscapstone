package com.company;
import java.util.logging.Level;
import java.util.logging.Logger;

public class GameLogger {

    private static final Logger LOGGER = Logger.getLogger("global");
    private boolean logToScreen;

    public GameLogger(boolean in_logToScreen) {
        this.logToScreen = in_logToScreen;
    }

    public void logEntry(String in_logentry) {
        if (this.logToScreen) {
            System.out.println(in_logentry);
        }

        LOGGER.log(Level.INFO, in_logentry);
    }

    public void logEntry(Level in_level, String in_logentry) {
        if (this.logToScreen) {
            System.out.println(in_logentry);
        }

        LOGGER.log(in_level, in_logentry);
    }

    public void setLogToScreen(boolean val){
        logToScreen = val;
    }

}
