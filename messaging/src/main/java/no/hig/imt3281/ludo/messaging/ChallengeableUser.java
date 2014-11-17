package no.hig.imt3281.ludo.messaging;

/**
 * Created by Martin on 17.11.2014.
 */
public class ChallengeableUser extends Message {
    private int userId;
    private String username;

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
