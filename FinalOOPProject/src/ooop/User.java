package ooop;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

// Public class
public class User {
    private int uid;
    private String userName;
    private String userPassword;

    // Constructors
    public User() {
        this.userName = "";
        this.userPassword = "";
    }

    // constructors with parameters
    public User(String userName, String userPass) {
        this.userName = userName;
        this.userPassword = userPass;
    }

    // Getter  get private data value	
    public String getUserName() {
        return this.userName;
    }

    public String getPassword() {
        return this.userPassword;
    }

    // setter - set private data	 
    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setPassword(String userPass) {
        this.userPassword = userPass;
    }

    @Override
    public String toString() {
        return this.userName + ", " + this.userPassword;
    }
    
    //test login

    public static void main(String[] args) {
        User user = new User("testuser", "testpass");
        UserManager manager = new UserManager();
        boolean success = manager.login(user);
        System.out.println("Login successful? " + success);
    }
}

//Handles database login logic


class UserJDBC {
    public boolean login(User user) {
        boolean result = false;
        try {
            Connection conn = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/oop", "root", "");  // connect sql

            String sql = "SELECT * FROM login WHERE UserName = ? AND Password = ?"; // sql query check username anf psd
            PreparedStatement pstat = conn.prepareStatement(sql);
            pstat.setString(1, user.getUserName());
            pstat.setString(2, user.getPassword());

            ResultSet rs = pstat.executeQuery(); // run sql query and gives result
            if (rs.next()) { // check if any record matched
                result = true; // login success
            }

            rs.close(); // close result
            pstat.close(); // close sql 
            conn.close(); // close db connection
        } catch (Exception ex) {
            System.out.println("Error: " + ex.getMessage()); // pint error
        }
        return result;
    }
}

class UserManager {
	//handles login datas
    public boolean login(User user) {
        UserJDBC userJDBC = new UserJDBC(); // create db
        return userJDBC.login(user); // call db login and psd 
    }
}
