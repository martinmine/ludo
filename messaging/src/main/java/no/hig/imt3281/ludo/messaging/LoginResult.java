package no.hig.imt3281.ludo.messaging;

/**
 * Contains information about the result of a LoginRequest.
 */
public class LoginResult extends Message {
    /**
     * Invalid username or password.
     */
    public static final int INVALID_CREDENTIALS = -2;
    /**
     * There was a server error while signing in.
     */
    public static final int SERVER_ERROR = 0;
    /**
     * The login was successful.
     */
    public static final int OK = 1;

    private int resultCode;
    private int userId;

    public int getResultCode() {
        return resultCode;
    }

    public void setResultCode(int resultCode) {
        this.resultCode = resultCode;
    }

    public int getUserId() {
        return this.userId;
    }

    public void setUserId(int id) {
        this.userId = id;
    }
}
