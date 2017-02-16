package ru.nivanov;

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
     * @return value ..
     */
    String getValue() {
        return this.props.getProperty("defaultLogDir");
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