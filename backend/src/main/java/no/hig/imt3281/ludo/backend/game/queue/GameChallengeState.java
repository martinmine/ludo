package no.hig.imt3281.ludo.backend.game.queue;

/**
 * Defines types of states which the user can be in while being challenged
 */
public class GameChallengeState {
    /**
     * User has not responded to the challenge
     */
    public static final int WAITING = 0;
    /**
     * User has accepted the challenge
     */
    public static final int ACCEPTED = 1;
    /**
     * User denied the challenge
     */
    public static final int DENIED = 2;

    private GameChallengeState() {
    }
}
