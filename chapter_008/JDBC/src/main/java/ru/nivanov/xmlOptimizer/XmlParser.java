package ru.nivanov.xmlOptimizer;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by Nikolay Ivanov on 12.09.2017.
 */
class XmlParser {
    /**
     * Parsing xml.
     * @param file is input xml
     * @return summ of all attribs.
     * @throws FileNotFoundException ..
     * @throws XMLStreamException ..
     */
    long parseXML(String file) throws FileNotFoundException, XMLStreamException {

        Collection<Integer> coll = new ArrayList<>();

        File inputXML = new File(file);
        XMLInputFactory factory = XMLInputFactory.newFactory();

        XMLStreamReader readerNew = factory.createXMLStreamReader(new FileInputStream(inputXML));

        while (readerNew.hasNext()) {
            readerNew.next();
            if (readerNew.getEventType() == XMLStreamConstants.START_ELEMENT) {
                if (readerNew.getName().getLocalPart().equals("entry")) {
                    int value = Integer.parseInt(readerNew.getAttributeValue(0));
                    coll.add(value);
                }
            }
        }
        return coll.stream().mapToLong(Long::valueOf).sum();
    }
}
