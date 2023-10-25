package persistence;

import model.Property;
import model.PropertyList;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

import org.json.*;

//Code influenced by the JsonSerizalizationDemo https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo
// Represents a reader that reads property list from JSON data stored in file
public class JsonReader {
    private String source;

    // EFFECTS: constructs reader to read from source file
    public JsonReader(String source) {
        this.source = source;
    }

    // EFFECTS: reads property list from file and returns it;
    // throws IOException if an error occurs reading data from file
    public PropertyList read() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parsePropertyList(jsonObject);
    }

    // EFFECTS: reads source file as string and returns it
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }

        return contentBuilder.toString();
    }

    // EFFECTS: parses property list from JSON object and returns it
    private PropertyList parsePropertyList(JSONObject jsonObject) {
        String name = jsonObject.getString("name");
        PropertyList pl = new PropertyList(name);
        addProperties(pl, jsonObject);
        return pl;
    }

    // MODIFIES: pl
    // EFFECTS: parses properties from JSON object and adds them to property list
    private void addProperties(PropertyList pl, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("properties");
        for (Object json : jsonArray) {
            JSONObject nextProperty = (JSONObject) json;
            addProperty(pl, nextProperty);
        }
    }

    // MODIFIES: pl
    // EFFECTS: parses property from JSON object and adds it to property list
    private void addProperty(PropertyList pl, JSONObject jsonObject) {
        String address = jsonObject.getString("address");
        String city = jsonObject.getString("city");
        int price = jsonObject.getInt("price");
        String ownerName = jsonObject.getString("ownerName");
        Property property = new Property(address, city, price, ownerName);
        pl.addProperty(property);
    }
}
