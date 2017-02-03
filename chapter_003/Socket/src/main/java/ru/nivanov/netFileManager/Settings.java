package ru.nivanov.netFileManager;

import java.io.InputStream;
import java.util.Properties;

/**
 * Set up programm params.
 * @author nivanov.
 */
class Settings {
    private final Properties props = new Properties();

    /**
     * Getting property value by given key.
     * @param key ..
     * @return value ..
     */
    String getValue(String key) {
        return this.props.getProperty(key);
    }

    /**
     * Loading property file.
     * @param io ..
     */
    void load(InputStream io) {
        try {
            this.props.load(io);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}