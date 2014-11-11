package no.hig.imt3281.ludo.messaging;

/**
 * Created by Martin on 03.11.2014.
 */
public class RegistrationResult extends Message {
    public static final int OK = -1;
    public static final int SERVER_ERROR = 0;
    public static final int WEAK_PASSWORD = 1;
    public static final int INVALID_MAIL = 2;
    public static final int INVALID_USERNAME = 3;
    public static final int USERNAME_TAKEN = 4;

    private int result;

    public int getResult() {
        return result;
    }

    public void setResult(int result) {
        this.result = result;
    }
}
