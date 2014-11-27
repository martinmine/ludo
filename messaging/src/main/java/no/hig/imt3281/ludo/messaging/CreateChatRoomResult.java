package no.hig.imt3281.ludo.messaging;

/**
 * Result for creating the chat room
 */
public class CreateChatRoomResult extends Message {
    /**
     * The creation of the chat room was successful.
     */
    public static final int OK = 0;
    /**
     * There was an error during the creation of the chat room.
     */
    public static final int ERROR = 1;

    private int channelId;
    private int status;
    private String channelName;

    public String getChannelName() {
        return channelName;
    }

    public void setChannelName(String channelName) {
        this.channelName = channelName;
    }

    public int getChannelId() {
        return channelId;
    }

    public void setChannelId(int channelId) {
        this.channelId = channelId;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
