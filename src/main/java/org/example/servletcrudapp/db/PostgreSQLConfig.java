package org.example.servletcrudapp.db;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class PostgreSQLConfig implements DBConfig {
    private final static String PROPS_FILE = "application.properties";
    private final String url;
    private final String username;
    private final String password;
    private final String driver;

    public PostgreSQLConfig() {
        try (FileInputStream source = new FileInputStream(PROPS_FILE)) {
            Properties props = new Properties();
            props.load(source);

            this.url = props.getProperty("PSQL_DB_URL");
            this.username = props.getProperty("PSQL_DB_USERNAME");
            this.password = props.getProperty("PSQL_DB_PASSWORD");
            this.driver = props.getProperty("PSQL_DB_DRIVER_CLASS");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public String getUrl() {
        return url;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getDriver() {
        return driver;
    }
}
