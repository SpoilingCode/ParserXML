package createfile;

import book.Book;
import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.util.ArrayList;


public class CreateXMLFile {

    private Document document;
    private ArrayList<Book> poolBooks;

    public void addBookInPool(Book book) {
        poolBooks.add(book);
    }

    public CreateXMLFile() {
        poolBooks = new ArrayList<Book>();
    }

    public void createXML() {
        try {

            document = DocumentBuilderFactory.newInstance().
                    newDocumentBuilder().
                    newDocument();

            Element catalog = document.createElement("catalog");
            document.appendChild(catalog);

            for (Book itemBook : poolBooks) {

                Element book = document.createElement("book");
                catalog.appendChild(book);

                Attr id = document.createAttribute(itemBook.getAttributeName());
                id.setTextContent(itemBook.getId());
                book.setAttributeNode(id);

                Element author = document.createElement("author");
                author.setTextContent(itemBook.getAuthor());
                book.appendChild(author);

                Element title = document.createElement("title");
                title.setTextContent(itemBook.getTitle());
                book.appendChild(title);

                Element genre = document.createElement("genre");
                genre.setTextContent(itemBook.getGenre());
                book.appendChild(genre);

                Element price = document.createElement("price");
                price.setTextContent(itemBook.getPrice());
                book.appendChild(price);

                Element publishDate = document.createElement("publishDate");
                publishDate.setTextContent(itemBook.getPublishDate());
                book.appendChild(publishDate);

                Element description = document.createElement("description");
                description.setTextContent(itemBook.getDescription());
                book.appendChild(description);

            }
            formXMLDocument();

        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        }
    }

    private void formXMLDocument() {
        Transformer transformer = null;
        try {
        transformer = TransformerFactory.newInstance().newTransformer();
        DOMSource source = new DOMSource(document);
        String fileName = "resources/newXML.xml";

        StreamResult streamResult = new StreamResult(new File("src/" + File.separator + fileName));
        transformer.transform(source, streamResult);

        System.out.println("\nФайл " + fileName + " успешно создан!");
        }catch (TransformerConfigurationException e) {
            e.printStackTrace();
        }
        catch (TransformerException e) {
            e.printStackTrace();
        }
    }
}
