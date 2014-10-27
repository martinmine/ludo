package no.hig.imt3281.ludo.backend;


import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Entity;

/**
 * Created by Martin on 27.10.2014.
 */
@Entity
public class Player {
    @Id
    @GeneratedValue
    private int id;
    private String username;
    private String email;

    public Player() {
    }

    public Player(int id, String username, String email) {
        this.id = id;
        this.username = username;
        this.email = email;
    }

    public Player(String username, String email) {
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
}
