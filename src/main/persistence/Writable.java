package persistence;

import org.json.JSONObject;

//Code influenced by the JsonSerizalizationDemo https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo
// Represents an interface Writable
public interface Writable {
    // EFFECTS: returns this as JSON object
    JSONObject toJson();
}
