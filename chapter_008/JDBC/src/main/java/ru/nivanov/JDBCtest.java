package ru.nivanov;

import javax.xml.stream.XMLStreamException;
import java.io.FileNotFoundException;
import java.sql.*;

/**
 * Created by Nikolay Ivanov on 29.08.2017.
 */
public class JDBCtest {

    private String conStr;
    private int numOfRec;

    /**
     * Main method.
     * @param args ..
     */
    public static void main(String[] args) {

        Connection conn = null;
        JDBCtest worker = new JDBCtest();
        XmlCreator creator = new XmlCreator("C:\\sql\\1.xml");
        XmlExecutor xmlExecutor = new XmlExecutor("C:\\sql\\1.xsl", "C:\\sql\\2.xml");
        XmlParser xmlParser = new XmlParser();

        final int numOfRecords = 999000;

        worker.init("jdbc:sqlite:C:/sql/sqlite/base/mybase.s3db", numOfRecords);
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
    }

    /**
     * Inserting N records into table.
     * @param conn ..
     */
    private void insertRecords(Connection conn) {
        long start = System.currentTimeMillis();
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
        System.out.println(System.currentTimeMillis() - start);
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
}
