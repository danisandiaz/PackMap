package com.danisanchez.PackMap.model;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Entity
public class Traveler {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String email;

    private String password;


    @OneToMany(mappedBy = "traveler", cascade = CascadeType.REMOVE)
    private Set<Trip> trips;


    public Traveler() {
    }

    public Traveler(long id, String email, String password){
        this.id = id;
        this.email = email;
        this.password = password;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
}