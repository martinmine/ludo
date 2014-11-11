package no.hig.imt3281.ludo.messaging;

/**
 * Created by Joakim on 11.11.2014.
 */
public class GroupChatMessage extends ChatMessage {
    private int channelId;

    public GroupChatMessage(String message) {
        super(message);
    }

    public int getChannelId() {
        return channelId;
    }

    public void setChannelId(int channelId) {
        this.channelId = channelId;
    }
}
