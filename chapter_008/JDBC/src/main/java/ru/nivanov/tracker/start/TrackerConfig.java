package ru.nivanov.tracker.start;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Created by Nikolay Ivanov on 19.09.2017.
 */
class TrackerConfig {

    private final Properties props = new Properties();

    /**
     * Constructor.
     */
    TrackerConfig() {

        InputStream io = getClass().getResourceAsStream("/forTracker/tracker.properties");
        try {
            props.load(io);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Return username.
     * @return ..
     */
    public String getUserName() {
        return this.props.getProperty("username");
    }

    /**
     * Return password.
     * @return ..
     */
    public String getPassword() {
        return this.props.getProperty("password");
    }

    /**
     * Return default base url.
     * @return ..
     */
    public String getDefaultUrl() {
        return this.props.getProperty("defaultUrl");
    }

    /**
     * Return base url.
     * @return ..
     */
    public String getBaseUrl() {
        return this.props.getProperty("baseUrl");
    }

    /**
     * Return base name.
     * @return ..
     */
    public String getBaseName() {
        return this.props.getProperty("database");
    }
}
