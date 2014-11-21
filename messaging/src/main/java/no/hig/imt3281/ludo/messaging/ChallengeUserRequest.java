package no.hig.imt3281.ludo.messaging;

import java.util.LinkedList;
import java.util.List;

/**
 * Message with the user ids the user wants to challenge
 */
public class ChallengeUserRequest extends Message {
    private List<Integer> userIds;

    public ChallengeUserRequest() {
        this.userIds = new LinkedList<>();
    }

    public void addUserId(Integer userId) {
        this.userIds.add(userId);
    }

    public List<Integer> getUserIds() {
        return userIds;
    }

    public void setUserIds(List<Integer> userIds) {
        this.userIds = userIds;
    }
}
