package com.robin.spi.plugin;

public class SpiLoaderExtension {

    private boolean abortOnError = true;

    private boolean enableLog = true;

    private boolean enableDebug = false;

    public void setAbortOnError(boolean abortOnError) {
        this.abortOnError = abortOnError;
    }

    public boolean getAbortOnError() {
        return abortOnError;
    }

    public void setEnableLog(boolean enableLog) {
        this.enableLog = enableLog;
    }

    public boolean getEnableLog() {
        return enableLog;
    }

    public void setEnableDebug(boolean enableDebug) {
        this.enableDebug = enableDebug;
    }

    public boolean getEnableDebug() {
        return enableDebug;
    }
}
