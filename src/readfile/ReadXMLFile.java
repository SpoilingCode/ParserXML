package readfile;

import book.Book;
import createfile.CreateXMLFile;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class ReadXMLFile extends DefaultHandler {

    private String id;
    private boolean author;
    private boolean title;
    private boolean genre;
    private boolean price;
    private boolean publishDate;
    private boolean description;
    private Book book;
    private CreateXMLFile createXMLFile;

    public ReadXMLFile() {
        createXMLFile = new CreateXMLFile();

    }

    @Override
    public void startDocument() throws SAXException {
        System.out.println("\nПарсинг запущен");
    }

    @Override
    public void startElement(String namespace, String localName, String qName, Attributes attributes) throws SAXException {

        if (qName.equals("book")) {
            book = new Book();
            id = attributes.getValue(0);
            book.setId(id);
            book.setAttributeName("id");
            System.out.println("ID: " + id );
        }

        if (qName.equals("author")) {
            author = true;
        }
        if (qName.equals("title")) {
            title = true;
        }
        if (qName.equals("genre")) {
            genre = true;
        }
        if (qName.equals("price")) {
            price = true;
        }
        if (qName.equals("publish_date")) {
            publishDate = true;
        }
        if (qName.equals("description")) {
            description = true;
        }
    }

    @Override
    public void characters(char[] ch, int start, int end) throws SAXException {

        if (author) {
            book.setAuthor(new String(ch, start, end));
            book.setElementName("author");
            System.out.println("Author : " + new String(ch, start, end));
            author = false;
        }
        if (title) {
            book.setTitle(new String(ch, start, end));
            book.setElementName("title");
            System.out.println("Title : " + new String(ch, start, end));
            title = false;
        }
        if (genre) {
            book.setGenre(new String(ch, start, end));
            book.setElementName("genre");
            System.out.println("Genre : " + new String(ch, start, end));
            genre = false;
        }
        if (price) {
            book.setPrice(new String(ch, start, end));
            book.setElementName("price");
            System.out.println("Price : " + new String(ch, start, end));
            price = false;
        }
        if (publishDate) {
            book.setPublishDate(new String(ch, start, end));
            book.setElementName("publishDate");
            System.out.println("Publish date : " + new String(ch, start, end));
            publishDate = false;
        }
        if (description) {
            book.setDescription(new String(ch, start, end));
            book.setElementName("description");
            createXMLFile.addBookInPool(book);
            System.out.println("Description : " + new String(ch, start, end));
            System.out.println();
            description = false;
        }
    }

    @Override
    public void endElement(String namespace, String localName, String qName) throws SAXException {

    }

    @Override
    public void endDocument() throws SAXException {
        createXMLFile.createXML();
        System.out.println("\nПарсинг завершен.");
    }
}
