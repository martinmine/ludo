package no.hig.imt3281.ludo.messaging;

import java.util.LinkedList;
import java.util.List;

/**
 * Message with the user ids the user wants to challenge
 */
public class ChallengeUserRequest extends Message {
    private List<Integer> userIds;

    /** Creates a new instance of ChallengeUserRequest */
    public ChallengeUserRequest() {
        this.userIds = new LinkedList<>();
    }
    /** @param userId to be added to userIds list */
    public void addUserId(Integer userId) {
        this.userIds.add(userId);
    }
    /** @return the list of userId */
    public List<Integer> getUserIds() {
        return userIds;
    }
    /** @param userIds is copied into this.userIds */
    public void setUserIds(List<Integer> userIds) {
        this.userIds = userIds;
    }
}
