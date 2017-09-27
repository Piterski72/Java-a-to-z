package ru.nivanov.xmlOptimizer;

import javax.xml.stream.XMLStreamException;
import java.io.File;
import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Created by Nikolay Ivanov on 29.08.2017.
 */
public class JDBCstart {

    /**
     * Main method.
     * @param args ..
     */
    public static void main(String[] args) {

        long start = System.currentTimeMillis();

        final int numOfRecords = 99;

        BaseHandler baseHandler = new BaseHandler(numOfRecords);

        baseHandler.setUp();

        String input = new JDBCstart().getXslFile().getPath();

        XmlCreator creator = new XmlCreator(baseHandler.getFilePathOne());
        XmlExecutor xmlExecutor = new XmlExecutor(input, baseHandler.getFilePathTwo());
        XmlParser xmlParser = new XmlParser();

        Connection conn = null;

        try {
            conn = DriverManager.getConnection(baseHandler.getBasePath());
            baseHandler.createTable(conn);
            baseHandler.insertRecords(conn);
            String fileOne = creator.generateXML(conn);
            String fileTwo = xmlExecutor.transformXML(fileOne);
            xmlParser.parseXML(fileTwo);

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
