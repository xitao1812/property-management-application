package persistence;

import model.Property;
import model.PropertyList;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

// Represents a reader test
public class JsonReaderTest extends JsonTest {

    @Test
    void testReaderNonExistentFile() {
        JsonReader reader = new JsonReader("./data/noSuchFile.json");
        try {
            PropertyList pl = reader.read();
            fail("IOException expected");
        } catch (IOException e) {
            // pass
        }
    }


    @Test
    void testReaderEmptyPropertyList() {
        JsonReader reader = new JsonReader("./data/testReaderEmptyPropertyList.json");
        try {
            PropertyList pl = reader.read();
            assertEquals("Realtor's property list", pl.getListName());
            assertEquals(0, pl.size());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    void testReaderGeneralPropertyList() {
        JsonReader reader = new JsonReader("./data/testReaderGeneralPropertyList.json");
        try {
            PropertyList pl = reader.read();
            assertEquals("Realtor's property list", pl.getListName());
            List<Property> properties = pl.getPropertyList();
            assertEquals(2, properties.size());
            checkProperty("3888 FOSTER STREET", "RICHMOND",
                    1400000, "Peter", properties.get(0));
            checkProperty("3038 MANITOBA STREET", "BURNABY",
                    900000, "Jason", properties.get(1));
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }
}
