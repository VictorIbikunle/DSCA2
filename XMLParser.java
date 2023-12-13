package com.example.demo;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

public class XMLParser {

    public static void main(String[] args) {
        try {
            // Load and parse the XML file
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.parse("emission.xml");

            // Get the root element
            Element root = document.getDocumentElement();

            // Get all "Row" elements
            NodeList rowList = root.getElementsByTagName("Row");

            // Criteria variables
            int validEntries = 0;

            // Iterate through each "Row" element
            for (int i = 0; i < rowList.getLength(); i++) {
                Element row = (Element) rowList.item(i);

                // Get relevant child elements
                String value = row.getElementsByTagName("Value").item(0).getTextContent();
                String scenario = row.getElementsByTagName("Scenario").item(0).getTextContent();
                String year = row.getElementsByTagName("Year").item(0).getTextContent();

                // Check criteria
                if (isValidEntry(value, scenario, year)) {
                    validEntries++;
                    // Process the valid entry as needed
                }
            }

            // Check if the total number of entries is 245
            if (validEntries == 245) {
                System.out.println("All criteria met. Total valid entries: " + validEntries);
            } else {
                System.out.println("Criteria not met. Total valid entries: " + validEntries);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Check criteria function
    private static boolean isValidEntry(String value, String scenario, String year) {
        double numericValue = Double.parseDouble(value);

        return numericValue > 0 && scenario.equals("WEM") && year.equals("2023");
    }
}