package no.hig.imt3281.ludo.messaging;

/**
 * Abstract class representing a generic chatmessage
 */

public abstract class ChatMessage extends Message {
    public static final String USER_JOIN = "USER_JOIN";
    public static final String USER_LEAVE = "USER_LEAVE";

    private int userId;
    private String username;
    private String message;
    private int timestamp;

    /** @return userId  */
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
    /** @return message */
    public String getMessage() {
        return message;
    }
    /** @param message to be set */
    public void setMessage(String message) {
        this.message = message;
    }
    /** @return timestamp */
    public int getTimestamp() {
        return timestamp;
    }
    /** @param timestamp to be set */
    public void setTimestamp(int timestamp) {
        this.timestamp = timestamp;
    }
}
