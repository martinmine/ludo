package no.hig.imt3281.ludo.backend.chat;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * A chat log entry for every chat log entry in the system
 */
@Entity
public class chatLogEntry {
    /**
     * Indicates that a message is a public chat message
     */
    public static final int PUBLIC_MESSAGE = 0;
    /**
     * Indicates that a message is for a group chat
     */
    public static final int GROUP_MESSAGE = 1;
    /**
     * Indicates that a message is for the chat for a game
     */
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

    /**
     * Makes a new chat log entry
     * @param chatLogTypeFlag What kind of chat-log entry this is, valid values are
     *                        PUBLIC_MESSAGE, GROUP_MESSAGE and GAME_MESSAGE
     */
    public chatLogEntry(int chatLogTypeFlag) {
        this.chatLogTypeFlag = chatLogTypeFlag;
    }

    /**
     * Makes a new, default, chat log entry
     */
    public chatLogEntry() {
    }

    /**
     * Sets the id of the chat message
     * @param id Id of the message
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Gets the id of the message
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * Gets the id of the user who posted the message
     * @return User id
     */
    public int getUserId() {
        return userId;
    }

    /**
     * Sets the id of the user who posted this message
     * @param userId Id of the user
     */
    public void setUserId(int userId) {
        this.userId = userId;
    }

    /**
     * Gets the id of the game where the user posted the message
     * @return Id of the game
     */
    public int getGameId() {
        return gameId;
    }

    /**
     * Sets the id of the game where the user posted the message
     * @param gameId Id of the game
     */
    public void setGameId(int gameId) {
        this.gameId = gameId;
    }

    /**
     * Gets the id of the group chat where the user posted the message
     * @return group chat id
     */
    public int getGroupChatId() {
        return groupChatId;
    }

    /**
     * Sets the id of the chat where the user posted the message
     * @param groupChatId Group chat id
     */
    public void setGroupChatId(int groupChatId) {
        this.groupChatId = groupChatId;
    }

    /**
     * Gets the type of the chat log entry, will either be
     * PUBLIC_MESSAGE, GROUP_MESSAGE or GAME_MESSAGE
     * @return Chat type
     */
    public int getChatlogTypeFlag() {
        return this.chatLogTypeFlag;
    }

    /**
     * Sets the type of that chat where the user posted the message.
     * A message can be of type PUBLIC_MESSAGE, GROUP_MESSAGE or GAME_MESSAGE
     * @param chatLogTypeFlag Chat message type
     */
    public void setChatlogTypeFlag(int chatLogTypeFlag) {
        this.chatLogTypeFlag = chatLogTypeFlag;
    }

    /**
     * Gets the actual message which the user posted
     * @return The message
     */
    public String getMessage() {
        return message;
    }

    /**
     * Sets the message which the user posted
     * @param message The message
     */
    public void setMessage(String message) {
        this.message = message;
    }

    /**
     * Gets the timestamp for when the user posted the message
     * @return Timestamp
     */
    public int getTimestamp() {
        return timestamp;
    }

    /**
     * Sets the timestamp of when the message was posted
     * @param timestamp Timestamp
     */
    public void setTimestamp(int timestamp) {
        this.timestamp = timestamp;
    }
}
