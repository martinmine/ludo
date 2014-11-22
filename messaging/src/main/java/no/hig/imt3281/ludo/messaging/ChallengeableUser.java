package no.hig.imt3281.ludo.messaging;

/**
 * Message containing information specific to a ChallengeableUser
 */
public class ChallengeableUser extends Message {
    private int userId;
    private String username;
    /** @return userId */
    public int getUserId() {
        return userId;
    }
    /** @param userId to be set */
    public void setUserId(int userId) {
        this.userId = userId;
    }
    /** @return username */
    public String getUsername() {
        return username;
    }
    /** @param username to be set */
    public void setUsername(String username) {
        this.username = username;
    }
}
