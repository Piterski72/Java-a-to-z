package ru.nivanov;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Set;
import java.util.TreeSet;

/**
 * Created by Nikolay Ivanov on 26.05.2017.
 */
class MyStAXparser {
    private static final int THREE = 3;
    private static final int FOUR = 4;
    private HashMap<Integer, Order> unsortedAll = new HashMap<>();
    private Set<String> bookNames = new TreeSet<>();


    /**
     * Parse method.
     * @throws FileNotFoundException ..
     * @throws XMLStreamException ..
     */
    void parse() throws FileNotFoundException, XMLStreamException {
        File orders = new File("c:/java/orders.xml");
        XMLInputFactory factory = XMLInputFactory.newFactory();

        XMLStreamReader readerNew = factory.createXMLStreamReader(new FileInputStream(orders));

        while (readerNew.hasNext()) {
            readerNew.next();
            if (readerNew.getEventType() == XMLStreamConstants.START_ELEMENT) {
                if (readerNew.getName().getLocalPart().equals("AddOrder")) {
                    String book = readerNew.getAttributeValue(0);
                    String oper = readerNew.getAttributeValue(1);
                    float price = Float.parseFloat(readerNew.getAttributeValue(2));
                    int volume = Integer.parseInt(readerNew.getAttributeValue(THREE));
                    int id = Integer.parseInt(readerNew.getAttributeValue(FOUR));
                    Order order = new Order(book, oper, price, volume);
                    unsortedAll.put(id, order);
                    if (!this.bookNames.contains(book)) {
                        this.bookNames.add(book);
                    }

                } else if (readerNew.getName().getLocalPart().equals("DeleteOrder")) {
                    int idDel = Integer.parseInt(readerNew.getAttributeValue(1));
                    unsortedAll.remove(idDel);
                }
            }
        }
    }

    /**
     * Getter for all unsorted orders.
     * @return ..
     */
    HashMap<Integer, Order> getUnsortedAll() {
        return unsortedAll;
    }

    /**
     * Getter for all book names.
     * @return ..
     */
    Set<String> getBookNames() {
        return bookNames;
    }
}
