package ru.dia.contactws.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.dia.contactws.domain.Person;

@Repository
@Transactional
public interface PersonRepository extends JpaRepository<Person,Long> {
}
