package csu.web.mypetstore.persistence;

import java.sql.*;
import com.mysql.cj.jdbc.Driver;

public class DBUtil {
    private static String DRIVER = "com.mysql.cj.jdbc.Driver";
    private static String URL = "jdbc:mysql://127.0.0.1:3306/mypetstore";
    private static String USERNAME = "root";
    private static String PASSWORD = "123456";

    private static String CREATE_LOG_TABEL = "CREATE TABLE IF NOT EXISTS user_logs (" +
            "id INT AUTO_INCREMENT PRIMARY KEY, " +
            "user_name VARCHAR(255) NOT NULL, " +
            "action_type VARCHAR(255) NOT NULL, " +
            "action_details VARCHAR(225), " +
            "request_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP" +
            ");";

    private static String CREATE_CART_ITEM_TABEL = "CREATE TABLE IF NOT EXISTS cartitem (" +
            "    username VARCHAR(50) NOT NULL," +
            "    itemid VARCHAR(20) NOT NULL," +
            "    quantity INT NOT NULL," +
            "    in_stock BOOLEAN NOT NULL DEFAULT TRUE," +
            "    PRIMARY KEY (username, itemid)," +
            "    FOREIGN KEY (username) REFERENCES signon(username)," +
            "    FOREIGN KEY (itemid) REFERENCES item(itemid)" +
            ");";

    public static Connection getConnection() {
        Connection connection = null;
        try {
            Class.forName(DRIVER);
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return connection;
    }

    public static void closeConnection(Connection connection) {
        try {
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void closeStatement(Statement statement) {
        try {
            statement.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void closePreparedStatement(PreparedStatement preparedStatement) {
        try {
            preparedStatement.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void closeResultSet(ResultSet resultSet) {
        try {
            resultSet.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void creatLogTable() {
        try {
            Connection connection = getConnection();
            Statement statement = connection.createStatement();
            statement.executeUpdate(CREATE_LOG_TABEL);
            System.out.println("Table 'user_logs is ready" );
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public static void creatCartItemTable() {
        try {
            Connection connection = getConnection();
            Statement statement = connection.createStatement();
            statement.executeUpdate(CREATE_CART_ITEM_TABEL);
            System.out.println("Table 'cartitem is ready" );
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
