package test_data.authentication;

public class LoginCreds {

    private String username;
    private String password;

    public LoginCreds(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "LoginCreds{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
