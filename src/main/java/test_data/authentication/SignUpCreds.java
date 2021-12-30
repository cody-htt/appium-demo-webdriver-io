package test_data.authentication;

public class SignUpCreds {

    private String email;
    private String password;
    private String repeatPassword;

    public SignUpCreds(String email, String password, String repeatPassword) {
        this.email = email;
        this.password = password;
        this.repeatPassword = repeatPassword;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String username) {
        this.email = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRepeatPassword() {
        return repeatPassword;
    }

    public void setRepeatPassword(String repeatPassword) {
        this.repeatPassword = repeatPassword;
    }

    @Override
    public String toString() {
        return "LoginCreds{" +
               "username='" + email + '\'' +
               ", password='" + password + '\'' +
               '}';
    }
}
