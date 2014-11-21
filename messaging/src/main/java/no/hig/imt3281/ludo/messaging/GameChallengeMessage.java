package no.hig.imt3281.ludo.messaging;

/**
 * Message sent to client when the user is being challenged
 */
public class GameChallengeMessage extends Message {
    private int challengeId;
    private int challengerUserId;
    private String challengerUsername;

    public int getChallengeId() {
        return challengeId;
    }

    public void setChallengeId(int challengeId) {
        this.challengeId = challengeId;
    }

    public int getChallengerUserId() {
        return challengerUserId;
    }

    public void setChallengerUserId(int challengerUserId) {
        this.challengerUserId = challengerUserId;
    }

    public String getChallengerUsername() {
        return challengerUsername;
    }

    public void setChallengerUsername(String challengerUsername) {
        this.challengerUsername = challengerUsername;
    }
}
