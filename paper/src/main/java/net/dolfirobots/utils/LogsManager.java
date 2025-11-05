/*
 * Utils Package
 * Created by Dolfirobots
 */
package net.dolfirobots.utils;

import net.dolfirobots.Main;
import org.bukkit.ChatColor;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;

import static net.dolfirobots.chat.Messanger.*;

/**
 * Little log manager written by Dolfirobots
 * Just for some features
 */
public class LogsManager {

    private static final String SERVICE = "Logs";

    // Logs folder
    private static final File logsFolder = new File(Main.getInstance().getDataFolder(), "logs");


    public static void saveFile() {
        if (!Main.getInstance().getDataFolder().exists()) {
            if (!Main.getInstance().getDataFolder().mkdirs()) {
                sendError(SERVICE, "Failed to create plugin data folder");
            }
        }
        if (!logsFolder.exists()) {
            if (!logsFolder.mkdir()) {
                sendError(SERVICE, "Failed to create logs folder");
            }
        }
        try {
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            String dateString = formatter.format(new Date());
            File logFile = new File(logsFolder, "log_" + dateString + ".log");
            if (!logFile.exists()) {
                if (!logFile.createNewFile()) sendError(SERVICE, "Failed to create log file");
            }
        } catch (IOException e) {
            sendError(SERVICE, "Error while creating log file: §e" + e.getMessage());
        }
    }

    /**
     * Creates a new log entry
     * @param provider The service or class which is providing the log
     * @param logEntry The log entry
     */
    public static void newLogEntry(String provider, String logEntry, LogType logType) {
        saveFile();

        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat timestampFormatter = new SimpleDateFormat("HH:mm:ss");
        String timestamp = timestampFormatter.format(new Date());

        File logFile = new File(logsFolder, "log_" + formatter.format(new Date()) + ".log");
        new TextFilesAPI(new CustomConfigAPI(logFile)).addLine("[" + timestamp + " " + logType.toString() + "] [" + provider + "] " + logEntry);
    }

    /**
     * Creates a new info log entry
     * @param provider The service or class which is providing the log
     * @param logEntry The log entry
     */
    public static void newLogEntry(String provider, String logEntry) {
        newLogEntry(provider, logEntry, LogType.INFO);
    }

    /**
    * Creates a new error log entry
    * @param service The service or class which is providing the error
    * @param message The error message
    */
    public static void newError(String service, String message) {
        sendError(service, message);
        message = ChatColor.stripColor(message);
        newLogEntry(service, message, LogType.ERROR);
    }

    /**
     * Log levels
     */
    public enum LogType {
        DEBUG("DEBUG"),
        INFO("INFO"),
        WARNING("WARNING"),
        ERROR("ERROR");
        private final String typeName;
        /**
         * Log level
         * @param typeName The name of the log level
         */
        LogType(String typeName) {
            this.typeName = typeName;
        }
        /**
         * Get the log level name
         * @return The log level name
         */
        public String toString() {
            return typeName;
        }
    }
}
