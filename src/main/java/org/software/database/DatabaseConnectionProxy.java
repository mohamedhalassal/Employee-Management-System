package org.software.database;

public class DatabaseConnectionProxy {
    private DatabaseConnectionManager dbManager;

    public void connect() {
        if (dbManager == null) {
            dbManager = DatabaseConnectionManager.getInstance();
            System.out.println("Initializing Database Connection through Proxy...");
        }
        dbManager.connect();
    }

    public void disconnect() {
        if (dbManager != null) {
            dbManager.disconnect();
            System.out.println("Terminating Database Connection through Proxy...");
        }
    }
}

