package com.epam.cdp.hw4.models;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "Personal")
public class Personal {
    @Id
    @Column(name="id")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private long id;
    private String name;
    private String surname;
    private Date birthDate;
}
