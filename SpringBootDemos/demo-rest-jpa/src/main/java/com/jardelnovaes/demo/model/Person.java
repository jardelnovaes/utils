package com.jardelnovaes.demo.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Person {
    @Id
    @SequenceGenerator(name = "person_seq", sequenceName = "person_id_seq", allocationSize = 1, initialValue = 1)
    @GeneratedValue(generator = "person_seq")
    private long id;

    private String name;
}
