package ooop;

public class UserManager {
public boolean login(User user) {
//Receive user info from UI
//Validate user info
//Send user info to DB
//Receive user status from DB
//Return Login status to UI
UserJDBC userJDBC = new UserJDBC();
boolean result = userJDBC.login(user);
return result;
}
}