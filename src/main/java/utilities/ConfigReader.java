package utilities;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * ConfigReader - Reads configuration from config.properties
 * Provides browser, base URL, and timeout configurations
 */
public class ConfigReader {

    private static Properties properties = new Properties();
    private static final String CONFIG_FILE_PATH = "src/main/resources/config.properties";

    static {
        try {
            FileInputStream file = new FileInputStream(CONFIG_FILE_PATH);
            properties.load(file);
            file.close();
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Error loading config.properties file");
        }
    }

    /**
     * Get property value from config.properties
     */
    public static String getProperty(String key) {
        return properties.getProperty(key);
    }

    /**
     * Get integer property value
     */
    public static int getIntProperty(String key) {
        return Integer.parseInt(properties.getProperty(key));
    }

    /**
     * Get boolean property value
     */
    public static boolean getBooleanProperty(String key) {
        return Boolean.parseBoolean(properties.getProperty(key));
    }
}
