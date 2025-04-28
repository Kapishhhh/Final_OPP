package ooop;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class UserJDBC {
	public boolean login(User user) {
		//receive user info
		boolean result =false;
		try {
		//connect with db
		Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/oop","root","");
		String sql = "SELECT * FROM login WHERE UserName=? and Password=?";
		PreparedStatement pstat = conn.prepareStatement(sql);
		pstat.setString(1, user.getUserName());
		pstat.setString
		(2, user.getPassword());
	
		ResultSet rs = pstat.executeQuery();
		while(rs.next()) {
	
		result=true;
		}
		//return result
		}
		catch(Exception ex) {
		System.out.println("Error : "+ex.getMessage());
		}
		return result;
		}
		

}
