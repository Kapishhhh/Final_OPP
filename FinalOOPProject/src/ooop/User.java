package ooop;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

// Main public class
public class User {
    private int uid;
    private String userName;
    private String userPassword;

    // Constructors
    public User() {
        this.userName = "";
        this.userPassword = "";
    }

    public User(String userName, String userPass) {
        this.userName = userName;
        this.userPassword = userPass;
    }

    // Getters
    public String getUserName() {
        return this.userName;
    }

    public String getPassword() {
        return this.userPassword;
    }

    // Setters
    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setPassword(String userPass) {
        this.userPassword = userPass;
    }

    // toString
    public String toString() {
        return this.userName + ", " + this.userPassword;
    }

    // Entry point for testing
    public static void main(String[] args) {
        User user = new User("testuser", "testpass");
        UserManager manager = new UserManager();
        boolean success = manager.login(user);
        System.out.println("Login successful? " + success);
    }
}

// UserJDBC class (not public)
class UserJDBC {
    public boolean login(User user) {
        boolean result = false;
        try {
            // Connect to the database
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/oop", "root", "");

            // SQL query
            String sql = "SELECT * FROM login WHERE UserName = ? AND Password = ?";
            PreparedStatement pstat = conn.prepareStatement(sql);
            pstat.setString(1, user.getUserName());
            pstat.setString(2, user.getPassword());

            // Execute query
            ResultSet rs = pstat.executeQuery();
            if (rs.next()) {
                result = true; // Login success
            }

            // Clean up
            rs.close();
            pstat.close();
            conn.close();
        } catch (Exception ex) {
            System.out.println("Error: " + ex.getMessage());
        }
        return result;
    }
}

// UserManager class (not public)
class UserManager {
    public boolean login(User user) {
        UserJDBC userJDBC = new UserJDBC();
        return userJDBC.login(user);
    }
}
