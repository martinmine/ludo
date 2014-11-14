package no.hig.imt3281.ludo.messaging;

public abstract class ChatMessage extends Message {
    private int userId;
    private String username;
    private String message;
    private int timestamp;
    public static final String USER_JOIN = "USER_JOIN";
    public static final String USER_LEAVE = "USER_LEAVE";

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

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(int timestamp) {
        this.timestamp = timestamp;
    }
}
