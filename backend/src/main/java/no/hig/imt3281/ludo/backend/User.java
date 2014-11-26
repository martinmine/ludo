package no.hig.imt3281.ludo.backend;

import no.hig.imt3281.ludo.messaging.handling.CommunicationContext;
import no.hig.imt3281.ludo.messaging.handling.ConnectivityNotifier;

import javax.persistence.*;

/**
 * User entity manages all user-related data.
 */
@Entity
public class User implements ConnectivityNotifier {
    @Id
    @GeneratedValue
    private int id;

    @Column(unique = true)
    @Basic(fetch = FetchType.EAGER)
    private String username;
    @Basic(fetch = FetchType.EAGER)
    private String email;
    @Basic(fetch = FetchType.EAGER)
    private String password;

    @Transient
    private CommunicationContext clientConnection;

    @Transient
    private int currentGameId;

    @Transient
    private int gamePlayerId;

    /**
     * Makes a new default user.
     */
    public User() {
    }

    /**
     * Creates a username with id, username and email.
     * @param id Id of the user
     * @param username Username of the user
     * @param email Email of the user
     */
    public User(int id, String username, String email) {
        this.id = id;
        this.username = username;
        this.email = email;
    }

    /**
     * Makes a new user without a userid
     * @param username Username of the user
     * @param email Email of the user
     */
    public User(String username, String email) {
        this.username = username;
        this.email = email;
    }

    /**
     * Gets the id of the user
     * @return User id
     */
    public int getId() {
        return id;
    }

    /**
     * Sets the user id
     * @param id User id
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Gets the username of the user
     * @return Username
     */
    public String getUsername() {
        return username;
    }

    /**
     * Sets the username of the user
     * @param username Username
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Gets the email of the usr
     * @return The email
     */
    public String getEmail() {
        return email;
    }

    /**
     * Sets the email of the user
     * @param email The email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Gets the users hashed password
     * @return The hashed password
     */
    public String getPassword() {
        return password;
    }

    /**
     * Sets the users password
     * @param password The hashed password
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Sets a reference to the client connection
     * @param clientConnection The client connection
     */
    public void setClientConnection(CommunicationContext clientConnection) {
        this.clientConnection = clientConnection;
    }

    /**
     * Gets the client connection where data can be sent
     * @return The client connection reference
     */
    public CommunicationContext getClientConnection() {
        return clientConnection;
    }

    /**
     * Gets the id of the game where the user is in.
     * @return Game id, 0 if the user is not in a game.
     */
    public int getCurrentGameId() {
        return currentGameId;
    }

    /**
     * Sets the id of the game which the user is in.
     * @param currentGameId Id of the game which the user is in.
     */
    public void setCurrentGameId(int currentGameId) {
        this.currentGameId = currentGameId;
    }

    /**
     * The current color/faction of the user in the current game.
     * @return Color/faction
     */
    public int getGameFactionId() {
        return gamePlayerId;
    }

    /**
     * Sets the users current faction/color in a game.
     * @param gamePlayerId Color/faction
     */
    public void setGamePlayerId(int gamePlayerId) {
        this.gamePlayerId = gamePlayerId;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void connectionClosed() {
        ServerEnvironment.getUserManager().reportLoggedOut(this.id);
    }
}
