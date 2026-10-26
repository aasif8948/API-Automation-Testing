package utils;

import java.io.InputStream;
import java.util.Properties;
    public class ConfigReader {

        public static String getProperty(String key) {

            Properties prop = new Properties();

            try (InputStream input = ConfigReader.class.getClassLoader().getResourceAsStream("config.properties")) {

                if (input == null) {
                    throw new RuntimeException("config.properties not found");
                }

                prop.load(input);

            } catch (Exception e) {
                throw new RuntimeException(e);
            }

            return prop.getProperty(key);
        }
    }


