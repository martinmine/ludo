package no.hig.imt3281.ludo.messaging;

/**
 * Created by Joakim on 11.11.2014.
 */
public class CreateChatroomResult extends Message {
    public static final int OK = 0;
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
