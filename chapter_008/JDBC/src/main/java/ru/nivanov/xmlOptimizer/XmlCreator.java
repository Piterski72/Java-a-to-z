package ru.nivanov.xmlOptimizer;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Created by Nikolay Ivanov on 12.09.2017.
 */
class XmlCreator {
    private final String path;

    /**
     * Constructor.
     * @param path for creating xml.
     */
    XmlCreator(String path) {
        this.path = path;
    }

    /**
     * Generating xml file.
     * @param conn ..
     * @return ..
     */
    String generateXML(Connection conn) {

        Document doc = null;
        Statement st;
        ResultSet rs = null;

        try {
            st = conn.createStatement();
            rs = st.executeQuery("SELECT * FROM TEST WHERE FIELD NOT NULL");
        } catch (SQLException e) {
            e.printStackTrace();
        }

        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        factory.setNamespaceAware(true);
        try {
            doc = factory.newDocumentBuilder().newDocument();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        }

        Element rootElement = doc != null ? doc.createElement("entries") : null;
        assert doc != null;
        doc.appendChild(rootElement);

        try {
            while (rs != null && rs.next()) {
                Element entry = doc.createElement("entry");
                assert rootElement != null;
                rootElement.appendChild(entry);

                Element field = doc.createElement("field");
                field.appendChild(doc.createTextNode(rs.getString(1)));
                entry.appendChild(field);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = null;
        try {
            transformer = transformerFactory.newTransformer();
        } catch (TransformerConfigurationException e) {
            e.printStackTrace();
        }
        DOMSource source = new DOMSource(doc);
        StreamResult result = new StreamResult(new File(this.path));

        try {
            assert transformer != null;
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.transform(source, result);
        } catch (TransformerException e) {
            e.printStackTrace();
        }
        return this.path;
    }
}
