package no.hig.imt3281.ludo.messaging;

/**
 * Message sent to the client after the user has requested that a token
 * should be moved with the dice value which the user was assigned.
 * This message indicates whether the move was valid or not.
 * If the move was not valid, the user should click another token.
 */
public class MoveTokenResult extends Message {
    private boolean validMove;

    public boolean isValidMove() {
        return validMove;
    }

    public void setValidMove(boolean validMove) {
        this.validMove = validMove;
    }
}
