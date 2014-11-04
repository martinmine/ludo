package no.hig.imt3281.ludo.messaging;

/**
 * Created by Martin on 03.11.2014.
 */
public class LoginRequest extends Message{
    private String username;
    private String password;

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
