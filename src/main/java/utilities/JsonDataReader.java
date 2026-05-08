package utilities;

import org.json.JSONObject;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * JsonDataReader - Reads test data from JSON file
 * Provides data-driven testing capability
 */
public class JsonDataReader {

    private static final String JSON_FILE_PATH = "src/main/resources/testdata.json";
    private static JSONObject jsonObject;

    static {
        try {
            String jsonContent = new String(Files.readAllBytes(Paths.get(JSON_FILE_PATH)));
            jsonObject = new JSONObject(jsonContent);
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Error reading JSON test data file");
        }
    }

    /**
     * Get test data object
     */
    public static JSONObject getTestData(String key) {
        return jsonObject.getJSONObject(key);
    }

    /**
     * Get string value from test data
     */
    public static String getValue(String dataKey, String fieldKey) {
        return jsonObject.getJSONObject(dataKey).getString(fieldKey);
    }

    /**
     * Get all test data
     */
    public static JSONObject getAllTestData() {
        return jsonObject;
    }
}
