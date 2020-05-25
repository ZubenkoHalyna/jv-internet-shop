package mate.academy.internet.shop.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import mate.academy.internet.shop.exceptions.ConnectToDbException;
import org.apache.log4j.Logger;

public class ConnectionUtil {
    private static final Logger LOGGER = Logger.getLogger(ConnectionUtil.class);
    private static final Properties connectionProperties;
    private static final String URL =
            "jdbc:mysql://localhost:3306/internet_shop?serverTimezone=UTC";

    static {
        connectionProperties = new Properties();
        connectionProperties.put("user", "root");
        connectionProperties.put("password", "root");
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            LOGGER.error("MySQL JDBC driver not found", e);
            throw new ConnectToDbException(e);
        }
    }

    public static Connection getConnection() {
        try {
            return DriverManager.getConnection(URL, connectionProperties);
        } catch (SQLException e) {
            LOGGER.error("Enable connect to database", e);
            throw new ConnectToDbException(e);
        }
    }
}
