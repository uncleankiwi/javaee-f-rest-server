package com.kovunov.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@NamedQuery(name = "Request.findAll", query = "SELECT r FROM Request r")
public class Request implements Serializable {
    @Id
    @GeneratedValue
    private Long id;
    private String requestText;

    @Lob //for longer requests - e.g. >500 length
        //signifies that this field is to be treated as binary data
    //Column(length=500) //limits the length of the string?
    private String requestTest;

    @ManyToOne
    @JoinColumn(name = "id_player")
    @JsonIgnore //fasterxml: needed because player entities holds a list
        //of requests, and a request also holds a player entity, so
        //sending a request will lead to stack overflow
    private Player player;

    public Request(String requestText) {
        this.requestTest = requestText;
    }
}
