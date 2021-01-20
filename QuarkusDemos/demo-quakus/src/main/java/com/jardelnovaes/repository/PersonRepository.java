package com.jardelnovaes.repository;

import com.jardelnovaes.model.Person;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

import javax.enterprise.context.ApplicationScoped;
import java.util.List;

@ApplicationScoped
public class PersonRepository implements PanacheRepository<Person> {

    public List<Person> findByName(final String name) {
        return find("name", name).list();
    }
}
