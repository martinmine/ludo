package no.hig.imt3281.ludo.backend;


import no.hig.imt3281.ludo.messaging.handling.CommunicationContext;
import no.hig.imt3281.ludo.messaging.handling.ConnectivityNotifier;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Entity;

/**
 * Created by Martin on 27.10.2014.
 */
@Entity
public class User implements ConnectivityNotifier {
    @Id
    @GeneratedValue
    private int id;
    private String username;
    private String email;
    private CommunicationContext clientConnection;

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

    public CommunicationContext getClientConnection() {
        return clientConnection;
    }

    @Override
    public void connectionClosed() {
        ServerEnvironment.getUserManager().reportLoggedOut(this.id);
    }
}
