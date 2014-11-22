package no.hig.imt3281.ludo.messaging;

/**
 * RequestMessage class
 */
public class CreateChatRoomRequest extends Message {
    private String chatroomName;

    public String getChatroomName() {
        return this.chatroomName;
    }

    public void setChatroomName(String chatroomName) {
        this.chatroomName = chatroomName;
    }
}
