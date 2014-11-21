package no.hig.imt3281.ludo.backend;

import no.hig.imt3281.ludo.messaging.handling.CommunicationContext;
import no.hig.imt3281.ludo.messaging.handling.ConnectivityNotifier;

import javax.persistence.*;
import java.io.IOException;

/**
 * Created by Martin on 27.10.2014.
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

    public User() {
    }

    public User(int id, String username, String email) {
        this.id = id;
        this.username = username;
        this.email = email;
    }

    public User(String username, String email) {
        this.username = username;
        this.email = email;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {

        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setClientConnection(CommunicationContext clientConnection) {
        this.clientConnection = clientConnection;
    }

    public CommunicationContext getClientConnection() {
        return clientConnection;
    }

    public int getCurrentGameId() {
        return currentGameId;
    }

    public void setCurrentGameId(int currentGameId) {
        this.currentGameId = currentGameId;
    }

    public int getGamePlayerId() {
        return gamePlayerId;
    }

    public void setGamePlayerId(int gamePlayerId) {
        this.gamePlayerId = gamePlayerId;
    }

    @Override
    public void connectionClosed() {
        ServerEnvironment.getUserManager().reportLoggedOut(this.id);
    }

    public void setTokensOnBoard() throws IOException {
        getClientConnection().sendMessage(new no.hig.imt3281.ludo.messaging.InitializePlayerTokenMessage());
    }
}
