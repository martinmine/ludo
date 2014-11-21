package no.hig.imt3281.ludo.messaging;

/**
 * Created by Martin on 21.11.2014.
 */
public class GameChallengeResponse extends Message {
    public static final int ACCEPTED = 0;
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
