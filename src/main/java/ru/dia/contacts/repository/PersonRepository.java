package ru.dia.contacts.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.dia.contacts.domain.Person;

@Repository
public interface PersonRepository extends JpaRepository<Person,Long> {
}
