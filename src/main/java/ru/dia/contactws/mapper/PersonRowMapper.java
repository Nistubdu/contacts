package ru.dia.contactws.mapper;

import org.springframework.jdbc.core.RowMapper;
import ru.dia.contactws.domain.Person;

import java.sql.ResultSet;
import java.sql.SQLException;

public class PersonRowMapper implements RowMapper<Person> {

    @Override
    public Person mapRow(ResultSet rs, int arg1) throws SQLException {

        Person person = new Person();

        person.setId( Long.valueOf( rs.getString("id") ));
        person.setFirstName(rs.getString("first_name"));
        person.setLastName(rs.getString("last_name"));
        person.setMiddleName(rs.getString("middle_name"));

        return person;
    }
}
