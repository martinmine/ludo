package no.hig.imt3281.ludo.client;

/**
 * Created by Joakim on 27.11.2014.
 */
public class User {
    private int userId;

    private User() {
        userId = -1;
    }

    private static class SingletonHolder {
       private static User INSTANCE = new User();
    }

    public void initialize(int id) {
        userId = id;
    }

    public static User getInstance() {
       return SingletonHolder.INSTANCE;
    }

    public int getUserId() {
        return this.userId;
    }
}
