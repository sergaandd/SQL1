package shpp.mentor;

import org.apache.ibatis.jdbc.ScriptRunner;

import java.io.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Arrays;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class RunSQLScript {
    static Connection connection;

    RunSQLScript(Connection myConnection) {
        connection = myConnection;
    }

    public static String startScript(String filename) throws FileNotFoundException {
        String result;
        try {
            ScriptRunner sr = new ScriptRunner(connection);
            Reader reader = new BufferedReader(new FileReader(filename));
            sr.runScript(reader);
            result = "Sql done";
        } catch (FileNotFoundException e) {
            result = "File not found!";
        }
        return result;
    }

    public static String startScriptResult(Statement myInstance,String filename,String typeArg) throws FileNotFoundException {
        String result="No data found";
        try {
            BufferedReader reader = new BufferedReader(new FileReader(filename));
            Stream<String> res = reader.lines().collect(Collectors.joining(" ")).lines();
            String query=Arrays.toString(res.toArray(String[]::new))
                    .replace("[","")
                    .replace("]","").replace("****",typeArg.trim());
            ResultSet select=myInstance.executeQuery(query);
                while (select.next()) {
                    result = select.getString("sum").trim() + " -> " +
                            select.getString("type_name").trim() +
                            " -> " + select.getString("store_name").trim();
                }
        } catch (FileNotFoundException e) {
                result="File not found.";
        } catch (IOException | SQLException e) {
            throw new RuntimeException(e);
        }
        return result;
    }

}
