package no.hig.imt3281.ludo.backend.chat;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * A chat log entry for every chat log entry in the system
 */
@Entity
public class ChatLogEntry {
    public static final int PUBLIC_MESSAGE = 0;
    public static final int GROUP_MESSAGE = 1;
    public static final int GAME_MESSAGE = 2;

    @Id
    @GeneratedValue
    private int id;
    private int userId;
    private int gameId;
    private int groupChatId;
    private int chatLogTypeFlag;
    private int timestamp;
    private String message;

    public ChatLogEntry(int chatLogTypeFlag) {
        this.chatLogTypeFlag = chatLogTypeFlag;
    }

    public ChatLogEntry() {
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getGameId() {
        return gameId;
    }

    public void setGameId(int gameId) {
        this.gameId = gameId;
    }

    public int getGroupChatId() {
        return groupChatId;
    }

    public void setGroupChatId(int groupChatId) {
        this.groupChatId = groupChatId;
    }

    public int getChatlogTypeFlag() {
        return this.chatLogTypeFlag;
    }

    public void setChatlogTypeFlag(int chatLogTypeFlag) {
        this.chatLogTypeFlag = chatLogTypeFlag;
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
