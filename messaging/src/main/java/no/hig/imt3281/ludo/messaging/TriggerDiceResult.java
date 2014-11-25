package no.hig.imt3281.ludo.messaging;

/**
 * Message being sent to all the clients in the game when a player has requested that the dice should be triggered.
 */
public class TriggerDiceResult extends Message {
    private int diceValue;
    private boolean anyTokensValid;
    private int currentMovingFaction;

    public int getDiceValue() {
        return diceValue;
    }

    public void setDiceValue(int diceValue) {
        this.diceValue = diceValue;
    }

    public boolean hasAnyTokensValid() {
        return anyTokensValid;
    }

    public void setAnyTokensValid(boolean anyTokensValid) {
        this.anyTokensValid = anyTokensValid;
    }

    public void setCurrentMovingFaction(int currentMovingFaction) {
        this.currentMovingFaction = currentMovingFaction;
    }

    public int getCurrentMovingFaction() {
        return this.currentMovingFaction;
    }
}
