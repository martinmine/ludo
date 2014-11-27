package no.hig.imt3281.ludo.messaging;

/**
 * Message for group chat messages.
 */
public class GroupChatMessage extends ChatMessage {
    private int channelId;

    public int getChannelId() {
        return channelId;
    }

    public void setChannelId(int channelId) {
        this.channelId = channelId;
    }
}
