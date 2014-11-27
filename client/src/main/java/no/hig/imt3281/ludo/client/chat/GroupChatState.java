package no.hig.imt3281.ludo.client.chat;

import no.hig.imt3281.ludo.client.Main;
import no.hig.imt3281.ludo.messaging.GroupChatMessage;
import java.io.IOException;
/**
 * State for the ChatMessageHandler
 * Creates and sends GroupChatMessages to the server
 */
public class GroupChatState implements ChatState {
    private int type = ChatMessageHandler.GROUP_CHAT;
    /**
     * Creates and sends a groupChatMessage to the server
     * @param channelId is the channel of the specific group channel
     * @param message chatmessage string
     */
    @Override
    public void broadcastMessage(int channelId, String message) {
        try {
            GroupChatMessage groupChatMessage = new GroupChatMessage();
            groupChatMessage.setMessage(message);
            groupChatMessage.setChannelId(channelId);
            Main.getServerConnection().sendMessage(groupChatMessage);
        } catch (IOException e) {
            Main.getServerConnection().close(e);
        }
    }

    @Override
    public int getStateType() {
        return type;
    }
}
