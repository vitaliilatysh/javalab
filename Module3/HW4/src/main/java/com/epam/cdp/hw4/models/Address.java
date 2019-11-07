package com.epam.cdp.hw4.models;

import javax.persistence.*;

@Entity
@Table(name = "Address")
public class Address {

    @Id
    @Column(name="id")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private long id;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
