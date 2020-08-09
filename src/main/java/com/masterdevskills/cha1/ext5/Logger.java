package com.masterdevskills.cha1.ext5;

import java.time.LocalDateTime;
import java.util.function.Supplier;

//TODO: implement info, trace, debug, warn
public class Logger implements Log {
    private static final String DELIM = "{}";
    private volatile boolean enabled;

    private Logger() {
    }

    public static Log getLogger() {
        return new Logger();
    }

    @Override
    public boolean isLoggable() {
        return enabled;
    }

    @Override
    public void enableLogging() {
        this.enabled = true;
    }

    @Override
    public void info(final String message, final Object... params) {
        if (isLoggable()) {
            System.out.println(formatMessage(LogType.INFO, message, params));
        }
    }

    private String formatMessage(final LogType type, final String message, final Object[] params) {
        if (message != null && params != null) {
            StringBuilder sbMessage = new StringBuilder(LocalDateTime.now().toString());
            sbMessage.append(": ").append(type.value).append(": ").append(message);

            for (Object arg : params) {
                int index = sbMessage.indexOf(DELIM);
                if (index == -1) {
                    break;
                }
                sbMessage.replace(index, index + DELIM.length(), arg == null ? "null" : arg.toString());
            }

            return sbMessage.toString();
        }
        return message;
    }

    @Override
    public void info(final String message, final Supplier<Object[]> params) {
        if (isLoggable()) {
            System.out.println(formatMessage(LogType.INFO, message, params.get()));
        }
    }

    @Override
    public void trace(String message, Supplier<Object[]> params) {
        if (isLoggable()) {
            System.out.println(formatMessage(LogType.TRACE, message, params.get()));
        }
    }

    @Override
    public void debug(String message, Supplier<Object[]> params) {
        if (isLoggable()) {
            System.out.println(formatMessage(LogType.DEBUG, message, params.get()));
        }
    }

    @Override
    public void warn(String message, Supplier<Object[]> params) {
        if (isLoggable()) {
            System.out.println(formatMessage(LogType.WARN, message, params.get()));
        }
    }

    @Override
    public void error(String message, Supplier<Object[]> params) {
        if (isLoggable()) {
            System.out.println(formatMessage(LogType.ERROR, message, params.get()));
        }
    }

    public Logger setEnabled(final boolean enabled) {
        this.enabled = enabled;
        return this;
    }

    enum LogType {
        INFO("INFO"),
        DEBUG("DEBUG"),
        TRACE("TRACE"),
        WARN("WARN"),
        ERROR("ERROR");

        final String value;

        LogType(String value) {
            this.value = value;
        }
    }
}


