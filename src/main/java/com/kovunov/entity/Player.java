package com.kovunov.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity

@NamedQuery(name = "Player.findAll", query = "SELECT p FROM Player p")
@NamedQuery(name = "Player.getByUserName", query = "SELECT p from Player p where p.userName = :userName")
@NamedQuery(name = "Player.clearAll", query = "DELETE FROM Player")
public class Player implements Comparable<Player>, Serializable {
    @Id
    @GeneratedValue(generator = "Player")
    private Long id;
    @Column(unique = true)
    private String userName;
    private String firstName;
    private String lastName;

    private Date signedUpDate;
    private Date birthDate;

    @OneToMany(mappedBy = "player", fetch = FetchType.EAGER)
    private List<Request> requestList;

    @ManyToOne
    @JoinColumn(name = "id_team")
    @JsonIgnore
    private Team team;

    @PrePersist
    void createdAt() {
        this.signedUpDate = new Date();
    }

    @PreUpdate
    void updatedAt() {
        this.signedUpDate = new Date();
    }

    public Player(String userName, String firstName) {
        this.userName = userName;
        this.firstName = firstName;
    }

    public Player(String userName, String firstName, String lastName) {
        this.userName = userName;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    @Override
    public int compareTo(Player o) {
        return signedUpDate.compareTo(o.signedUpDate);
    }
}
