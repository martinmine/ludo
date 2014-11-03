package no.hig.imt3281.ludo.messaging;

/**
 * Created by Martin on 03.11.2014.
 */
public class LoginResult extends Message{
    public static final int INVALID_PASSWORD = -2;
    public static final int UNKNOWN_USERNAME = -1;
    public static final int SERVER_ERROR = 0;
    public static final int OK = 1;

    private int resultCode;

    public int getResultCode() {
        return resultCode;
    }

    public void setResultCode(int resultCode) {
        this.resultCode = resultCode;
    }
}
