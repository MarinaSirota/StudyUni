package ud;

public class user {
	
	protected String login; 
	protected String password;
   
    public user (String login_, String password_) {        
	    login=login_;
	    password=password_;
    }   
    public String getLogin() {
        return login;
    } 
    public String getPassword() {
        return password;
    }
   
    public void setLogin(String login) {
        this.login = login;
    }
    public void setPassword(String password) {
        this.password = password;
    }
}
