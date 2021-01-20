package com.jardelnovaes.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

@Data
@Entity
public class Person {
    @Id
    @SequenceGenerator(name = "person_seq", sequenceName = "person_id_seq", allocationSize = 1, initialValue = 1)
    @GeneratedValue(generator = "person_seq")
    private long id;

    private String name;
}
