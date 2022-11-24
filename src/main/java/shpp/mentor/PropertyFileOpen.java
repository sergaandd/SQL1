package shpp.mentor;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.logging.Logger;

public class PropertyFileOpen {
    PropertyFileOpen() {
    }

    public static final String FILE_NAME = "myProp.properties";
    static FileInputStream input;

    public static Properties openPropertyFile() throws IOException {
        try {
            Properties property = new Properties();
            FileInputStream input = new FileInputStream(FILE_NAME);
            property.load(input);
            return property;
        } catch (IOException e) {
            return null;
        }
    }
}
