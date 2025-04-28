package ooop;

public class User {
	
		private int uid;
		private String userName; 
		private String userPassword;
		//Constructors
		public User() {
		
		this.userName="";
		this.userPassword="";
		}
		public User(String userName, String userPass) {
		
		this.userName=userName;
		this.userPassword=userPass;
		}
		//Getters
		
		public String getUserName() {
		return this.userName;
		}
		public String getPassword() {
		return this.userPassword;
		}
		//Setters
		
		public void setUserName(String userName) {
		this.userName=userName;
		}
		public void setPassword(String userPass) {
		this.userPassword=userPass;
		}
		//toString
		public String toString() {
		return this.userName+", "+this.userPassword;
		}

		}


