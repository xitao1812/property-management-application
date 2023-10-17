package persistence;

import model.Property;
import model.PropertyList;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class JsonWriterTest extends JsonTest {


    @Test
    void testWriterInvalidFile() {
        try {
            PropertyList pl = new PropertyList("Realtor's property list");
            JsonWriter writer = new JsonWriter("./data/my\0illegal:fileName.json");
            writer.open();
            fail("IOException was expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testWriterEmptyPropertyList() {
        try {
            PropertyList pl = new PropertyList("Realtor's property list");
            JsonWriter writer = new JsonWriter("./data/testWriterEmptyPropertyList.json");
            writer.open();
            writer.write(pl);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterEmptyPropertyList.json");
            pl = reader.read();
            assertEquals("Realtor's property list", pl.getListName());
            assertEquals(0, pl.size());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    void testWriterGeneralPropertyList() {
        try {
            PropertyList pl = new PropertyList("Realtor's property list");
            pl.addProperty(new Property("3888 FOSTER STREET", "RICHMOND",
                    1400000, "Peter"));
            pl.addProperty(new Property("3038 MANITOBA STREET", "BURNABY",
                    900000, "Jason"));
            JsonWriter writer = new JsonWriter("./data/testWriterGeneralPropertyList.json");
            writer.open();
            writer.write(pl);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterGeneralPropertyList.json");
            pl = reader.read();
            assertEquals("Realtor's property list", pl.getListName());
            List<Property> properties = pl.getPropertyList();
            assertEquals(2, properties.size());
            checkProperty("3888 FOSTER STREET", "RICHMOND",
                    1400000, "Peter", properties.get(0));
            checkProperty("3038 MANITOBA STREET", "BURNABY",
                    900000, "Jason", properties.get(1));

        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }


}
