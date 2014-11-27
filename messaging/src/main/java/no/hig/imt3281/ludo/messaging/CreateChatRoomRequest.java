package no.hig.imt3281.ludo.messaging;

/**
 * Message for requesting that a new chat room should be created.
 */
public class CreateChatRoomRequest extends Message {
    private String chatroomName;

    /**
     * Gets the name of the chat room.
     * @return Chat room name.
     */
    public String getChatroomName() {
        return this.chatroomName;
    }

    /**
     * Sets the name of the chat room.
     * @param chatroomName Name of the chat room.
     */
    public void setChatroomName(String chatroomName) {
        this.chatroomName = chatroomName;
    }
}
