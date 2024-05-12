package Utils;

import Data.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DatabaseUtils {
    private static final String DB_URL = "jdbc:mysql://localhost:3306/qaproject";
    private static final String USER = "root";
    private static final String PASS = "root";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(DB_URL, USER, PASS);
    }

    public static void createTableUsers() {
        String sql = "CREATE TABLE IF NOT EXISTS users (username VARCHAR(255), password VARCHAR(255))";
        try (Connection conn = getConnection();
             Statement stmt = conn.createStatement()) {

            stmt.execute(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void dropTableUsers() {
        String sql = "DROP TABLE IF EXISTS users";
        try (Connection conn = getConnection();
             Statement stmt = conn.createStatement()) {

            stmt.execute(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void createUser(User user) {
        String sql = "INSERT INTO users (username, password) VALUES (?, ?)";
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, user.getUsername());
            stmt.setString(2, user.getPassword());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static List<User> getUsers() {
        List<User> users = new ArrayList<>();
        String sql = "SELECT username, password FROM users";
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                String username = rs.getString("username");
                String password = rs.getString("password");
                users.add(new User(username, password));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }
}
