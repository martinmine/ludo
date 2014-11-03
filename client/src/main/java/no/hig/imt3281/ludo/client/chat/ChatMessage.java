package no.hig.imt3281.ludo.client.chat;

/**
 * Created by Joakim on 03.11.2014.
 */
public class ChatMessage {

    private int messageType;
    private String message;

    public ChatMessage(String message){
        this.messageType = -1;
        this.message = message;
    }

    public void setMessageType(int type) {
        this.messageType = type;
    }
}
