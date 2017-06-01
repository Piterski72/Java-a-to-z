package ru.nivanov;

import javax.xml.stream.XMLStreamException;
import java.io.FileNotFoundException;

/**
 * Created by Nikolay Ivanov on 01.06.2017.
 */
public class StartParse {
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
        MyStAXparser parser = new MyStAXparser();

        try {
            parser.parse();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (XMLStreamException e) {
            e.printStackTrace();
        }
        unsort.fillBooksNew(parser.getUnsortedAll(), parser.getBookNames());
        unsort.bookExecuteNew();
        unsort.printBooksNew();


        long end = System.currentTimeMillis();
        System.out.println((end - start) / THOUSAND);

    }
}
