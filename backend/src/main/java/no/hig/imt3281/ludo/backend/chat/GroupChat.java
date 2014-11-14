package no.hig.imt3281.ludo.backend.chat;

import no.hig.imt3281.ludo.backend.ServerEnvironment;
import no.hig.imt3281.ludo.backend.User;
import no.hig.imt3281.ludo.messaging.GroupChatMessage;

/**
 * Created by Martin on 11.11.2014.
 */
public class GroupChat extends ChatRoom {
    private int id;
    private String caption;

    /**
     * Creates a new group chat which a user can create
     * @param id Id of the new group chat
     * @param caption Name/title of the group chat
     */
    public GroupChat(int id, String caption) {
        this.id = id;
        this.caption = caption;
    }

    public int getId() {
        return id;
    }

    public String getCaption() {
        return caption;
    }

    @Override
    public void broadcastSystemMessage(String systemMessage) {
        GroupChatMessage message = new GroupChatMessage();
        message.setMessage(systemMessage);
        message.setChannelId(this.id);
        message.setTimestamp(ServerEnvironment.getCurrentTimeStamp());

        super.broadcastMessage(message);
    }

    @Override
    public void userSays(User user, String chatMessage) {
        ChatLogEntry entry = new ChatLogEntry(ChatLogEntry.GROUP_MESSAGE);
        entry.setMessage(chatMessage);
        entry.setGroupChatId(this.id);
        entry.setTimestamp(ServerEnvironment.getCurrentTimeStamp());
        entry.setUserId(user.getId());

        ServerEnvironment.getChatManager().storeChatLogEntry(entry);

        GroupChatMessage message = new GroupChatMessage();
        message.setMessage(chatMessage);
        message.setChannelId(this.id);
        message.setUsername(user.getUsername());
        message.setUserId(user.getId());
        message.setTimestamp(ServerEnvironment.getCurrentTimeStamp());

        super.broadcastMessage(message);
    }
}
