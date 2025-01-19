package Config;


import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * <h1>Header </h1>
 * description
 * <p>
 *
 * @author Kalin Govender
 * @version 1.1
 * @since 2025/01/19
 */

public class propertiesdReader {
    private static Properties properties;
    private static final String DIRECTORY = System.getProperty("user.dir");
    private static final String systemDelimiter = File.separator;



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
}
