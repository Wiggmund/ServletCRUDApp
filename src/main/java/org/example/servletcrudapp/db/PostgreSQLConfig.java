package org.example.servletcrudapp.db;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PostgreSQLConfig implements DBConfig {
    private final String url;
    private final String username;
    private final String password;
    private final String driver;

    public PostgreSQLConfig() {
        try(InputStream source = getClass().getClassLoader().getResourceAsStream("application.properties")) {
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

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getDriver() {
        return driver;
    }
}
