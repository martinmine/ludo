package no.hig.imt3281.ludo.messaging;

/**
 * Message sent when users replies to a game challenge.
 */
public class GameChallengeResponse extends Message {
    /**
     * Challenge accepted.
     */
    public static final int ACCEPTED = 0;
    /**
     * User rejects the challenge.
     */
    public static final int REJECTED = 1;

    private int state;
    private int challengeId;

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public int getChallengeId() {
        return challengeId;
    }

    public void setChallengeId(int challengeId) {
        this.challengeId = challengeId;
    }
}
