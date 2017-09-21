package ru.nivanov.xmlOptimizer;

import javax.xml.stream.XMLStreamException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.Properties;

/**
 * Created by Nikolay Ivanov on 29.08.2017.
 */
public class JDBCtest {

    private static final Properties PROPERTIES = new Properties();
    private String conStr;
    private int numOfRec;

    /**
     * Main method.
     * @param args ..
     */
    public static void main(String[] args) {

        long start = System.currentTimeMillis();

        Connection conn = null;

        JDBCtest worker = new JDBCtest();

        worker.setUp();

        String input = worker.getXslFile().getPath();
        String filePathOne = PROPERTIES.getProperty("fileOne");
        String filePathTwo = PROPERTIES.getProperty("fileTwo");

        XmlCreator creator = new XmlCreator(filePathOne);
        XmlExecutor xmlExecutor = new XmlExecutor(input, filePathTwo);
        XmlParser xmlParser = new XmlParser();

        final int numOfRecords = 99;

        String basePath = PROPERTIES.getProperty("sqlite_loc");

        worker.init(basePath, numOfRecords);
        try {
            conn = DriverManager.getConnection(worker.getConStr());
            worker.createTable(conn);
            worker.insertRecords(conn);
            String fileOne = creator.generateXML(conn);
            String fileTwo = xmlExecutor.transformXML(fileOne);
            long result = xmlParser.parseXML(fileTwo);
            System.out.println(result);

        } catch (SQLException | FileNotFoundException | XMLStreamException e) {
            e.printStackTrace();
        } finally {
            try {
                assert conn != null;
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        System.out.println(System.currentTimeMillis() - start);
    }

    /**
     * Inserting N records into table.
     * @param conn ..
     */
    private void insertRecords(Connection conn) {

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
     * Creating table with given name and column.
     * @param conn ..
     */
    private void createTable(Connection conn) {

        Statement st = null;
        try {
            st = conn.createStatement();
            st.execute("CREATE TABLE IF NOT EXISTS TEST (FIELD INTEGER NOT NULL )");
            st.execute("DELETE FROM TEST WHERE FIELD NOT NULL");

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
     * Getter for connection details.
     * @return ..
     */
    private String getConStr() {
        return conStr;
    }

    /**
     * Connection param setter.
     * @param conStr ..
     */
    private void setConStr(String conStr) {
        this.conStr = conStr;
    }

    /**
     * Number of records getter.
     * @return ..
     */
    private int getNumOfRec() {
        return numOfRec;
    }

    /**
     * Number of records setter.
     * @param numOfRec ..
     */
    private void setNumOfRec(int numOfRec) {
        this.numOfRec = numOfRec;
    }

    /**
     * Setup connection.
     * @param line is connection params.
     * @param number is number of records.
     */
    private void init(String line, int number) {

        this.setConStr(line);
        this.setNumOfRec(number);
    }

    /**
     * Setting properties.
     */
    public void setUp() {

        try (InputStream io = getClass().getResourceAsStream("/forXml/app.properties")) {
            try {
                PROPERTIES.load(io);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Getter for xsl file from resources.
     * @return file.
     */
    private File getXslFile() {

        File result;

        ClassLoader classLoader = getClass().getClassLoader();
        result = new File(classLoader.getResource("forXml/1.xsl").getFile());

        return result;
    }

}
