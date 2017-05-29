package ru.nivanov;

import javax.xml.namespace.QName;
import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.Attribute;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

/**
 * Created by Nikolay Ivanov on 26.05.2017.
 */
public class MyStAXparser {
    private static final int THOUSAND = 1000;

    /**
     * Main method.
     * @param args ..
     * @throws FileNotFoundException ..
     * @throws XMLStreamException ..
     */
    public static void main(String[] args) throws FileNotFoundException, XMLStreamException {
        long start = System.currentTimeMillis();
        OrderBook unsort = new OrderBook("root");

        File orders = new File("c:/java/orders.xml");
        XMLInputFactory factory = XMLInputFactory.newFactory();
        XMLEventReader reader = factory.createXMLEventReader(new FileInputStream(orders));

        while (reader.hasNext()) {
            XMLEvent event = reader.nextEvent();
            if (event.isStartElement()) {
                StartElement current = event.asStartElement();
                if (current.getName().getLocalPart().equals("AddOrder")) {
                    Attribute book = current.getAttributeByName(new QName("book"));
                    Attribute oper = current.getAttributeByName(new QName("operation"));
                    Attribute price = current.getAttributeByName(new QName("price"));
                    Attribute vol = current.getAttributeByName(new QName("volume"));
                    Attribute id = current.getAttributeByName(new QName("orderId"));
                    Order order = new Order(book.getValue(), oper.getValue(), Float.parseFloat(price.getValue()),
                            Integer.parseInt(vol.getValue()));
                    unsort.getUnsorted().put(Integer.parseInt(id.getValue()), order);
                    if (!unsort.getBookNames().contains(book.getValue())) {
                        unsort.getBookNames().add(book.getValue());
                    }

                } else if (current.getName().getLocalPart().equals("DeleteOrder")) {

                    Attribute idDel = current.getAttributeByName(new QName("orderId"));

                    unsort.getUnsorted().remove(Integer.parseInt(idDel.getValue()));

                }

            }

        }

        unsort.fillBooks();
        unsort.bookExecute();
        unsort.printBooks();
        long end = System.currentTimeMillis();
        System.out.println((end - start) / THOUSAND);


    }
}
