import org.example.JSONReader;
import org.example.JSONResourceReader;
import org.json.JSONException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class JSONReaderTests {
    private final static JSONReader jsonReader = new JSONResourceReader();
    @Test
    public void givenCorrectJSONFile_whenReadJSON_shouldNotReturnNull() {
        String url = "json/correct_asterisk.json";
        try {
            String json = jsonReader.readJSON(url);
            assertNotNull(json);
        } catch (JSONException e) {
            fail("Unexpected JSONException occurred");
        }
    }

    @Test
    public void givenIncorrectJSONFile_whenReadJSON_shouldThrowsException() {
        String url = "json/incorrect_json.json";
        assertThrows(JSONException.class, () -> {
            jsonReader.readJSON(url);
        });
    }

    @Test
    public void givenNotExistingFile_whenReadJSON_shouldThrowsException() {
        String url = "nonexistent.json";
        assertThrows(IllegalArgumentException.class, () -> {
            jsonReader.readJSON(url);
        });
    }
}
