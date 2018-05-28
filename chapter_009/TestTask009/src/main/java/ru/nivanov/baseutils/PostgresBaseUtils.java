package ru.nivanov.baseutils;

import org.apache.commons.dbcp2.BasicDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

/**
 * Created by Nikolay Ivanov on 25.10.2017.
 */
public class PostgresBaseUtils {

    private static final Logger LOG = LoggerFactory.getLogger(PostgresBaseUtils.class);
    private static final PostgresBaseUtils BASE = new PostgresBaseUtils();
    private BasicDataSource pool;

    /**
     * Private constructor.
     */
    private PostgresBaseUtils() {
        this.pool = new BasicDataSource();
        InputStream io = getClass().getResourceAsStream("/db.properties");
        Properties props = new Properties();
        try {
            props.load(io);
            LOG.info("props loaded!");
        } catch (IOException e) {
            LOG.error(e.getMessage(), e);
        }
        String url = props.getProperty("baseUrl");
        String username = props.getProperty("username");
        String pass = props.getProperty("password");

        this.pool.setDriverClassName(props.getProperty("driverClass"));
        this.pool.setUrl(url);
        this.pool.setUsername(username);
        this.pool.setPassword(pass);
        this.pool.setInitialSize(5);
    }

    /**
     * Get connection
     * @return connection
     */
    public Connection getConnection() {
        Connection conn = null;
        try {
            conn = getBase().pool.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return conn;

    }

    /**
     * Get base instance.
     * @return base object, 1 for class.
     */
    public static PostgresBaseUtils getBase() {
        return BASE;
    }

    /**
     * Closing pool.
     */
    public void shutDownDataSource() {
        try {
            this.pool.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
