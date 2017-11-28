package co.example.samantha.doit;

/**
 * Created by Sam Schwartz-Horney on 11/27/2017.
 */

public class LoginItem {

    private String username;
    private String password;
    private String email;
    private int totalBets;

    public LoginItem(String username, String password, String email, int totalBets) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.totalBets = totalBets;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getTotalBets() {
        return totalBets;
    }

    public void setTotalBets(int totalBets) {
        this.totalBets = totalBets;
    }
}
