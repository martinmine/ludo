package no.hig.imt3281.ludo.messaging;

/**
 * Created by Joakim on 11.11.2014.
 */
public class CreateChatroomRequest extends Message {
    private String chatroomName;

    public CreateChatroomRequest(String chatroomName) {
        this.chatroomName = chatroomName;
    }

    public String getChatroomName() {
        return this.chatroomName;
    }
}
