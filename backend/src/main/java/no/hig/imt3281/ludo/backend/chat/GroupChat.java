package no.hig.imt3281.ludo.backend.chat;

import no.hig.imt3281.ludo.backend.User;

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
    void userSays(User user, String message) {

    }
}
