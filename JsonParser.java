package com.example.demo;

import com.example.demo.repository.JsonRepository;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;

@Component
public class JsonParser implements CommandLineRunner {

    private final JsonRepository jsonRepository;

    @Autowired
    public JsonParser(JsonRepository jsonRepository) {
        this.jsonRepository = jsonRepository;
    }

    @Override
    public void run(String... args) {
        parseAndSave();
    }

    public void parseAndSave() {
        try {
            // Create ObjectMapper instance
            ObjectMapper objectMapper = new ObjectMapper();

            // Replace "your_json_file.json" with the actual file path
            File jsonFile = new File("GreenhouseGas.json");

            // Read JSON data and convert it to a JsonNode object
            JsonNode rootNode = objectMapper.readTree(jsonFile);
            JsonNode emissionsNode = rootNode.get("Emissions");

            // Check if "Emissions" node exists and is an array
            if (emissionsNode != null && emissionsNode.isArray()) {
                // Iterate through the "Emissions" array
                for (JsonNode emissionNode : emissionsNode) {
                    // Get values from each emission node
                    String category = emissionNode.get("Category").asText();
                    String gasUnits = emissionNode.get("Gas Units").asText();
                    double value = emissionNode.get("Value").asDouble();

                    // Save to the database
                    EmissionJson jsonEntity = new EmissionJson();
                    jsonEntity.setCategory(category);
                    jsonEntity.setGasUnits(gasUnits);
                    jsonEntity.setValue(value);

                    try {
                        // Assuming you have a save method in your JsonRepository
                        jsonRepository.save(jsonEntity);

                        // Print or process other properties...
                        System.out.println("Category: " + category);
                        System.out.println("Gas Units: " + gasUnits);
                        System.out.println("Value: " + value);
                        System.out.println("Saved to the database!");
                        System.out.println("--------------------");
                    } catch (Exception e) {
                        System.err.println("Error saving to the database:");
                        e.printStackTrace();
                    }
                }
            } else {
                System.out.println("Invalid or missing 'Emissions' array in JSON.");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

