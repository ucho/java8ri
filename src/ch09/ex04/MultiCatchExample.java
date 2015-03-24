package ch09.ex04;

import java.io.IOException;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.junit.Test;
import org.xml.sax.SAXException;

public class MultiCatchExample {

    @Test(expected = RuntimeException.class)
    public void example() {
        try {
            DocumentBuilderFactory.newInstance().newDocumentBuilder().parse("dummy");
        } catch (SAXException | IOException | ParserConfigurationException e) {
            throw new RuntimeException();
        }
    }
}
