package ru.nivanov;

import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

/**
 * Created by Nikolay Ivanov on 12.09.2017.
 */
class XmlExecutor {

    private final String inXsl;
    private final String outXml;

    /**
     * Constructor.
     * @param inXsl ..
     * @param outXml ..
     */
    XmlExecutor(String inXsl, String outXml) {
        /*
         * String path to xsl file with transformation instructions.
         */
        this.inXsl = inXsl;
        /*
         * String path for transformed xml.
         */
        this.outXml = outXml;
    }

    /**
     * Transforming xml to other xml.
     * @param fileLocation ..
     * @return new path.
     */
    String transformXML(String fileLocation) {

        StreamSource xslStream = new StreamSource(this.inXsl);
        StreamSource in = new StreamSource(fileLocation);
        StreamResult out = new StreamResult(this.outXml);

        try {
            Transformer transformer = TransformerFactory.newInstance().newTransformer(xslStream);
            transformer.transform(in, out);
        } catch (TransformerException e) {
            e.printStackTrace();
        }
        return this.outXml;
    }

}
