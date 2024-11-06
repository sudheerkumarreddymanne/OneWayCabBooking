package userDefinedLibraries;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class PropertiesRead {
    // Declares a Properties instance to hold the properties.
    public Properties prop;
    
    // Constructor for the PropertiesRead class.
    public PropertiesRead() {
        // Creates a File instance pointing to the properties file.
        File file = new File(System.getProperty("user.dir") + "/src/test/java/Configuration/config.properties");
        
        // Initializes the Properties instance.
        prop = new Properties();
        try (FileInputStream fileInput = new FileInputStream(file)) {
            // Loads the properties from the file input stream.
            prop.load(fileInput);        
        } catch (IOException e) {
            // Prints the stack trace if an IOException occurs.
            e.printStackTrace();
        }
    }
    
    // Method to get the value of a property by its key.
    public String getProperty(String key) {
        // Returns the property value for the given key.
        return prop.getProperty(key);
    }
}

