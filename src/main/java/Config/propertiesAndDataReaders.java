package Config;


import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

/**
 * <h1>propertiesAndDataReaders Class</h1>
 *
 * <p>This class provides utility methods for loading properties and JSON data files.</p>
 *
 * <h2>Methods:</h2>
 * <ul>
 *     <li><b>loadProperties(String fileName):</b> Loads properties from the specified file.</li>
 *     <li><b>getProperty(String key):</b> Retrieves a property value by key.</li>
 *     <li><b>getJSONPath(String jsonFileName):</b> Returns the full path to a JSON file.</li>
 *     <li><b>getJsonObjectData(String filePath, String array):</b> Retrieves a JSON array from a JSON file.</li>
 * </ul>
 *
 * @author Kalin Govender
 * @version 1.0
 * @since 2025/01/18
 */

public class propertiesAndDataReaders {
    private static Properties properties;
    private static final String DIRECTORY = System.getProperty("user.dir");
    private static final String systemDelimiter = File.separator;
    static JSONObject file = new JSONObject();
    static JSONParser jsonParser = new JSONParser();


    public static void loadProperties(String fileName) {
        properties = new Properties();
        try (FileInputStream input = new FileInputStream(DIRECTORY.concat((systemDelimiter + "Properties" + systemDelimiter + fileName)))) {
            properties.load(input);
            System.out.println("Properties loaded successfully from: " + fileName);
        } catch (IOException e) {
            throw new RuntimeException("Failed to load properties file: " + fileName, e);
        }
    }

    public static String getProperty(String key) {
        if (properties == null) {
            throw new IllegalStateException("Properties have not been loaded. Call loadProperties() first.");
        }
        return properties.getProperty(key);
    }

    public static String getJSONPath(String jsonFileName) {
        return DIRECTORY.concat("/TestData/" + jsonFileName);
    }

    public static JSONArray getJsonObjectData(String filePath, String array) {
        JSONObject file = parseFile(filePath);

        if (file != null) {
            return (JSONArray) file.get(array);
        }
        return null;

    }


    private static JSONObject parseFile(String filePath) {
        try {
            return (JSONObject) jsonParser.parse(new FileReader(filePath));
        } catch (Exception e) {
            System.err.println("Error parsing file: " + filePath);
            e.printStackTrace();
        }
        return null;
    }
}
