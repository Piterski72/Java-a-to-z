package ru.nivanov.xmlOptimizer;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

/**
 * Created by Nikolay Ivanov on 26.09.2017.
 */
class BaseHandler {

    private static final Properties PROPERTIES = new Properties();
    private final int numOfRec;
    private String basePath;
    private String filePathOne;
    private String filePathTwo;

    /**
     * Constructor.
     * @param numOfRec is number of records to insert into table.
     */
    BaseHandler(int numOfRec) {
        this.numOfRec = numOfRec;
    }

    /**
     * Creating table with given name and column.
     * @param conn ..
     */
    void createTable(Connection conn) {

        Statement st = null;
        try {
            st = conn.createStatement();
            st.execute("CREATE TABLE IF NOT EXISTS TEST (FIELD INTEGER NOT NULL )");
            st.execute("DELETE FROM TEST WHERE FIELD IS NOT NULL");

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {

            try {
                assert st != null;
                st.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Inserting N records into table.
     * @param conn ..
     */
    void insertRecords(Connection conn) {

        PreparedStatement prst = null;

        try {
            conn.setAutoCommit(false);
            prst = conn.prepareStatement("INSERT INTO TEST (FIELD) VALUES (?)");

            for (int i = 1, n = getNumOfRec() + 1; i < n; i++) {
                prst.setInt(1, i);
                prst.addBatch();
            }

            prst.executeBatch();
            conn.commit();
            conn.setAutoCommit(true);

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                assert prst != null;
                prst.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Number of records getter.
     * @return ..
     */
    private int getNumOfRec() {
        return numOfRec;
    }

    /**
     * Base path getter.
     * @return base path.
     */
    String getBasePath() {
        return basePath;
    }

    /**
     * File 1 path getter.
     * @return ..
     */
    String getFilePathOne() {
        return filePathOne;
    }

    /**
     * File 2 path getter.
     * @return ..
     */
    String getFilePathTwo() {
        return filePathTwo;
    }

    /**
     * Setting properties.
     */
    void setUp() {

        try (InputStream io = getClass().getResourceAsStream("/forXml/app.properties")) {
            try {
                PROPERTIES.load(io);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.basePath = PROPERTIES.getProperty("sqlite_loc");
        this.filePathOne = PROPERTIES.getProperty("fileOne");
        this.filePathTwo = PROPERTIES.getProperty("fileTwo");
    }

}
