package co.wedevx.digitalbank.automation.ui.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

//build a logic that read the config file which is the .properties file
public class ConfigReader {

    private static Properties properties;

    //static initializer run the block only once for the whole project
    //instance initializers run the block once for every object created from the class
    static {
        //filePath -> the directory (location) of your .properties file
        String filePath = "src/test/resources/properties/digitalbank.properties";

        //this is a class that enables you to read files
        //FileInputStream throws a checked FileNotFoundException

        FileInputStream input = null;

        try {
            input = new FileInputStream(filePath);
            properties = new Properties();
            properties.load(input);

        } catch (IOException e) { //FileNotFoundException is a child of IOException, so they can be collapsed
            System.out.println("File not found");
        } finally {
            try {
                input.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public static String getPropertiesValue(String key) {
        return properties.getProperty(key);
    }
}
