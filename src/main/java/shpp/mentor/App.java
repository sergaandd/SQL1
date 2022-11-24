package shpp.mentor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.io.*;
import java.sql.*;
import java.sql.Connection;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Properties;
import java.util.Timer;
import java.util.concurrent.atomic.AtomicInteger;

public class App {
    public static final Logger logger = LoggerFactory.getLogger(App.class);

    public static void main(String[] args) throws SQLException, IOException {
        Long start = System.currentTimeMillis();
        Properties myProp = PropertyFileOpen.openPropertyFile();//Open property file
        //Create connection to database
        Connection connectionToDB = DriverManager.getConnection(
                myProp.getProperty("URL"),
                myProp.getProperty("userName"),
                myProp.getProperty("password"));
        String result = new RunSQLScript(connectionToDB).startScript("src/main/resources/DBCreateScript.sql");
        connectionToDB.commit();
        connectionToDB.setAutoCommit(true);
        //Create statement
        Statement myInstance = connectionToDB.createStatement();
        //Return qty of records generated and sent in queue by stream
        AtomicInteger resultSession = new StreamProduce(myInstance).loadStreamTypes(
                myProp.getProperty("typesList"));
        logger.info("We send in table TYPES {}", resultSession, " records.");

        resultSession = new StreamProduce(myInstance).loadStreamStores(
                Integer.parseInt(myProp.getProperty("qtyStores")), myProp.getProperty("storeAddressPrefix"));
        logger.info("We send in table STORES {}", resultSession, " records.");

        resultSession = new StreamProduce(myInstance).loadStreamGoods(
                Integer.parseInt(myProp.getProperty("qtyGoods")),
                Integer.parseInt(myProp.getProperty("qtyTypes")));
        logger.info("We send in table GOODS {}", resultSession, " records.");

        resultSession = new StreamProduce(myInstance).loadStreamLots(connectionToDB,
                Integer.parseInt(myProp.getProperty("qtyLots")),
                Integer.parseInt(myProp.getProperty("qtyStores")),
                Integer.parseInt(myProp.getProperty("qtyGoods")),
                Integer.parseInt(myProp.getProperty("qty")));
        logger.info("We send in table STORE_LOTS {}", resultSession, " records.");

        String consoleType=System.getProperty("type")==null?"Drinks":System.getProperty("type");
        String total = new RunSQLScript(connectionToDB).startScriptResult(myInstance
                , "src/main/resources/MainQuery.sql",consoleType);
        logger.info("Final select is {}", total);

        logger.info(String.valueOf((System.currentTimeMillis()-start)/1000)+"s");
        //Closing all elements of connection
        myInstance.close();
        connectionToDB.close();
    }
}
