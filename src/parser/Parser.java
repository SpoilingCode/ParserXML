package parser;

import org.xml.sax.SAXException;
import readfile.ReadXMLFile;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;
import java.io.IOException;

public class Parser {

    public static void main(String[] args) throws Exception {
        SAXParserFactory factory = SAXParserFactory.newInstance();
        ReadXMLFile readXMLFile = new ReadXMLFile();

        try {
            SAXParser saxParser = factory.newSAXParser();
            saxParser.parse(new File("src/resources/sampleXml.xml"), readXMLFile);

        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

