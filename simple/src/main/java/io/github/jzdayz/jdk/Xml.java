package io.github.jzdayz.jdk;

import org.springframework.core.io.ClassPathResource;
import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.Objects;

public class Xml {

    public static void main(String[] args) throws Exception {
        System.err.println(xmlSql("aa"));;
    }

    public static String xmlSql(String id){
        try {
            return xmlSqlEx(id);
        } catch (ParserConfigurationException | IOException | SAXException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String xmlSqlEx(String id) throws ParserConfigurationException, IOException, SAXException {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        factory.setValidating(false);
        factory.setIgnoringElementContentWhitespace(true);
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document doc = builder.parse(new ClassPathResource("test.xml").getInputStream());
        NodeList sql = doc.getElementsByTagName("sql");
        for (int i = 0; i < sql.getLength(); i++) {
            Node item = sql.item(i);
            NamedNodeMap attributes = item.getAttributes();
            String n = attributes.getNamedItem("id").getNodeValue();
            if (Objects.equals(n, id)) {
                return item.getTextContent();
            }
        }
        throw new RuntimeException("not found");
    }
}
