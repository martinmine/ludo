package no.hig.imt3281.ludo.messaging;

/**
 * Created by Martin on 03.11.2014.
 */
public class RegistrationResult extends Message {
    private final int OK = -1;
    private final int SERVER_ERROR = 0;
    private final int WEAK_PASSWORD = 1;
    private final int INVALID_MAIL = 2;
    private final int INVALID_USERNAME = 3;
    private final int USERNAME_TAKEN = 4;

    private int result;

    public int getResult() {
        return result;
    }

    public void setResult(int result) {
        this.result = result;
    }
}
