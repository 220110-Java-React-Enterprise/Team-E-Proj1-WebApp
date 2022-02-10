package utils;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;


//creates a string that will be passed to the orm to create the connection to the database
public class ConnectionManager {
    private static Connection conn;
    private static String connectionStr;
    private static boolean databaseStartedUp = false;
    public static void setUpConnection() {

        //creates connection string to pass in orm
        try {
            Properties props = new Properties();
            ClassLoader loader = Thread.currentThread().getContextClassLoader();
            InputStream input = loader.getResourceAsStream("jdbc.properties");
            props.load(input);
            String connectionString = "jdbc:mariadb://" +
                    props.getProperty("hostname") + ":" +
                    props.getProperty("port") + "/" +
                    props.getProperty("dbname") + "?user=" +
                    props.getProperty("username") + "&password=" +
                    props.getProperty("password");
            Class.forName("org.mariadb.jdbc.Driver");
            conn = DriverManager.getConnection(connectionString);
            databaseStartedUp = true;
            connectionStr = connectionString;
        } catch (IOException | SQLException | ClassNotFoundException e) {
            FileLogger.getFileLogger().log(e);
        }
    }

    //returns the string that goes to the orm to get the connection
    public static String getConnection() {
        if (!databaseStartedUp) {
            setUpConnection();
        }
        return connectionStr;
    }
}
