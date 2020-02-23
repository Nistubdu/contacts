package service;

import domain.Person;

import java.util.List;

public interface PersonService {

    List<Person> findAll();

    void create(Person person);

    void update(Person person);

    void executeUpdate(Person person);

    void delete(Person person);

}
