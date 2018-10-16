package model;

/**
 * Created by Administrator on 2017/5/27 0027.
 */
public class UsernamePassword {
    public String username;
    public  String password;

    public UsernamePassword() {}
    public UsernamePassword(String username, String password) {
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
}
