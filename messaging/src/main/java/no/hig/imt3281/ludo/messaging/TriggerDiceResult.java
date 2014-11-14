package no.hig.imt3281.ludo.messaging;

/**
 * Created by Martin on 14.11.2014.
 */
public class TriggerDiceResult extends Message {
    private int diceValue;

    public int getDiceValue() {
        return diceValue;
    }

    public void setDiceValue(int diceValue) {
        this.diceValue = diceValue;
    }
}
