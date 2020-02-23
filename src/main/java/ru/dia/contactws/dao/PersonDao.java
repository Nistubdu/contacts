package dao;

import domain.Person;

import java.util.List;

public interface PersonDao {

    List<Person> findAll();

    void create(Person person);

    void update(Person person);

    void executeUpdate(Person person);

    void delete(Person person);
}
