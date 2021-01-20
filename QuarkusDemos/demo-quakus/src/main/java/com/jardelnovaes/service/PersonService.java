package com.jardelnovaes.service;

import com.jardelnovaes.model.Person;
import com.jardelnovaes.repository.PersonRepository;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.List;

@ApplicationScoped
public class PersonService {

    @Inject
    private PersonRepository repository;

    // @Inject EntityManager em;

    @Transactional
    public Person add(final String name) {
        Person person = new Person();
        person.setName(name);
        // em.persist(person); // em.flush();
        repository.persist(person);
        return person;
    }

    public List<Person> findAll() {
        //return em.createQuery("from Person", Person.class).getResultList();
        return repository.listAll();
    }

    public List<Person> findByName(final String name) {
        return repository.findByName(name);
    }
}
