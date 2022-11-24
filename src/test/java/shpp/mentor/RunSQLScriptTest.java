package shpp.mentor;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import static org.junit.jupiter.api.Assertions.*;

class RunSQLScriptTest {
    Properties myProp = PropertyFileOpen.openPropertyFile();
    Connection connectionToDB = DriverManager.getConnection(
            myProp.getProperty("URL"),
            myProp.getProperty("userName"),
            myProp.getProperty("password"));

    RunSQLScriptTest() throws IOException, SQLException {
    }

    @Test
    void startScript() throws FileNotFoundException {
        String actual = new RunSQLScript(connectionToDB).startScript("src/main/resources/DBCreateScript.sql");
        String expected = "Sql done";
        Assertions.assertEquals(actual, expected);
    }

    @Test
    void startScriptResult() throws SQLException, FileNotFoundException {
        Statement myInstance = connectionToDB.createStatement();
        String actual = new RunSQLScript(connectionToDB).startScriptResult(myInstance
                , "src/main/resources/MainQuery.sql","Drinks");
        String expected = "No data found";
        Assertions.assertEquals(actual, expected);
    }
}