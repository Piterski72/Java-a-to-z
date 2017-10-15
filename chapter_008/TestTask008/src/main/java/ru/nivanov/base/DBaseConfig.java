package ru.nivanov.base;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Created by Nikolay Ivanov on 10.10.2017.
 */
class DBaseConfig {

    private static final Logger LOG = LoggerFactory.getLogger(DBaseConfig.class);
    private final Properties props = new Properties();
    private String url;
    private String baseUrl;
    private String username;
    private String password;
    private String basename;

    /**
     * Load base properties.
     */
    void loadProps() {
        InputStream io = getClass().getResourceAsStream("/db.properties");
        try {
            this.props.load(io);
            this.url = this.props.getProperty("defaultUrl");
            this.username = this.props.getProperty("username");
            this.password = this.props.getProperty("password");
            this.basename = this.props.getProperty("database");
            this.baseUrl = this.props.getProperty("baseUrl");

        } catch (IOException e) {
            LOG.error(e.getMessage(), e);
        }
    }

    /**
     * Getter for default base (postgres) url.
     * @return ..
     */
    String getUrl() {
        return url;
    }

    /**
     * Getter for vacancy base url.
     * @return ..
     */
    String getBaseUrl() {
        return baseUrl;
    }

    /**
     * User name getter.
     * @return ..
     */
    String getUsername() {
        return username;
    }

    /**
     * User pass getter.
     * @return ..
     */
    String getPassword() {
        return password;
    }

    /**
     * Base name getter.
     * @return ..
     */
    String getBasename() {
        return basename;
    }
}
