package com.example.demo;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;
import java.io.File;
import java.io.IOException;
import javax.xml.parsers.ParserConfigurationException;
import org.xml.sax.SAXException;

public class XmlParser {

    public static void main(String argv[]) throws IOException, ParserConfigurationException, SAXException {

        File xmlFile = new File("EmissionsData.xml");

        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document doc = builder.parse(xmlFile);

        System.out.println("Root element: " + doc.getDocumentElement().getNodeName());

        NodeList rows = doc.getElementsByTagName("Row");

        int totalEntries = 0;

        for (int i = 0; i < rows.getLength(); i++) {

            Node row = rows.item(i);

            if (row.getNodeType() == Node.ELEMENT_NODE) {

                Element rowElement = (Element) row;

                // Check conditions
                String year = getElementValue(rowElement, "Year");
                String scenario = getElementValue(rowElement, "Scenario");
                String value = getElementValue(rowElement, "Value");

                // Additional conditions
                if ("2023".equals(year) && "WEM".equals(scenario) && isValueValid(value)) {
                    totalEntries++;

                    // Change variable names
                    String category = getElementValue(rowElement, "Category__1_3");
                    String nk = getElementValue(rowElement, "NK");
                    String gasUnits = getElementValue(rowElement, "Gas___Units");

                    System.out.println("Year: " + year);
                    System.out.println("Scenario: " + scenario);
                    System.out.println("Category: " + category);
                    System.out.println("NK: " + nk);
                    System.out.println("Gas Units: " + gasUnits);
                    System.out.println("Value: " + value);
                    System.out.println("--------------------");
                }
            }
        }

        // Check the total number of valid entries
        System.out.println("Total valid entries: " + totalEntries);
    }

    private static String getElementValue(Element element, String tagName) {
        NodeList nodeList = element.getElementsByTagName(tagName);
        if (nodeList != null && nodeList.getLength() > 0) {
            return nodeList.item(0).getTextContent();
        } else {
            return ""; // or handle it as needed
        }
    }

    private static boolean isValueValid(String value) {
        try {
            double numericValue = Double.parseDouble(value);
            return numericValue > 0;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}

